package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.ItemClickListener;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;
import it.extrasys.tesi.tagsystem.meal_app.client.RestClient;

/**
 * The Class SearchMealForm.
 */
public class SearchMealForm extends Window {

    /** The meal to add. */
    private MealDto mealToAdd;
    private VerticalLayout form;
    private List<MealDto> meals;
    private Grid<MealDto> grid;
    /**
     * Instantiates a new search meal form.
     */
    public SearchMealForm() {
        RestClient restClient = new RestClient();
        this.meals = new ArrayList<>();
        this.meals.addAll(Arrays.asList(restClient.getAllMeals()));
        this.form = new VerticalLayout();

        this.grid = new Grid<>(MealDto.class);
        this.grid.setItems(this.meals);
        this.grid.setCaption("Meals in db");
        this.grid.addItemClickListener(new ItemClickListener<MealDto>() {

            @Override
            public void itemClick(ItemClick<MealDto> event) {
                if (event.getMouseEventDetails().isDoubleClick()) {
                    SearchMealForm.this.mealToAdd = event.getItem();
                    close();
                }

            }
        });
        this.form.addComponent(this.grid);
        setContent(this.form);
    }

    /**
     * Instantiates a new search meal form.
     *
     * @param mealPresent
     *            the meal present
     */
    public SearchMealForm(List<MealDto> mealPresent) {
        this();

        mealPresent.forEach(meal -> this.meals
                .removeIf(meal2 -> meal.getMealId() == meal2.getMealId()));
        this.grid.getDataProvider().refreshAll();
    }
    /**
     * Gets the meal to add.
     *
     * @return the meal to add
     */
    @Override
    public MealDto getData() {
        return this.mealToAdd;
    }

}
