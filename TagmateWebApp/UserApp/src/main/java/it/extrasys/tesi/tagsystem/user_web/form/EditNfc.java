package it.extrasys.tesi.tagsystem.user_web.form;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.user_web.client.NfcTagDto;

/**
 * The Class EditNfc.
 */
public class EditNfc extends Window {

    private VerticalLayout form;
    private Button submit;
    private NfcTagDto oldNfc, newNfc;
    /**
     * Instantiates a new edits the nfc.
     *
     * @param nfcTagDto
     *            the nfc tag dto
     */
    public EditNfc(NfcTagDto nfcTagDto) {
        super("Edit Nfc");
        this.form = new VerticalLayout();
        this.oldNfc = new NfcTagDto();
        this.oldNfc.setNfcId(nfcTagDto.getNfcId());
        this.oldNfc.setDisabled(nfcTagDto.isDisabled());
        this.newNfc = new NfcTagDto();
        TextField id = new TextField();
        id.setCaption("Nfc tag");
        CheckBox disabled = new CheckBox();
        this.form.addComponents(id, disabled);
        id.setValue(nfcTagDto.getNfcId());
        disabled.setValue(nfcTagDto.isDisabled());
        disabled.setCaption("Disabled");
        this.submit = new Button("Submit");
        this.form.addComponent(this.submit);
        this.form.setSpacing(true);
        this.form.setMargin(new MarginInfo(true, true, true, true));
        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                nfcTagDto.setNfcId(id.getValue());
                nfcTagDto.setDisabled(disabled.getValue());
                EditNfc.this.newNfc.setNfcId(id.getValue());
                EditNfc.this.newNfc.setDisabled(disabled.getValue());
                close();

            }
        });
        setContent(this.form);

    }
    public NfcTagDto getNewNfc() {
        return this.newNfc;
    }
    public NfcTagDto getOldNfc() {
        return this.oldNfc;
    }

}
