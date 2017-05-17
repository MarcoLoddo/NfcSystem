package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

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

    /**
     * Instantiates a new search meal form.
     */
    public SearchMealForm() {
        RestClient restClient = new RestClient();

        this.form = new VerticalLayout();

        Grid<MealDto> grid = new Grid<>(MealDto.class);
        grid.setItems(restClient.getAllMeals());
        grid.setCaption("Meals in db");
        grid.addItemClickListener(new ItemClickListener<MealDto>() {

            @Override
            public void itemClick(ItemClick<MealDto> event) {
                if (event.getMouseEventDetails().isDoubleClick()) {
                    SearchMealForm.this.mealToAdd = event.getItem();
                    close();
                }

            }
        });
        this.form.addComponent(grid);
        setContent(this.form);
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
