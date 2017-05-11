package it.extrasys.tesi.tagsystem.meal_service.api;

import java.util.List;

/**
 * The Class MealDto.
 */
public class MealDto {

    private int mealId;

    /** The description. */
    private String description;

    /** The price. */
    private int price;

    private List<MenuDto> menus;

    public String getDescription() {
        return this.description;
    }

    public int getMealId() {
        return this.mealId;
    }

    public List<MenuDto> getMenus() {
        return this.menus;
    }

    public int getPrice() {
        return this.price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
