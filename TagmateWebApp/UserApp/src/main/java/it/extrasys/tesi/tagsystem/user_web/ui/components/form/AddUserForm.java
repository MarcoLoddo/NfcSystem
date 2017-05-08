package it.extrasys.tesi.tagsystem.user_web.ui.components.form;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.user_web.client.UserDto;

/**
 * The Class AddUserForm.
 */
public class AddUserForm extends Window {
    private VerticalLayout form;
    private Button submit;

    /**
     * Instantiates a new adds the user form.
     */
    public AddUserForm(String userUri) {
        this.form = new VerticalLayout();
        TextField name, lastname, email;
        name = new TextField();
        name.setCaption("Name");
        email = new TextField();
        email.setCaption("Email");
        TextField passwordField = new TextField();
        passwordField.setCaption("Password");
        this.form.addComponents(name, passwordField, email);
        this.submit = new Button("Submit");
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UserDto userDto = new UserDto();
                userDto.setName(name.getValue());
                userDto.setEmail(email.getValue());
                userDto.setPassword(passwordField.getValue());
                RestTemplate restTemplate = new RestTemplate();

                String uri = userUri + "/add";
                restTemplate.postForEntity(uri, userDto, null);
                close();
            }
        });
        this.form.addComponent(this.submit);
        setContent(this.form);
        setCaption("Adding new user...");
    }
}
