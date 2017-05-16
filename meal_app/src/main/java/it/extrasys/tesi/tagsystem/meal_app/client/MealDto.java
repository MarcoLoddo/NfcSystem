package it.extrasys.tesi.tagsystem.meal_app.client;

import java.util.ArrayList;
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

    private MealType type;

    private List<MenuDto> menus;
    public String getDescription() {
        return this.description;
    }

    public int getMealId() {
        return this.mealId;
    }

    /**
     * Gets the menus.
     *
     * @return the menus
     */
    public List<MenuDto> getMenus() {
        if (this.menus == null) {
            this.menus = new ArrayList<>();
        }
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
