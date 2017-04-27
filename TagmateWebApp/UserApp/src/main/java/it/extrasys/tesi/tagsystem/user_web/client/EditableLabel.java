package it.extrasys.tesi.tagsystem.user_web.client;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The Class EditableLabel.
 */
public class EditableLabel extends VerticalLayout {
    private Label label;
    private TextField textField;

    /**
     * Instantiates a new editable label.
     *
     * @param value
     *            the value
     */
    public EditableLabel(String value) {

        this.label = new Label(value);
        this.label.addStyleName(ValoTheme.LINK_SMALL);
        this.label.setSizeUndefined();
        this.textField = new TextField();
        addComponent(this.label);
        addListeners();
    }

    private void addListeners() {
        addLayoutClickListener(new LayoutClickListener() {

            @Override
            public void layoutClick(LayoutClickEvent event) {
                if (event.isDoubleClick()
                        && event.getClickedComponent() instanceof Label) {
                    EditableLabel.this.textField
                            .setValue(EditableLabel.this.label.getValue());
                    removeComponent(EditableLabel.this.label);
                    addComponent(EditableLabel.this.textField);
                    EditableLabel.this.textField.focus();
                }
            }
        });
        this.textField.addBlurListener(new BlurListener() {

            @Override
            public void blur(BlurEvent event) {
                EditableLabel.this.label
                        .setValue(EditableLabel.this.textField.getValue());
                removeComponent(EditableLabel.this.textField);
                addComponent(EditableLabel.this.label);
            }
        });
    }

}
