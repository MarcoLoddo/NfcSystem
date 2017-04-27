package it.extra.tesi.tagsystem.user_web.ui.view.usermanaging;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import client.NfcTagDto;
import client.UserDto;

/**
 * The Class UserForm.
 */
public class UserForm extends VerticalLayout {
    private TextField name, email, passwordField;
    private Grid<NfcTagDto> nfc;

    /**
     * Instantiates a new user form.
     */
    public UserForm() {
        this.name = new TextField("Name");
        this.email = new TextField("Email");
        this.passwordField = new TextField("Password");
        this.nfc = new Grid<NfcTagDto>();
        this.nfc.addColumn(NfcTagDto::getNfcId).setCaption("Nfc tag");
        this.nfc.addColumn(NfcTagDto::isDisabled).setCaption("Disabled");

        this.nfc.setCaption("Nfc tag list");
        addComponents(this.name, this.email, this.passwordField, this.nfc);
        Button previousPage = new Button("Back to user search");
        previousPage.addStyleName(ValoTheme.BUTTON_LINK);

        previousPage.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo("UserManaging");
            }
        });
        addComponent(previousPage);
        setComponentAlignment(previousPage, Alignment.BOTTOM_LEFT);
        addStyleName(ValoTheme.LAYOUT_CARD);
        setSizeUndefined();

        setSpacing(true);
        setMargin(new MarginInfo(true, true, true, true));
    }

    /**
     * Initialize method.
     *
     * @param userDto
     *            the user dto
     */
    public void init(UserDto userDto) {
        this.name.setValue(userDto.getName());
        this.email.setValue(userDto.getEmail());
        this.passwordField.setValue(userDto.getPassword());
        this.nfc.setItems(userDto.getNfcTags());
    }
}
