package it.extrasys.tesi.tagsystem.meal_service.api;

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
