package it.extrasys.tesi.tagsystem.meal_service.db.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import it.extrasys.tesi.tagsystem.meal_service.api.MealType;

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
    private Date date;

    private MealType type;

    /** The meals. */
    @ManyToMany(mappedBy = "menus")
    private List<MealEntity> meals;

    public Date getDate() {
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

    public MealType getType() {
        return this.type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMeals(List<MealEntity> meals) {
        this.meals = meals;
    }

    public void setType(MealType type) {
        this.type = type;
    }

}
