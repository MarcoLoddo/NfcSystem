package it.extra.tesi.tagsystem.user_web.ui.events;

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
