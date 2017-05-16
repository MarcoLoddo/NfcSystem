package it.extrasys.tesi.tagsystem.meal_app.ui.components.events;

// TODO: Auto-generated Javadoc
/**
 * The Interface MealUpdatePublisher.
 */
public interface MealUpdatePublisher {

    /**
     * Adds the listener.
     *
     * @param listener
     *            the listener
     */
    void addListener(MealUpdateEventListener listener);

    /**
     * Fire meal update event.
     */
    void fireMealUpdateEvent();
}
