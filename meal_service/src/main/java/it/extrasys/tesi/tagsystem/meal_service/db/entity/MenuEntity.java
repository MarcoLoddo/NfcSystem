package it.extrasys.tesi.tagsystem.meal_service.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Class MenuEntity.
 */
@Entity(name = "Menus")
@Table(name = "menus")
public class MenuEntity {

    /** The menu id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    /** The date. */
    private String date;

    private String type;

    /** The meals. */
    @ManyToMany(mappedBy = "menus")
    private List<MealEntity> meals;

    public String getDate() {
        return this.date;
    }

    public List<MealEntity> getMeals() {
        return this.meals;
    }

    /**
     * Gets the menu id.
     *
     * @return the menu id
     */
    public int getMenuId() {
        return this.menuId;
    }

    public String getType() {
        return this.type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMeals(List<MealEntity> mealEntityList) {
        this.meals = mealEntityList;
    }

    public void setType(String menuType) {
        this.type = menuType;
    }

}
