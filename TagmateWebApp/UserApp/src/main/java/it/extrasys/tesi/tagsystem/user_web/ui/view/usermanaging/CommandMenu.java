package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.events.LoadNewTabEvent;
import it.extrasys.tesi.tagsystem.user_web.events.LoadNewTabEventInterface;
import it.extrasys.tesi.tagsystem.user_web.events.LoadNewTabInterfacePublisher;

/**
 * The Class CommandMenu.
 */
public class CommandMenu extends VerticalLayout
        implements
            LoadNewTabInterfacePublisher {
    private List<LoadNewTabEventInterface> newTabListeners;
    private String userUri;
    /**
     * Instantiates a new command menu.
     */
    public CommandMenu(String userUri) {
        this.userUri = userUri;
        this.newTabListeners = new ArrayList<>();
        initializeUI();
        initializeStyle();

    }
    @Override
    public void addListener(LoadNewTabEventInterface caller) {
        this.newTabListeners.add(caller);

    }
    private void fireNewTab(VerticalLayout tab, String name) {
        for (LoadNewTabEventInterface loader : this.newTabListeners) {
            loader.loadNewTab(new LoadNewTabEvent(tab, name));
        }
    }
    private void initializeStyle() {
        setWidthUndefined();
        setHeight("100%");
        addStyleName("bg");
    }
    private void initializeUI() {
        Button search = new Button("Search User");
        search.addStyleName(ValoTheme.BUTTON_LINK);
        search.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                fireNewTab(new UserManaging(CommandMenu.this.userUri),
                        "User Search");
            }
        });
        addComponents(search);
    }

}
