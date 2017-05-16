package it.extrasys.tesi.tagsystem.meal_app.ui.components.events;

/**
 * The listener interface for receiving mealUpdateEvent events. The class that
 * is interested in processing a mealUpdateEvent event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's <code>addMealUpdateEventListener</code>
 * method. When the mealUpdateEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MealUpdateEventEvent
 */
public interface MealUpdateEventListener {

    /**
     * Update.
     */
    void update();

}
