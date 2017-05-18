package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;
import it.extrasys.tesi.tagsystem.meal_app.client.MenuDto;
import it.extrasys.tesi.tagsystem.meal_app.client.Messages;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuCompilationForm.
 */
public class MenuCompilationForm extends Window {

    /** The form. */
    private VerticalLayout form;

    /** The submit. */
    private Button submit;

    public Button getSubmit() {
        return this.submit;
    }

    /** The data. */
    private MenuDto data;

    /** The meals in grid. */
    private List<MealDto> mealsInGrid;

    /** The meals. */
    private Grid<MealDto> meals;

    /** The date field. */
    private DateField dateField;

    /** The type. */
    private TextField type;

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.ui.AbstractComponent#getData()
     */
    @Override
    public MenuDto getData() {
        this.data.setMeals(this.mealsInGrid);
        this.data.setType(this.type.getValue());
        Date date = new Date();

        this.data.setDate(Date.from(this.dateField.getValue()
                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return this.data;
    }

    /**
     * Gets the submit button.
     *
     * @return the submit button
     */
    public Button getSubmitButton() {
        return this.submit;
    }

    /**
     * Instantiates a new menu compilation form.
     */
    public MenuCompilationForm() {
        this.data = new MenuDto();
        this.meals = new Grid<MealDto>(MealDto.class);
        this.type = new TextField("Type");
        this.mealsInGrid = new ArrayList<>();
        this.meals.setItems(this.mealsInGrid);

        this.dateField = new DateField();
        this.dateField.setValue(LocalDate.now());
        this.dateField.setDateFormat(Messages.get("date.format"));

        Button addMeal = new Button("Add meal");
        addMeal.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                SearchMealForm mealAddForm = new SearchMealForm(
                        MenuCompilationForm.this.mealsInGrid);
                UI.getCurrent().addWindow(mealAddForm);
                mealAddForm.setModal(true);
                mealAddForm.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (mealAddForm.getData() != null) {
                            MenuCompilationForm.this.mealsInGrid
                                    .add(mealAddForm.getData());
                            MenuCompilationForm.this.meals.getDataProvider()
                                    .refreshAll();

                        }
                        UI.getCurrent().removeWindow(mealAddForm);
                    }
                });
            }
        });
        this.submit = new Button("Submit");
        this.form = new VerticalLayout();
        this.form.addComponents(this.type, this.meals, addMeal, this.dateField,
                this.submit);
        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        setContent(this.form);
    }

    /**
     * Instantiates a new menu compilation form.
     *
     * @param menu
     *            the menu
     */
    public MenuCompilationForm(MenuDto menu) {
        this();
        this.dateField.setValue(menu.getDate().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate());
        this.dateField.setEnabled(false);
        this.type.setValue(menu.getType());
        this.mealsInGrid.addAll(menu.getMeals());
        this.meals.getDataProvider().refreshAll();
        this.data.setMenuId(menu.getMenuId());

    }

}
