package it.extrasys.tesi.tagsystem.meal_service.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class MenuDto.
 */
public class MenuDto {

    private Long menuId;

    private String type;

    private List<MealDto> meals = new ArrayList<>();
    private Date date;

    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Long getMenuId() {
        return this.menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public List<MealDto> getMeals() {
        return this.meals;
    }
    public void setMeals(List<MealDto> meals) {
        this.meals = meals;
    }

}
