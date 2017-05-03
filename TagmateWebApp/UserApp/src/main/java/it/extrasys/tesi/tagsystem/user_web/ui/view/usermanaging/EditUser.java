package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.client.EditableGrid;
import it.extrasys.tesi.tagsystem.user_web.client.EditableLabel;
import it.extrasys.tesi.tagsystem.user_web.client.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_web.client.UserDto;
import it.extrasys.tesi.tagsystem.user_web.ui.view.MenuBarPage;

/**
 * The Class UserForm.
 */

public class EditUser extends MenuBarPage implements View {

    /** The name. */
    private String pageName = "EditUser";
    private EditableLabel name, email, passwordField;

    private EditableGrid<NfcTagDto> nfc;
    private VerticalLayout verticalLayout;
    private UserDto incomingData;

    private Button submit;
    private String userUri;
    /**
     * Instantiates a new user form.
     */
    public EditUser(String userUri) {

        super(userUri);
        this.verticalLayout = new VerticalLayout();
        this.userUri = userUri;
        this.verticalLayout.addStyleName(ValoTheme.LAYOUT_CARD);
        this.verticalLayout.setSizeUndefined();
        this.verticalLayout.setSpacing(false);
        addComponent(this.verticalLayout);
        setComponentAlignment(this.verticalLayout, Alignment.TOP_CENTER);

    }
    @Override
    public void enter(ViewChangeEvent event) {

    }
    public String getPageName() {
        return this.pageName;
    }

    /**
     * Initializer.
     *
     * @param id
     *            the id
     */
    public void init(int id) {
        this.verticalLayout.removeAllComponents();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        String uri = this.userUri + "/{id}/findById";

        this.incomingData = restTemplate.getForEntity(uri, UserDto.class, map)
                .getBody();

        this.name = new EditableLabel(this.incomingData.getName());
        this.name.setCaption("Name:");

        this.email = new EditableLabel(this.incomingData.getEmail());
        this.email.setCaption("Email:");

        this.passwordField = new EditableLabel(this.incomingData.getPassword());
        this.passwordField.setCaption("Password:");

        this.nfc = new EditableGrid<NfcTagDto>(NfcTagDto.class);

        this.verticalLayout.addComponents(this.name, this.email,
                this.passwordField, this.nfc);
        this.nfc.setItems(this.incomingData.getNfcTags());

        this.nfc.setCaption("Nfc tag list");
        this.verticalLayout.addComponents(this.name, this.email,
                this.passwordField, this.nfc);
        Button previousPage = new Button("Back to user search");
        previousPage.addStyleName(ValoTheme.BUTTON_LINK);

        previousPage.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

                fireEndEditUser();
            }
        });
        this.verticalLayout.addComponent(previousPage);
        this.verticalLayout.setComponentAlignment(previousPage,
                Alignment.BOTTOM_LEFT);
        setSubmit();
    }

    private void setSubmit() {
        this.submit = new Button("Submit");
        this.verticalLayout.addComponent(this.submit);
        this.verticalLayout.setComponentAlignment(this.submit,
                Alignment.BOTTOM_RIGHT);
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                EditUser.this.incomingData
                        .setName(EditUser.this.name.getValue());
                EditUser.this.incomingData
                        .setEmail(EditUser.this.email.getValue());
                EditUser.this.incomingData
                        .setPassword(EditUser.this.passwordField.getValue());

                RestTemplate restTemplate = new RestTemplate();
                String uri = EditUser.this.userUri + "/update";
                try {
                    UserDto response = restTemplate.postForEntity(uri,
                            EditUser.this.incomingData, UserDto.class)
                            .getBody();
                    if (response != null) {
                        Notification notification = new Notification(
                                "Update successful!",
                                Notification.TYPE_TRAY_NOTIFICATION);
                        notification.setPosition(Position.BOTTOM_RIGHT);
                        notification.show(Page.getCurrent());
                    } else {
                        Notification notification = new Notification(
                                "Update failed",
                                Notification.TYPE_WARNING_MESSAGE);
                        notification.setPosition(Position.BOTTOM_RIGHT);
                        notification.show(Page.getCurrent());
                    }
                } catch (Exception e) {
                    Notification notification = new Notification(
                            "Update failed", Notification.TYPE_WARNING_MESSAGE);
                    notification.setPosition(Position.BOTTOM_RIGHT);
                    notification.show(Page.getCurrent());
                }
            }
        });
    }
}
