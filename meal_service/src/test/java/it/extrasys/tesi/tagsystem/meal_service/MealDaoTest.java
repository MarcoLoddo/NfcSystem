// La classe di test va nello stesso package della classe da testare (ma in src/test/java)
package it.extrasys.tesi.tagsystem.meal_service;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.tesi.tagsystem.meal_service.db.jpa.dao.MealDao;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.dao.MenuDao;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MenuEntity;

/**
 * Test del repository JPA MealDao.
 *
 * @author davide
 */
@DataJpaTest
@RunWith(SpringRunner.class)
// La classe di test di chiama come la classe da testare, ma col suffisso "Test"
public class MealDaoTest {

    // Testiamo principalmente MealDao
    @Autowired
    private MealDao mealDao;

    // Serve solo per testare la relazione tra meal e menu
    @Autowired
    private MenuDao menuDao;

    private final MealEntity testMealEntity = createTestMealEntity();

    private Verify verifyDatabase = Verify.NOTHING;

    /**
     * Il setup lo mettiamo in cima, prima di tutto il resto, cosi' non ci
     * confondiamo.
     */
    @Before
    public void setup() {
        // Precondizione comune a tutti i test
        this.mealDao.deleteAll();
        this.menuDao.deleteAll();
    }

    /**
     * Il metodo di test deve avere un nome significativo.
     *
     * Il metodo "save" e' un metodo nativo dei repository JPA, quindi in teoria
     * dovrebbe sempre funzionare. Il vero scopo di questo test e' verificare di
     * non aver sbagliato il mapping dell'entita'.
     *
     * @throws Exception
     */
    @Test
    public void testSaveMealEntityAndReceiveNewId() throws Exception {
        // Uso System.out invece del logger perche' cosi' riesco a vedere
        // l'output anche dalla command line di Maven
        System.out.println("\n testSaveMealEntityAndReceiveNewId \n");

        // Precondizione
        MealEntity mealEntityToSave = this.testMealEntity;

        // Esecuzione
        this.mealDao.save(mealEntityToSave);

        // Verifica.
        // Mediante gli hamcrest matchers e' possibile scrivere le verifiche in
        // modo piu' naturale.
        // In un test bisogna effettuare solo la verifica pertinente a quel
        // test.
        assertThat(mealEntityToSave.getMealId(), is(notNullValue()));
        assertThat(mealEntityToSave.getMealId(),
                is(greaterThan(Long.parseLong("0"))));
    }

    /**
     * Verifica che la MealEntity venga correttamente salvata su database.
     *
     * @throws Exception
     */
    @Test
    @Commit
    public void testSaveMealEntityAndVerifyDatabaseState() throws Exception {
        System.out.println("\n testSaveMealEntityAndVerifyDatabaseState \n");

        // Precondizione
        MealEntity mealEntityToSave = this.testMealEntity;

        // Esecuzione
        this.mealDao.save(mealEntityToSave);

        // Verifica.
        // La verifica viene effettuata post-transazione.
        this.verifyDatabase = Verify.SINGLE_MEAL_ENTITY_SAVED;
    }

    /**
     * Metodo di verifica post-transazione.
     */
    private void verifySingleMealEntitySaved() {
        List<MealEntity> meals = this.mealDao.findAll();

        assertThat(meals.size(), is(1));
        assertThat(
                meals.get(
                        0),
                allOf(hasProperty("description",
                        is(this.testMealEntity.getDescription())),
                        hasProperty("type",
                                is(this.testMealEntity.getType()))));
    }

    /**
     * Verifica il metodo findByPrice.
     *
     * @throws Exception
     */
    @Test
    public void testFindMealEntityByPrice() throws Exception {
        System.out.println("\n testFindMealEntityByPrice \n");

        // Precondizione
        this.mealDao.save(this.testMealEntity);
        this.mealDao.flush();

        // Esecuzione
        List<MealEntity> meals = this.mealDao
                .findByPrice(this.testMealEntity.getPrice());

        // Verifica
        assertThat(meals.size(), is(1));
        assertThat(
                meals.get(
                        0),
                allOf(hasProperty("description",
                        is(this.testMealEntity.getDescription())),
                        hasProperty("type",
                                is(this.testMealEntity.getType()))));
    }

    /**
     * Verifica che la MealEntity venga correttamente associata ad un menu.
     *
     * @throws Exception
     */
    @Test
    public void testAssociateMealEntityWithMenu() throws Exception {
        System.out.println("\n testAssociateMealEntityWithMenu \n");

        // Precondizione
        MenuEntity testMenuEntity = createTestMenuEntity();
        this.menuDao.save(testMenuEntity);
        this.menuDao.flush();

        // Esecuzione
        this.testMealEntity.addToMenu(testMenuEntity);
        this.mealDao.save(this.testMealEntity);
        this.mealDao.flush();

        // Verifica
        List<MealEntity> meals = this.mealDao.findAll();

        assertThat(meals.size(), is(1));
        assertThat(meals.get(0).getMenus().size(), is(1));

        MenuEntity associatedMenuEntity = meals.get(0).getMenus().get(0);
        assertThat(associatedMenuEntity,
                hasProperty("type", is(testMenuEntity.getType())));
    }

    /**
     * Controlli post-transazione.
     */
    @AfterTransaction
    void verifyFinalDatabaseState() {
        switch (this.verifyDatabase) {
            case SINGLE_MEAL_ENTITY_SAVED :
                verifySingleMealEntitySaved();
                break;
            default :
        }
    }

    private enum Verify {
        NOTHING, SINGLE_MEAL_ENTITY_SAVED
    }

    private MealEntity createTestMealEntity() {
        MealEntity mealEntity = new MealEntity();

        mealEntity.setDescription("primo generico");
        mealEntity.setPrice(new BigDecimal("5"));
        mealEntity.setType(MealType.PASTA);

        return mealEntity;
    }

    private MenuEntity createTestMenuEntity() {
        MenuEntity menuEntity = new MenuEntity();

        menuEntity.setType("vegetarian");
        menuEntity.setDate(Date.from(Instant.now()));

        return menuEntity;
    }
}
