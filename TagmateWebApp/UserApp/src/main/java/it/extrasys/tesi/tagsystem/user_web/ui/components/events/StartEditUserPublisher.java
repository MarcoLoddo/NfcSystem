package it.extrasys.tesi.tagsystem.user_web.ui.components.events;

/**
 * The Interface StartEditUserPublisher.
 */
public interface StartEditUserPublisher {

    /**
     * Adds the start edit listener.
     *
     * @param listener
     *            the listener
     */
    void addStartEditListener(StartEditUserListener listener);

    /**
     * Fire edit.
     * @param id TODO
     */
    void fireStartEdit(int id);

}
