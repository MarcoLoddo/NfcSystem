package it.extrasys.tesi.tagsystem.meal_service.db;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.meal_service.db.dao.MealDao;
import it.extrasys.tesi.tagsystem.meal_service.db.dao.MenuDao;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class MealManagingImpl.
 */
@Component
public class MealManagingImpl implements MealManaging {

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
    @Transactional
    public void addMeal(MealEntity meal) {
        this.mealDao.save(meal);

    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#addMenu(it.
     * extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity)
     */
    @Transactional
    public void addMenu(MenuEntity menu) {

        this.menuDao.save(menu);

    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#getMeal(int)
     */
    @Transactional
    public MealEntity getMeal(int id) {
        return this.mealDao.findOne(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#getMenu(int)
     */
    @Transactional
    public MenuEntity getMenu(int menuId) {
        return this.menuDao.findOne(menuId);
    }

    public List<MenuEntity> getMenuByDate(String date) {
        return this.menuDao.findByDate(date);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.meal_service.db.MealManaging#updateMenu(it.
     * extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity)
     */
    @Transactional
    public MenuEntity updateMenu(MenuEntity menu) {
        this.menuDao.save(menu);
        return this.menuDao.findOne(menu.getMenuId());
    }

}
