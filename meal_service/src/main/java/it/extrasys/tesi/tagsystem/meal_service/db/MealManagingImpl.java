package it.extrasys.tesi.tagsystem.meal_service.db;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.meal_service.db.jpa.dao.MealDao;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.dao.MenuDao;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity.MenuEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class MealManagingImpl.
 */
@Component
public class MealManagingImpl implements MealManaging {

    @Autowired
    private EntityManager entityManager;
    /** The meal dao. */
    @Autowired
    private MealDao mealDao;

    /** The menu dao. */
    @Autowired
    private MenuDao menuDao;

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#addMeal(it.
     * extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity)
     */
    @Override
    @Transactional
    public MealEntity addMeal(MealEntity meal) {
        return this.mealDao.save(meal);

    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#addMenu(it.
     * extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity)
     */
    @Override
    @Transactional
    public MenuEntity addMenu(MenuEntity menu) {

        return this.menuDao.save(menu);

    }

    @Override
    @Transactional
    public List<MealEntity> getAllMeal() {
        return this.mealDao.findAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#getMeal(int)
     */
    @Override
    @Transactional
    public MealEntity getMeal(Long id) {
        return this.mealDao.findOne(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#getMenu(int)
     */
    @Override
    @Transactional
    public MenuEntity getMenu(Long menuId) {
        return this.menuDao.findOne(menuId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#getMenuByDate(
     * java.lang.String)
     */
    @Override
    @Transactional
    public List<MenuEntity> getMenuByDate(Date date) {
        return this.menuDao.findByDate(date);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#updateMeal(it.
     * extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity)
     */
    @Override
    @Transactional
    public MealEntity updateMeal(MealEntity meal) {

        return this.mealDao.save(meal);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#updateMenu(it.
     * extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity)
     */
    @Override
    @Transactional
    public MenuEntity updateMenu(MenuEntity menu) {

        return this.menuDao.save(menu);
    }

}
