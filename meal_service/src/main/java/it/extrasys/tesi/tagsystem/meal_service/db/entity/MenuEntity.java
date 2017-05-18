package it.extrasys.tesi.tagsystem.meal_service.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private Long menuId;

    /** The date. */
    @Temporal(value = TemporalType.DATE)
    private Date date;

    private String type;

    /** The meals. */
    @ManyToMany(mappedBy = "menus")
    private List<MealEntity> meals = new ArrayList<MealEntity>();

    /**
     * Gets the meals.
     *
     * @return the meals
     */
    public List<MealEntity> getMeals() {
        return this.meals;
    }

    public Long getMenuId() {
        return this.menuId;
    }

    public String getType() {
        return this.type;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setType(String menuType) {
        this.type = menuType;
    }

}
