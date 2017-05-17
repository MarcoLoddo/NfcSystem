package it.extrasys.tesi.tagsystem.meal_service.api;

// TODO: Auto-generated Javadoc
/**
 * The Enum MenuType.
 */
public enum MenuType {

    /** The vegetarian. */
    vegetarian("vegetarian"),
    /** The gluttenfree. */
    gluttenfree("glutten free"),
    /** The generic. */
    generic("generic");

    /** The name. */
    private String name;

    /**
     * Instantiates a new menu type.
     *
     * @param name
     *            the name
     */
    MenuType(String name) {
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
