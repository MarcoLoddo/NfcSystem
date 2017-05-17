package it.extrasys.tesi.tagsystem.meal_app.ui.components.forms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.meal_app.client.MealDto;
import it.extrasys.tesi.tagsystem.meal_app.client.MenuDto;
import it.extrasys.tesi.tagsystem.meal_app.client.RestClient;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.events.MealUpdateEventListener;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.events.MealUpdatePublisher;

/**
 * The Class MenuForm.
 */
public class MenuForm extends VerticalLayout implements MealUpdatePublisher {

    private MenuCompilationForm menuEdit;
    private List<MealUpdateEventListener> listeners;
    private Button editMenuButton;
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

        this.editMenuButton = new Button("Edit this menu");

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
        this.menuEdit = new MenuCompilationForm(menu);

        removeAllComponents();

        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.addComponent(new Label(menu.getType()));
        titleBar.addComponent(this.editMenuButton);
        this.editMenuButton.addStyleName(ValoTheme.BUTTON_LINK);

        this.editMenuButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (UI.getCurrent().getWindows()
                        .contains(MenuForm.this.menuEdit)) {
                    UI.getCurrent().removeWindow(MenuForm.this.menuEdit);
                    MenuForm.this.menuEdit = new MenuCompilationForm(menu);
                }
                UI.getCurrent().addWindow(MenuForm.this.menuEdit);
                MenuForm.this.menuEdit.setModal(true);
                MenuForm.this.menuEdit.center();
                MenuForm.this.menuEdit.focus();
                MenuForm.this.menuEdit.getSubmitButton()
                        .addClickListener(new ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent event) {
                                MenuDto menuToAdd = MenuForm.this.menuEdit
                                        .getData();
                                menuToAdd.getMeals().removeAll(
                                        MenuForm.this.menuDto.getMeals());
                                RestClient restClient = new RestClient();
                                restClient.updateMenu(menuToAdd);
                                MenuForm.this.menuEdit.close();

                            }
                        });
                MenuForm.this.menuEdit.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {

                        fireMealUpdateEvent();
                    }
                });
            }

        });
        addComponent(titleBar);
        titleBar.setSpacing(true);
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
            Button edtiMealButton = new Button("edit");
            line.addComponent(new Label("€" + mealDto.getPrice()));
            line.addComponent(edtiMealButton);
            edtiMealButton.addStyleName(ValoTheme.BUTTON_LINK);
            edtiMealButton.addClickListener(new ClickListener() {

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
                                RestClient restClient = new RestClient();
                                restClient
                                        .updateMeal(formEdit.getUpdatedMeal());
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
