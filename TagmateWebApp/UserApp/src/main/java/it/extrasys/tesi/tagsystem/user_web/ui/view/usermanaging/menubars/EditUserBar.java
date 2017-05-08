package it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging.menubars;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.user_web.client.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_web.client.UserDto;
import it.extrasys.tesi.tagsystem.user_web.form.AddNfcForm;

/**
 * The Class EditUserBar.
 */
public class EditUserBar extends CommandMenu {

    private String userUri;
    private Button addNfc;
    /**
     * Instantiates a new edits the user bar.
     *
     * @param userUri
     *            the user uri
     */
    public EditUserBar(String userUri) {
        super(userUri);
        this.userUri = userUri;
        this.addNfc = new Button("Add nfc");
        this.addNfc.addStyleName(ValoTheme.BUTTON_LINK);
        getButtons().addComponent(this.addNfc);
    }

    /**
     * Initialize.
     *
     * @param id
     *            the id
     */
    public void initialize(int id) {
        initializeUI(id);
    }

    /**
     * Initialize UI.
     *
     * @param id
     *            the id
     */
    protected void initializeUI(int id) {

        this.addNfc.getListeners(ClickEvent.class)
                .forEach(listener -> this.addNfc
                        .removeListener(ClickEvent.class, listener));
        this.addNfc.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                AddNfcForm form = new AddNfcForm(id);
                getUI().addWindow(form);
                form.center();
                form.focus();
                form.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        NfcTagDto tagDto = form.getNewNfc();
                        sendNewNfc(id, tagDto);
                        fireStartEdit(id);
                    }
                });

            }
        });

    }
    private UserDto sendNewNfc(Integer id, NfcTagDto nfcTagDto) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        String uri = this.userUri + "/{id}/nfc/add";

        return restTemplate.postForEntity(uri, nfcTagDto, UserDto.class, map)
                .getBody();
    }

}
