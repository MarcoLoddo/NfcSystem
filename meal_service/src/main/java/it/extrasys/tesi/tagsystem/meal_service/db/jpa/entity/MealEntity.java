package it.extrasys.tesi.tagsystem.meal_service.db.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private Long mealId;

    /** The description. */
    private String description;

    /** The price. */
    private BigDecimal price;

    @ManyToMany
    @JoinColumn(name = "menu_id")
    private List<MenuEntity> menus = new ArrayList<MenuEntity>();
    private MealType type;
    /**
     * Adds the menu.
     *
     * @param menu
     *            the menu
     */
    public void addToMenu(MenuEntity menu) {
        this.menus.add(menu);
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
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

    public Long getMealId() {
        return this.mealId;
    }
    public void setMealId(Long mealId) {
        this.mealId = mealId;

    }

    public List<MenuEntity> getMenus() {
        return this.menus;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MealType getType() {
        return this.type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

}
