package it.extrasys.tesi.tagsystem.user_web.events;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.HorizontalLayout;

/**
 * The Class CustomLayoutGenerative.
 */
public class CustomLayoutEvents extends HorizontalLayout
        implements
            StartEditUserPublisher,
            EndEditUserPublisher {

    private List<StartEditUserListener> startEditListeners;
    private List<EndEditUserListener> endEditListeners;
    /**
     * Instantiates a new custom layout events.
     */
    public CustomLayoutEvents() {
        setSizeFull();
        this.startEditListeners = new ArrayList<>();
        this.endEditListeners = new ArrayList<>();
    }
    @Override
    public void addEndEditListener(EndEditUserListener listener) {
        this.endEditListeners.add(listener);
    }

    @Override
    public void addStartEditListener(StartEditUserListener listener) {
        this.startEditListeners.add(listener);

    }
    @Override
    public void fireEndEditUser() {
        for (EndEditUserListener listener : this.endEditListeners) {
            listener.endEditUser();
        }
    }
    @Override
    public void fireStartEdit(int id) {
        for (StartEditUserListener listener : this.startEditListeners) {
            listener.startEdit(id);
        }

    }

}
