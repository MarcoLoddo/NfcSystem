package it.extra.tesi.tagsystem.user_web.ui.view.editor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import client.NfcTagDto;
import client.UserDto;
import it.extra.tesi.tagsystem.user_web.ui.events.EditEvent;
import it.extra.tesi.tagsystem.user_web.ui.events.EndEditEventInterface;
import it.extra.tesi.tagsystem.user_web.ui.events.StartEditEventInterface;

/**
 * Father class for data editing user interfaces.
 *
 * @author marco
 *
 */
public class DataEditor extends VerticalLayout
        implements
            StartEditEventInterface {

    private Button submit, cancel;
    private CheckBox disable;
    private TextField id;

    private TextField name;

    private TextField email;
    private TextField password;

    private List<EndEditEventInterface> endEditEventListeners;

    private EditEvent startEventData;
    /**
     * Instantiates a new data editor.
     */
    public DataEditor() {
        init();
    }

    /**
     * Constructor for nfc edit.
     *
     * @param user
     * @param nfc
     * @param userUri
     */
    public DataEditor(UserDto user, NfcTagDto nfc, String userUri) {
        init();
        nfcEditor(nfc, user, userUri);
    }
    /**
     * Constructor for user edit.
     *
     * @param user
     * @param userUri
     */
    public DataEditor(UserDto user, String userUri) {
        init();
        userEditor(user, userUri);
    }
    /**
     * End editing method subscriber.
     */
    public void addEndEditListener(EndEditEventInterface listener) {

        this.endEditEventListeners.add(listener);
    }
    /**
     * Fire event to start editing.
     *
     * @param userSelected
     */
    private void fireEndEdit(UserDto update) {
        this.startEventData.setUserDto(update);
        for (EndEditEventInterface listener : this.endEditEventListeners) {
            listener.endEditEvent(this.startEventData);
        }
    }

    public Button getCancel() {
        return this.cancel;
    }
    public Button getSubmit() {
        return this.submit;
    }
    /**
     * Base constructor.
     */
    public void init() {
        this.endEditEventListeners = new ArrayList<>();
        this.setStyleName("background-white");
        setWidthUndefined();
        setHeight("100%");
        this.submit = new Button("Submit");
        this.cancel = new Button("Cancel");

        HorizontalLayout buttons = new HorizontalLayout();

        buttons.addComponents(this.cancel, this.submit);
        addComponent(buttons);
        getCancel().addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                fireEndEdit(null);
                DataEditor.this.setVisible(false);
            }
        });
    }
    /**
     * nfc editor UI initializer.
     *
     * @param nfc
     * @param user
     * @param userUri
     */
    private void nfcEditor(NfcTagDto nfc, UserDto user, String userUri) {

        HorizontalLayout form = new HorizontalLayout();
        this.id = new TextField();
        this.id.focus();
        this.disable = new CheckBox();
        this.id.setCaption("Set new id");
        this.id.setValue(nfc.getNfcId());

        this.disable.setCaption("Disable status");
        this.disable.setValue(nfc.isDisabled());

        getSubmit().addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

                nfc.setDisabled(DataEditor.this.disable.getValue());
                nfc.setNfcId(DataEditor.this.id.getValue());

                RestTemplate restTemplate = new RestTemplate();
                String uri = userUri + "/update";
                UserDto response = restTemplate
                        .postForEntity(uri, user, UserDto.class).getBody();
                DataEditor.this.setVisible(false);
                fireEndEdit(response);
            }
        });
        form.addComponents(this.id, this.disable);
        addComponent(form);

    }
    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }
    public void setSubmit(Button submit) {
        this.submit = submit;
    }
    @Override
    public void startedEdit(EditEvent event) {
        setVisible(true);
        this.startEventData = event;
    }
    /**
     * user editor UI initializer.
     *
     * @param user
     * @param userUri
     */
    public void userEditor(UserDto user, String userUri) {
        HorizontalLayout form = new HorizontalLayout();
        this.name = new TextField();
        this.name.setValue(user.getName());
        this.name.setCaption("Name");
        this.email = new TextField();
        this.email.setCaption("Email");
        this.email.setValue(user.getEmail());
        this.password = new TextField();
        this.password.setCaption("Password");
        this.password.setValue(user.getPassword());

        form.addComponents(this.name, this.email, this.password);
        addComponent(form);
        getSubmit().addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                user.setEmail(DataEditor.this.email.getValue());
                user.setName(DataEditor.this.name.getValue());
                user.setPassword(DataEditor.this.password.getValue());

                RestTemplate restTemplate = new RestTemplate();
                String uri = userUri + "/update";
                UserDto response = restTemplate
                        .postForEntity(uri, user, UserDto.class).getBody();
                DataEditor.this.setVisible(false);
                fireEndEdit(response);
            }
        });

    }

}
