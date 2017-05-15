package it.extrasys.tesi.tagsystem.meal_service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import it.extrasys.tesi.tagsystem.meal_service.db.dao.MealDao;
import it.extrasys.tesi.tagsystem.meal_service.db.dao.MenuDao;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealType;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

/**
 * The Class MealTest.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class MealTest {

    @Autowired
    private MealDao mealDao;

    @Autowired
    private MenuDao menuDao;

    /**
     * Adds the meal to menu test.
     */
    @Test
    public void addMealToMenuTest() {
        addMenu();
        MealEntity mealEntity = new MealEntity();
        mealEntity.setDescription("secondo generico");
        mealEntity.setPrice(5);
        mealEntity.setType(MealType.meat);
        MenuEntity menuEntity = this.menuDao.findByDate("2017-05-10").get(0);
        mealEntity.addToMenu(menuEntity);
        this.mealDao.save(mealEntity);
        menuEntity.getMeals().add(mealEntity);
        this.menuDao.save(menuEntity);
        assertTrue(this.menuDao.findByDate("2017-05-10").get(0).getMeals()
                .size() > 1);

    }

    private void addMenu() {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setType("vegetarian");
        menuEntity.setDate("2017-05-10");

        MealEntity mealEntity = new MealEntity();
        mealEntity.setDescription("primo generico");
        mealEntity.setPrice(5);
        mealEntity.setType(MealType.pasta);
        menuEntity.getMeals().add(mealEntity);
        this.menuDao.save(menuEntity);
        mealEntity.addToMenu(menuEntity);
        this.mealDao.save(mealEntity);

    }

    /**
     * Adds the menu test.
     */
    @Test
    @Commit
    public void addMenuTest() {
        addMenu();
        assertTrue(this.mealDao.count() > 0);
        assertTrue(this.menuDao.count() > 0);
        MenuEntity menuEntity2 = this.menuDao.findByDate("2017-05-10").get(0);
        assertTrue(menuEntity2.getMeals().size() > 0);
    }
    /**
     * Setup.
     */
    @Before
    public void setup() {
        this.mealDao.deleteAll();
        this.menuDao.deleteAll();
    }

}
