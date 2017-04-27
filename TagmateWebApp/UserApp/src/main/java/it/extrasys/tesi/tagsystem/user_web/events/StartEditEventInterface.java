package it.extrasys.tesi.tagsystem.user_web.events;

/**
 * Interface for the event of a started editation.
 *
 * @author marco
 *
 */
public interface StartEditEventInterface extends EditEventInterface {

    /**
     * callback method.
     *
     * @param event
     */
    void startedEdit(EditEvent event);
}
