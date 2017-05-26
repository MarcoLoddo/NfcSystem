package it.extrasys.tesi.tagsystem.meal_service;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
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

// TODO: Auto-generated Javadoc
/**
 * The Class MenuDaoTest.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class MenuDaoTest {

    /**
     * The Enum Verify.
     */
    private enum Verify {

        /** The nothing. */
        NOTHING,
        /** The single menu entity saved. */
        SINGLE_MENU_ENTITY_SAVED, MENU_EDITED
    }

    /** The menu dao. */
    @Autowired
    private MenuDao menuDao;

    /** The meal dao. */
    @Autowired
    private MealDao mealDao;

    /** The test meal entity. */
    private final MealEntity testMealEntity = createTestMealEntity();

    /** The verify database. */
    private Verify verifyDatabase = Verify.NOTHING;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MenuEntity menuEntity = createTestMenuEntity();
        this.testMealEntity.addToMenu(menuEntity);
        this.menuDao.save(menuEntity);
        this.mealDao.save(this.testMealEntity);
        this.mealDao.flush();
        this.menuDao.flush();

    }
    private void clear() {
        this.mealDao.deleteAll();
        this.menuDao.deleteAll();
    }
    /**
     * Test find meny by date.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testGetMenuByDate() throws Exception {
        clear();
        System.out.println("\n testFindMenyByDate \n");

        // Precondizione
        MenuEntity testMenuEntity = createTestMenuEntity();
        this.menuDao.save(testMenuEntity);
        this.menuDao.flush();

        this.mealDao.save(this.testMealEntity);

        testMenuEntity = createTestMenuEntity();
        this.menuDao.save(testMenuEntity);
        this.menuDao.flush();

        // Esecuzione
        this.mealDao.save(this.testMealEntity);

        // Verifica
        List<MenuEntity> menuEntities = this.menuDao
                .findByDate(Date.from(Instant.now()));

        assertThat(menuEntities.size(), is(2));
    }

    /**
     * Testa che i menu vengano recuperati correttamente tramite id.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testGetMenuById() throws Exception {
        clear();
        System.out.println("\n testFindMenyByDate \n");

        // Precondizione
        MenuEntity testMenuEntity = createTestMenuEntity();
        this.menuDao.save(testMenuEntity);
        this.menuDao.flush();

        this.mealDao.save(this.testMealEntity);

        MenuEntity testMenuEntity2 = createTestMenuEntity();
        this.menuDao.save(testMenuEntity2);
        this.menuDao.flush();

        // Esecuzione
        MenuEntity menuEntities = this.menuDao
                .findOne(testMenuEntity2.getMenuId());

        // Verifica
        assertThat(menuEntities, is(not(null)));
    }

    /**
     * Test update menu.
     */
    @Test
    @Commit
    public void testUpdateMenu() {
        this.verifyDatabase = Verify.MENU_EDITED;
        MenuEntity testMenuPersisted = this.menuDao.findAll().get(0);
        // Esecuzione
        testMenuPersisted.setType("vegan");
        this.menuDao.save(testMenuPersisted);
        this.menuDao.flush();

    }
    /**
     * Controlli post-transazione.
     */
    @AfterTransaction
    void verifyFinalDatabaseState() {
        switch (this.verifyDatabase) {
            case MENU_EDITED :
                assertThat(this.menuDao.findAll().get(0).getType(),
                        is(equalTo("vegan")));
                break;
            default :
        }
    }

    /**
     * Creates the test meal entity.
     *
     * @return the meal entity
     */
    private MealEntity createTestMealEntity() {
        MealEntity mealEntity = new MealEntity();

        mealEntity.setDescription("primo generico");
        mealEntity.setPrice(new BigDecimal("5"));
        mealEntity.setType(MealType.PASTA);

        return mealEntity;
    }

    /**
     * Creates the test menu entity.
     *
     * @return the menu entity
     */
    private MenuEntity createTestMenuEntity() {
        MenuEntity menuEntity = new MenuEntity();

        menuEntity.setType("vegetarian");
        menuEntity.setDate(Date.from(Instant.now()));

        return menuEntity;
    }
}
