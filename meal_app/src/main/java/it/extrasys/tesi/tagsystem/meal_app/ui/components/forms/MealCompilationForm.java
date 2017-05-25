package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.math.BigDecimal;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.meal_app.client.MealType;
import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;;

// TODO: Auto-generated Javadoc
/**
 * The Class MealCompilationForm.
 */
public class MealCompilationForm extends Window {

    /** The form. */
    private VerticalLayout form;

    /** The submit. */
    private Button submit;

    private TextField description, price;
    private ComboBox<MealType> type;
    /**
     * Gets the submit.
     *
     * @return the submit
     */
    public Button getSubmitButton() {
        return this.submit;
    }

    /** The meal dto. */
    private MealDto mealDto;

    public MealDto getMealDto() {
        return this.mealDto;
    }

    /**
     * Sets the data.
     */
    public void setData() {

        this.mealDto.setDescription(this.description.getValue());
        this.mealDto.setPrice(new BigDecimal(this.price.getValue()));
        this.mealDto.setType(this.type.getValue());
    }

    /**
     * Instantiates a new meal compilation form.
     */
    public MealCompilationForm() {
        setCaption("Add meal...");
        this.form = new VerticalLayout();
        this.mealDto = new MealDto();
        this.description = new TextField();
        this.price = new TextField();
        this.price.setSizeUndefined();

        this.price.addValueChangeListener(new ValueChangeListener<String>() {

            @Override
            public void valueChange(ValueChangeEvent<String> event) {
                try {
                    new BigDecimal(event.getValue());
                } catch (Exception e) {
                    MealCompilationForm.this.price
                            .setValue(event.getOldValue());
                }
            }
        });
        this.type = new ComboBox<>();
        this.type.setItems(MealType.values());
        this.submit = new Button("Submit");
        this.form.addComponents(this.description, this.price, this.type,
                this.submit);
        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        setContent(this.form);
    }
    /**
     * Instantiates a new meal compilation form.
     *
     * @param meal
     *            the meal
     */
    public MealCompilationForm(MealDto meal) {
        this();
        setCaption("Edit meal...");
        this.description.setValue(meal.getDescription());
        this.type.setValue(meal.getType());
        this.price.setValue(meal.getPrice().toString());
        this.mealDto.setMealId(meal.getMealId());

    }

}
