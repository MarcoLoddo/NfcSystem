package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;
import it.extrasys.tesi.tagsystem.meal_app.client.MenuDto;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.Externalizer;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.events.MealUpdateEventListener;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.events.MealUpdatePublisher;

/**
 * The Class MenuForm.
 */
public class MenuForm extends VerticalLayout implements MealUpdatePublisher {

    private List<MealUpdateEventListener> listeners;
    /**
     * Instantiates a new menu form.
     *
     * @param menu
     *            the menu
     */
    private MenuDto menuDto;

    /**
     * Instantiates a new menu form.
     *
     * @param menu
     *            the menu
     */
    public MenuForm(MenuDto menu) {

        setSizeFull();
        setCaption(menu.getType());
        updateForm(menu);
    }
    @Override
    public void addListener(MealUpdateEventListener listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList<>();
        }
        this.listeners.add(listener);

    }
    @Override
    public void fireMealUpdateEvent() {
        for (MealUpdateEventListener listener : this.listeners) {
            listener.update();
        }

    }
    public MenuDto getMenuDto() {
        return this.menuDto;
    }

    /**
     * Update form.
     *
     * @param menu
     *            the menu
     */
    public void updateForm(MenuDto menu) {
        this.menuDto = menu;
        removeAllComponents();
        menu.getMeals().sort(new Comparator<MealDto>() {

            @Override
            public int compare(MealDto o1, MealDto o2) {

                if (o1.getType().ordinal() < o2.getType().ordinal()) {
                    return -1;
                }
                if (o1.getType() == o2.getType()) {
                    return 0;
                }
                return 1;
            }
        });
        int course = -1;
        for (MealDto mealDto : menu.getMeals()) {
            HorizontalLayout line = new HorizontalLayout();
            if (course < mealDto.getType().ordinal()) {
                Label title = new Label(mealDto.getType().toString());

                addComponent(title);
                title.addStyleName("bg");
                title.setSizeFull();
                course = mealDto.getType().ordinal();

            }
            line.addComponent(new Label(mealDto.getDescription()));
            Button editButton = new Button("edit");
            line.addComponent(new Label("€" + mealDto.getPrice()));
            line.addComponent(editButton);
            editButton.addStyleName(ValoTheme.BUTTON_LINK);
            editButton.addClickListener(new ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    EditMeal formEdit = new EditMeal(mealDto);
                    getUI().addWindow(formEdit);
                    formEdit.focus();
                    formEdit.center();
                    formEdit.addCloseListener(new CloseListener() {

                        @Override
                        public void windowClose(CloseEvent e) {
                            if (formEdit.getUpdatedMeal() != null) {
                                String uri = Externalizer
                                        .getString("updateMeal");
                                RestTemplate restTemplate = new RestTemplate();

                                Map<String, Integer> map = new HashMap<>();
                                map.put("id",
                                        formEdit.getUpdatedMeal().getMealId());
                                restTemplate.postForEntity(uri,
                                        formEdit.getUpdatedMeal(),
                                        MealDto.class, map);
                                map = new HashMap<>();
                                map.put("mealId", mealDto.getMealId());
                                map.put("menuId", menu.getMenuId());
                                restTemplate.getForEntity(
                                        Externalizer.getString("addMealtoMenu"),
                                        null, map);
                                map = new HashMap<>();
                                map.put("id", menu.getMenuId());
                                MenuDto update = restTemplate.getForEntity(
                                        Externalizer.getString("getMenu"),
                                        MenuDto.class, map).getBody();
                                fireMealUpdateEvent();
                            }
                            getUI().removeWindow(formEdit);
                        }
                    });

                }
            });
            addComponent(line);
            line.setSpacing(true);

        }

    }

}
