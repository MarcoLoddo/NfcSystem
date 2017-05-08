package it.extrasys.tesi.tagsystem.user_web.ui.components.menubars;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.ui.components.events.CustomLayoutEvents;
import it.extrasys.tesi.tagsystem.user_web.ui.components.form.AddUserForm;

/**
 * The Class CommandMenu.
 */
public class CommandMenu extends CustomLayoutEvents {
    private String userUri;
    private VerticalLayout buttons;
    /**
     * Instantiates a new command menu.
     */
    public CommandMenu(String userUri) {
        this.userUri = userUri;
        initializeUI();
        initializeStyle();

    }

    public VerticalLayout getButtons() {
        return this.buttons;
    }

    private void initializeStyle() {
        setWidthUndefined();
        setHeight("100%");
        addStyleName("bg");
    }

    /**
     * Initialize UI.
     */
    protected void initializeUI() {
        this.buttons = new VerticalLayout();
        Button search = new Button("Search User");
        search.addStyleName(ValoTheme.BUTTON_LINK);
        search.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                fireStartEdit(-1);
            }
        });

        Button addNewUser = new Button("Add new user");
        addNewUser.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                AddUserForm form = new AddUserForm(CommandMenu.this.userUri);
                UI.getCurrent().addWindow(form);
                form.center();
                form.focus();
                fireStartEdit(-1);
            }
        });
        addNewUser.addStyleName(ValoTheme.BUTTON_LINK);

        this.buttons.addComponents(search, addNewUser);
        addComponent(this.buttons);

    }

}
