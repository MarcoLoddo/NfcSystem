package it.extrasys.tesi.tagsystem.user_web.events;

import com.vaadin.ui.VerticalLayout;

/**
 * The Class LoadNewTabEvent.
 */
public class LoadNewTabEvent {

    private VerticalLayout tab;
    private String name;
    /**
     * Instantiates a new load new tab event.
     *
     * @param toAdd
     *            the to add
     */
    public LoadNewTabEvent(VerticalLayout toAdd, String name) {
        this.tab = toAdd;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public VerticalLayout getTab() {
        return this.tab;
    }

}
