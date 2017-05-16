package it.extrasys.tesi.tagsystem.meal_app.client;

import java.util.List;

/**
 * The Class MenuDto.
 */
public class MenuDto {

    private int menuId;

    private String type;

    private List<MealDto> meals;
    private String date;
    public String getDate() {
        return this.date;
    }
    public List<MealDto> getMeals() {
        return this.meals;
    }
    public int getMenuId() {
        return this.menuId;
    }

    public String getType() {
        return this.type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public void setType(String type) {
        this.type = type;
    }
}
