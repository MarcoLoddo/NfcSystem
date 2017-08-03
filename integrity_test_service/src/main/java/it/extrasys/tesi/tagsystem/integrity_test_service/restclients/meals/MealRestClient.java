package it.extrasys.tesi.tagsystem.integrity_test_service.restclients.meals;

import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MenuDto;

public interface MealRestClient {

    /**
     * Adds the menu.
     *
     * @param menuDto
     *            the menu dto
     * @return the menu dto
     */
    MenuDto addMenu(MenuDto menuDto);

    /**
     * Gets the menu by day.
     *
     * @param date
     *            the date
     * @return the menu by day
     */
    List<MenuDto> getMenuByDay(Date date);

    /**
     * Gets the menu.
     *
     * @param id
     *            the id
     * @return the menu
     */
    MenuDto getMenu(long id);

    /**
     * Gets the all meals.
     *
     * @return the all meals
     */
    MealDto[] getAllMeals();

    /**
     * Update menu.
     *
     * @param menu
     *            the menu
     * @return the menu dto
     */
    MenuDto updateMenu(MenuDto menu);

    /**
     * Adds the meals to menu.
     *
     * @param menu
     *            the menu
     */
    void addMealsToMenu(MenuDto menu);

    /**
     * Adds the meal to menu.
     *
     * @param mealDto
     *            the meal dto
     * @param menuDto
     *            the menu dto
     * @return
     */
    MenuDto addMealToMenu(MealDto mealDto, MenuDto menuDto);

    /**
     * Update meal.
     *
     * @param updatedMeal
     *            the updated meal
     */
    void updateMeal(MealDto updatedMeal);

    /**
     * Adds the meal.
     *
     * @param mealDto
     *            the meal dto
     * @return the meal dto
     */
    MealDto addMeal(MealDto mealDto);

}