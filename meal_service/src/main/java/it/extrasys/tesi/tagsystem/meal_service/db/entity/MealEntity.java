package it.extrasys.tesi.tagsystem.meal_service.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class MealEntity.
 */
@Entity(name = "Meals")
@Table(name = "meals")
public class MealEntity {

    /** The meal id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private int mealId;

    /** The description. */
    private String description;

    /** The price. */
    private int price;

    @ManyToMany
    @JoinColumn(name = "menu_id")
    private List<MenuEntity> menus;
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the meal id.
     *
     * @return the meal id
     */
    public int getMealId() {
        return this.mealId;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the price.
     *
     * @param price
     *            the new price
     */
    public void setPrice(int price) {
        this.price = price;
    }

}
