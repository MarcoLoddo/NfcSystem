package it.extrasys.tesi.tagsystem.meal_app.client;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class MealDto.
 */
public class MealDto {

    private Long mealId;

    /** The description. */
    private String description;

    /** The price. */
    private BigDecimal price;

    private MEALTYPE type;

    private List<MenuDto> menus;

    public Long getMealId() {
        return this.mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MEALTYPE getType() {
        return this.type;
    }

    public void setType(MEALTYPE type) {
        this.type = type;
    }

    public List<MenuDto> getMenus() {
        return this.menus;
    }

    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }
}
