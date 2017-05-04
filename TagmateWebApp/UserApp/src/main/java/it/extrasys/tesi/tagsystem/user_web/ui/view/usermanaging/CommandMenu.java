package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.events.CustomLayoutEvents;

/**
 * The Class CommandMenu.
 */
public class CommandMenu extends CustomLayoutEvents {
    private String userUri;
    /**
     * Instantiates a new command menu.
     */
    public CommandMenu(String userUri) {
        this.userUri = userUri;
        initializeUI();
        initializeStyle();

    }

    private void initializeStyle() {
        setWidthUndefined();
        setHeight("100%");
        addStyleName("bg");
    }
    private void initializeUI() {
        VerticalLayout buttons = new VerticalLayout();
        Button search = new Button("Search User");
        search.addStyleName(ValoTheme.BUTTON_LINK);
        search.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                fireStartEdit(-1);
            }
        });

        Button addNewUser = new Button("Add user");
        addNewUser.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

            }
        });
        addNewUser.addStyleName(ValoTheme.BUTTON_LINK);

        buttons.addComponents(search, addNewUser);
        addComponent(buttons);

    }

}
