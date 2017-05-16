package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

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
import it.extrasys.tesi.tagsystem.meal_app.ui.components.Externalizer;

/**
 * The Class MenuAddForm.
 */
public class MenuAddForm extends Window {

    private VerticalLayout form;
    private MenuDto menuToAdd;
    private Button submit;

    /**
     * Instantiates a new menu add form.
     */
    public MenuAddForm() {
        this.menuToAdd = new MenuDto();

        this.form = new VerticalLayout();
        TextField type = new TextField("Type");
        this.menuToAdd
                .setDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        DateField data = new DateField();
        data.setValue(LocalDate.now());
        data.setDateFormat("yyyy-MM-dd");
        data.setEnabled(false);
        Grid<MealDto> meals = new Grid<>(MealDto.class);
        List<MealDto> mealsInGrid = new ArrayList<>();
        meals.setItems(mealsInGrid);
        this.submit = new Button("Submit");
        meals.setCaption("Meals:");
        Button addMeal = new Button("Add Meal");
        addMeal.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {

                RestTemplate restTemplate = new RestTemplate();

                String uri = Externalizer.getString("getAllMeals");
                MealDto[] allMeals = restTemplate
                        .getForEntity(uri, MealDto[].class).getBody();

                SearchMealForm searchMealForm = new SearchMealForm(
                        Arrays.asList(allMeals));
                UI.getCurrent().addWindow(searchMealForm);
                searchMealForm.center();
                searchMealForm.setModal(true);
                searchMealForm.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        if (searchMealForm.getMealToAdd() != null) {
                            mealsInGrid.add(searchMealForm.getMealToAdd());
                            meals.getDataProvider().refreshAll();
                        }

                    }
                });
            }
        });
        this.submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                MenuAddForm.this.menuToAdd.setType(type.getValue());
                MenuAddForm.this.menuToAdd.setMeals(mealsInGrid);

                RestTemplate restTemplate = new RestTemplate();
                String uri = Externalizer.getString("addMenu");
                MenuDto menuPersisted = restTemplate.postForEntity(uri,
                        MenuAddForm.this.menuToAdd, MenuDto.class).getBody();
                uri = Externalizer.getString("addMealtoMenu");
                Map<String, Integer> map = new HashMap<>();
                for (MealDto mealDto : mealsInGrid) {
                    map.clear();
                    map.put("menuId", menuPersisted.getMenuId());
                    map.put("mealId", mealDto.getMealId());
                    restTemplate.getForEntity(uri, null, map);
                }

                close();

            }
        });
        this.form.addComponents(data, type, meals, addMeal, this.submit);
        this.form.setComponentAlignment(this.submit, Alignment.BOTTOM_RIGHT);
        setContent(this.form);
    }

    public MenuDto getMenuToAdd() {
        return this.menuToAdd;
    }

}
