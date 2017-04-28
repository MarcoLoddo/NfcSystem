package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

import it.extrasys.tesi.tagsystem.user_web.events.LoadNewTabEvent;
import it.extrasys.tesi.tagsystem.user_web.events.LoadNewTabEventInterface;

/**
 * The Class UserForm.
 */
public class UserView extends HorizontalLayout
        implements
            View,
            LoadNewTabEventInterface {

    private CommandMenu menu;
    private TabSheet tabSheet;

    /**
     * Instantiates a new user form.
     *
     * @param userUri
     *            the user uri
     */
    public UserView(String userUri) {
        setSizeFull();
        this.menu = new CommandMenu(userUri);

        this.tabSheet = new TabSheet();
        this.tabSheet.setSizeUndefined();
        addComponents(this.menu, this.tabSheet);

        setComponentAlignment(this.tabSheet, Alignment.MIDDLE_CENTER);
        setComponentAlignment(this.menu, Alignment.MIDDLE_LEFT);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.menu.addListener(this);

    }

    @Override
    public void loadNewTab(LoadNewTabEvent event) {
        this.tabSheet.addTab(event.getTab(), event.getName());
    }

}
