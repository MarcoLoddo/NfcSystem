package it.extrasys.tesi.tagsystem.user_web.ui.components.form;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.user_web.client.NfcTagDto;

/**
 * The Class AddNfcForm.
 */
public class AddNfcForm extends Window {
    private VerticalLayout form;
    private Button submit;
    private NfcTagDto newNfc;

    /**
     * Instantiates a new form to add a nfc.
     *
     * @param id
     *            the user id
     */
    public AddNfcForm(int id) {
        this.newNfc = new NfcTagDto();
        this.form = new VerticalLayout();
        setContent(this.form);
        TextField tag = new TextField();
        tag.setCaption("Nfc tag");
        this.form.addComponent(tag);
        this.submit = new Button("Submit");
        this.form.addComponent(this.submit);
        this.form.setSpacing(true);
        this.form.setMargin(new MarginInfo(true, true, true, true));
        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

                AddNfcForm.this.newNfc.setNfcId(tag.getValue());
                close();

            }
        });
    }

    public NfcTagDto getNewNfc() {
        return this.newNfc;
    }

}
