package it.extrasys.tesi.tagsystem.meal_service.db;

import java.util.List;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealEntity;
import it.extrasys.tesi.tagsystem.meal_service.db.entity.MenuEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface MealManaging.
 */

public interface MealManaging {

    /**
     * Adds the meal.
     *
     * @param meal
     *            the meal
     */
    void addMeal(MealEntity meal);
    /**
     * Adds the menu.
     *
     * @param menu
     *            the menu
     * @return
     */
    MenuEntity addMenu(MenuEntity menu);

    /**
     * Gets all the meal.
     *
     * @return the all meal
     */
    List<MealEntity> getAllMeal();

    /**
     * Gets the meal.
     *
     * @param id
     *            the id
     * @return the meal
     */
    MealEntity getMeal(int id);
    /**
     * Gets the menu.
     *
     * @param menuId
     *            the menu id
     * @return the menu
     */
    MenuEntity getMenu(int menuId);

    /**
     * Gets the by date.
     *
     * @param date
     *            the date
     * @return the by date
     */
    List<MenuEntity> getMenuByDate(String date);

    /**
     * Update meal.
     *
     * @param meal
     *            the meal
     * @return the meal entity
     */
    MealEntity updateMeal(MealEntity meal);

    /**
     * Update menu.
     *
     * @param menu
     *            the menu
     * @return the menu entity
     */
    MenuEntity updateMenu(MenuEntity menu);
}
