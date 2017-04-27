package it.extrasys.tesi.tagsystem.user_web.events;

/**
 * Interface for the event of an ended editation.
 *
 * @author marco
 *
 */
public interface EndEditEventInterface extends EditEventInterface {

    /**
     * callback method.
     *
     * @param event
     */
    void endEditEvent(EditEvent event);
}
