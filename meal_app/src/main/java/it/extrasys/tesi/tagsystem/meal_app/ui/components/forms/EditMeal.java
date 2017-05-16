package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;
import it.extrasys.tesi.tagsystem.meal_app.client.MealType;

/**
 * The Class EditMeal.
 */
public class EditMeal extends Window {

    /** The form. */
    private VerticalLayout form;

    /** The submit. */
    private Button submit;

    private MealDto updatedMeal;
    /**
     * Instantiates a new edits the meal.
     *
     * @param meal
     *            the meal
     */
    public EditMeal(MealDto meal) {
        this.form = new VerticalLayout();

        setCaption("Edit meal...");

        setContent(this.form);

        TextField description = new TextField();
        description.setValue(meal.getDescription());
        TextField price = new TextField();
        price.setValue(Integer.toString(meal.getPrice()));
        price.setSizeUndefined();

        price.addValueChangeListener(new ValueChangeListener<String>() {

            @Override
            public void valueChange(ValueChangeEvent<String> event) {
                try {
                    Integer.parseInt(event.getValue());
                } catch (Exception e) {
                    price.setValue(event.getOldValue());
                }
            }
        });
        ComboBox<MealType> comboBox = new ComboBox<>();
        comboBox.setItems(MealType.values());
        comboBox.setValue(meal.getType());
        this.submit = new Button("Submit");
        this.form.addComponents(description, price, comboBox, this.submit);
        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                EditMeal.this.updatedMeal = new MealDto();
                EditMeal.this.updatedMeal.setMealId(meal.getMealId());

                EditMeal.this.updatedMeal
                        .setDescription(description.getValue());
                EditMeal.this.updatedMeal
                        .setPrice(Integer.parseInt(price.getValue()));
                EditMeal.this.updatedMeal.setType(comboBox.getValue());
                close();
            }
        });
    }
    public MealDto getUpdatedMeal() {
        return this.updatedMeal;
    }

}
