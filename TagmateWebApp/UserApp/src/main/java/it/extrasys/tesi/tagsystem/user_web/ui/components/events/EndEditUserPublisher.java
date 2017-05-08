package it.extrasys.tesi.tagsystem.user_web.ui.components.events;

/**
 * The Interface EndEditUserPublisher.
 */
public interface EndEditUserPublisher {

    /**
     * Adds the end edit listener.
     *
     * @param listener
     *            the listener
     */
    void addEndEditListener(EndEditUserListener listener);

    /**
     * Fire end edit.
     */
    void fireEndEditUser();

}
