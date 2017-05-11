package it.extrasys.tesi.tagsystem.meal_service.api;

import java.sql.Date;
import java.util.List;

/**
 * The Class MenuDto.
 */
public class MenuDto {

    private int menuId;

    private MealType type;

    private List<MealDto> meals;
    private Date date;
    public Date getDate() {
        return this.date;
    }
    public List<MealDto> getMeals() {
        return this.meals;
    }
    public int getMenuId() {
        return this.menuId;
    }

    public MealType getType() {
        return this.type;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public void setType(MealType type) {
        this.type = type;
    }
}
