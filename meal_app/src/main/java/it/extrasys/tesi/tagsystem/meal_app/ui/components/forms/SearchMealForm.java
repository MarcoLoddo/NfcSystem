package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.util.List;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.ItemClickListener;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;

/**
 * The Class SearchMealForm.
 */
public class SearchMealForm extends Window {

    /** The meal to add. */
    private MealDto mealToAdd;
    private VerticalLayout form;

    /**
     * Instantiates a new search meal form.
     *
     * @param list
     *            the as list
     */
    public SearchMealForm(List<MealDto> list) {
        this.form = new VerticalLayout();

        Grid<MealDto> grid = new Grid<>(MealDto.class);
        grid.setItems(list);
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
    public MealDto getMealToAdd() {
        return this.mealToAdd;
    }

}
