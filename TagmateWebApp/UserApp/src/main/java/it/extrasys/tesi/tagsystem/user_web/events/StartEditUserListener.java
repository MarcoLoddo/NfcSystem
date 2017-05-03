package it.extrasys.tesi.tagsystem.user_web.events;

/**
 * The listener interface for receiving startEditUser events. The class that is
 * interested in processing a startEditUser event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStartEditUserListener</code> method. When the
 * startEditUser event occurs, that object's appropriate method is invoked.
 *
 * @see StartEditUserEvent
 */
public interface StartEditUserListener {

    /**
     * Start edit.
     * @param id TODO
     */
    void startEdit(int id);
}
