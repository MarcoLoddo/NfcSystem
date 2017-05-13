package it.extrasys.tesi.tagsystem.meal_service.api;

import java.util.List;

import it.extrasys.tesi.tagsystem.meal_service.db.entity.MealType;

/**
 * The Class MealDto.
 */
public class MealDto {

    private int mealId;

    /** The description. */
    private String description;

    /** The price. */
    private int price;

    private MealType type;

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

    public MealType getType() {
        return this.type;
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

    public void setType(MealType type) {
        this.type = type;
    }
}
