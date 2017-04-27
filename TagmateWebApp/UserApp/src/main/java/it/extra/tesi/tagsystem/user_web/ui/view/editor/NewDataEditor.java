package it.extra.tesi.tagsystem.user_web.ui.view.editor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import client.NfcTagDto;
import client.UserDto;
import it.extra.tesi.tagsystem.user_web.ui.events.EditEvent;
import it.extra.tesi.tagsystem.user_web.ui.events.EndEditEventInterface;
import it.extra.tesi.tagsystem.user_web.ui.events.StartEditEventInterface;

/**
 * The Class NewDataEditor.
 */
public class NewDataEditor extends VerticalLayout
        implements
            StartEditEventInterface {

    private Button submit, cancel;

    private List<EndEditEventInterface> endEditEventListeners;

    /**
     * Instantiates a new new data editor.
     *
     * @param userDto
     *            the user dto
     * @param userUri
     *            the user uri
     */
    public NewDataEditor(UserDto userDto, String userUri) {
        init();
        VerticalLayout submitContainer = new VerticalLayout();
        TextField id = new TextField();
        id.focus();
        id.setCaption("NFC ID:");
        Button submitRow = new Button("Submit");
        Button cancel = new Button("Cancel");
        submitContainer.addComponents(id, cancel, submitRow);
        addComponent(submitContainer);
        cancel.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                removeComponent(submitContainer);
            }
        });

        submitRow.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                NfcTagDto newNfc = new NfcTagDto();
                newNfc.setNfcId(id.getValue());
                userDto.getNfcTags().add(newNfc);
                RestTemplate restTemplate = new RestTemplate();
                String uri = userUri + "/update";
                UserDto user = restTemplate
                        .postForEntity(uri, userDto, UserDto.class).getBody();
                getSession().setAttribute("selectedUser", null);
            }
        });
    }

    /**
     * Initalizer.
     */
    public void init() {
        this.endEditEventListeners = new ArrayList<>();
        this.setStyleName("background-white");
        setWidthUndefined();
        setHeight("100%");
        this.submit = new Button("Submit");
        this.cancel = new Button("Cancel");
    }
    @Override
    public void startedEdit(EditEvent event) {
        // TODO Auto-generated method stub

    }
}
