package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;

/**
 * The Class MealAddForm.
 */
public class MealAddForm extends Window {

    private VerticalLayout form;
    private Button submit;
    private MealDto data;

    @Override
    public MealDto getData() {
        return this.data;
    }

    /**
     * Instantiates a new meal add form.
     */
    public MealAddForm() {

        this.form = new VerticalLayout();
        this.submit = new Button("Submit");
        this.form.addComponent(this.submit);

        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);

        setContent(this.form);
    }
}
