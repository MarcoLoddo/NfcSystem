package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import org.springframework.web.client.RestTemplate;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.client.EditableLabel;
import it.extrasys.tesi.tagsystem.user_web.client.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_web.client.UserDto;

/**
 * The Class UserForm.
 */

public class UserForm extends VerticalLayout {
    private EditableLabel name, email, passwordField;
    private Grid<NfcTagDto> nfc;

    private UserDto incomingData;
    private Button submit;
    /**
     * Instantiates a new user form.
     */
    public UserForm(UserDto userDto, String userUri) {
        this.incomingData = userDto;

        this.name = new EditableLabel(userDto.getName());
        this.name.setCaption("Name:");

        this.email = new EditableLabel(userDto.getEmail());
        this.email.setCaption("Email:");

        this.passwordField = new EditableLabel(userDto.getPassword());
        this.passwordField.setCaption("Password:");

        this.nfc = new Grid<NfcTagDto>();

        addComponents(this.name, this.email, this.passwordField, this.nfc);
        this.nfc.setItems(userDto.getNfcTags());
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
        setSpacing(false);
        setSubmit(userUri);
    }
    private void setSubmit(String userUri) {
        this.submit = new Button("Submit");
        addComponent(this.submit);
        setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UserForm.this.incomingData
                        .setName(UserForm.this.name.getValue());
                UserForm.this.incomingData
                        .setEmail(UserForm.this.email.getValue());
                UserForm.this.incomingData
                        .setPassword(UserForm.this.passwordField.getValue());

                RestTemplate restTemplate = new RestTemplate();
                String uri = userUri + "/update";
                UserDto response = restTemplate.postForEntity(uri,
                        UserForm.this.incomingData, UserDto.class).getBody();
                if (response != null) {
                    Notification notification = new Notification(
                            "Update successful!",
                            Notification.TYPE_TRAY_NOTIFICATION);
                    notification.setPosition(Position.BOTTOM_RIGHT);
                    notification.show(Page.getCurrent());
                } else {
                    Notification notification = new Notification(
                            "Update failed", Notification.TYPE_WARNING_MESSAGE);
                    notification.setPosition(Position.BOTTOM_RIGHT);
                    notification.show(Page.getCurrent());
                }
            }
        });
    }
}
