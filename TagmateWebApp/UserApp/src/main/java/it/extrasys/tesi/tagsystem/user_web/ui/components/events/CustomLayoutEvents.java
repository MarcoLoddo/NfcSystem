package it.extrasys.tesi.tagsystem.user_web.ui.components.events;

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
        if (!this.endEditListeners.contains(listener)) {
            this.endEditListeners.add(listener);
        }
    }

    @Override
    public void addStartEditListener(StartEditUserListener listener) {
        if (!this.startEditListeners.contains(listener)) {
            this.startEditListeners.add(listener);
        }

    }
    @Override
    public void fireEndEditUser() {
        if (this.endEditListeners.size() == 0) {
            System.out.println("Evento endedit vuoto!");
        } else {
            for (EndEditUserListener listener : this.endEditListeners) {
                listener.endEditUser();
            }
        }
    }
    @Override
    public void fireStartEdit(int id) {
        if (this.startEditListeners.size() == 0) {
            System.out.println("Evento startedit vuoto!");
        } else {
            for (StartEditUserListener listener : this.startEditListeners) {
                listener.startEdit(id);
            }
        }
    }

}
