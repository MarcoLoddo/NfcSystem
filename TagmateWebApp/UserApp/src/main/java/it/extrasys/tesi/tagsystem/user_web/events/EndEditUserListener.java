package it.extrasys.tesi.tagsystem.user_web.events;

/**
 * The listener interface for receiving endEditUser events. The class that is
 * interested in processing a endEditUser event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addEndEditUserListener</code> method. When the endEditUser
 * event occurs, that object's appropriate method is invoked.
 *
 * @see EndEditUserEvent
 */
public interface EndEditUserListener {

    /**
     * End edit.
     */
    void endEditUser();
}
