package it.extrasys.tesi.tagsystem.meal_service.db;

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
     */
    void addMenu(MenuEntity menu);

    /**
     * Update menu.
     *
     * @param menu
     *            the menu
     * @return the menu entity
     */
    MenuEntity updateMenu(MenuEntity menu);
}
