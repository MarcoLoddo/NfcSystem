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

/**
 * The Class MenuEntity.
 */
@Entity(name = "Menus")
@Table(name = "menus")
public class MenuEntity {

    /**
     * The Enum MealType.
     */
    public enum MealType {

        /** The pasta. */
        pasta("pasta"),

        /** The soup. */
        soup("soup"),

        /** The salad. */
        salad("salad"),

        /** The fish. */
        fish("fish"),

        /** The cheese. */
        cheese("cheese"),

        /** The dessert. */
        dessert("dessert"),

        /** The drink. */
        drink("drink");

        /** The name. */
        private String name;

        /**
         * Instantiates a new meal type.
         *
         * @param name
         *            the name
         */
        MealType(String name) {
            this.name = name;
        }

        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName() {
            return this.name;
        }

    }

    /** The menu id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    /** The date. */
    private Date date;

    /** The meals. */
    @ManyToMany(mappedBy = "menus")
    private List<MealEntity> meals;

    public Date getDate() {
        return this.date;
    }

    /**
     * Gets the menu id.
     *
     * @return the menu id
     */
    public int getMenuId() {
        return this.menuId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
