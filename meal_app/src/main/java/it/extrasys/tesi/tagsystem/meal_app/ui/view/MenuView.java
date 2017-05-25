package it.extrasys.tesi.tagsystem.meal_app.ui.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.meal_app.client.MenuDto;
import it.extrasys.tesi.tagsystem.meal_app.client.RestClient;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.events.MealUpdateEventListener;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.forms.MealCompilationForm;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.forms.MenuCompilationForm;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.forms.MenuForm;
import it.extrasys.tesi.tagsystem.meal_app.ui.i18n.Messages;

/**
 * The Class MenuView.
 */
public class MenuView extends VerticalLayout
        implements
            View,
            MealUpdateEventListener {

    /** The page name. */
    private String pageName = "";

    /** The form. */
    private VerticalLayout form;

    /** The datapicker. */
    private DateField datapicker;

    private List<MenuForm> menus;
    /**
     * Instantiates a new menu view.
     */
    public MenuView() {
        this.menus = new ArrayList<>();

        this.form = new VerticalLayout();
        this.form.setSpacing(true);

        this.form.addStyleName(ValoTheme.LAYOUT_CARD);
        this.datapicker = new DateField();
        this.datapicker.setCaption("Select a date:");
        setSizeFull();
        this.form.addComponent(this.datapicker);
        this.datapicker.setDateFormat(Messages.get("date.format"));
        this.form.setSizeUndefined();
        addComponent(this.form);
        setComponentAlignment(this.form, Alignment.MIDDLE_CENTER);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.
     * ViewChangeEvent)
     */
    @Override
    public void enter(ViewChangeEvent event) {
        RestClient restClient = new RestClient();
        Button addMeal = new Button("Add new Meal");
        addMeal.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                MealCompilationForm mealAddForm = new MealCompilationForm();
                UI.getCurrent().addWindow(mealAddForm);
                mealAddForm.setModal(true);
                mealAddForm.getSubmitButton()
                        .addClickListener(new ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent event) {
                                mealAddForm.setData();
                                RestClient restClient = new RestClient();
                                restClient.addMeal(mealAddForm.getMealDto());

                            }
                        });
            }
        });
        Button addMenu = new Button("Add new Menu");
        addMenu.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                MenuCompilationForm menuAddForm = new MenuCompilationForm();
                UI.getCurrent().addWindow(menuAddForm);
                menuAddForm.setModal(true);
                menuAddForm.center();
                menuAddForm.focus();
                menuAddForm.getSubmitButton()
                        .addClickListener(new ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent event) {

                                MenuDto menuDto = menuAddForm.getData();

                                restClient.addMenu(menuDto);

                                menuAddForm.close();

                            }
                        });
                menuAddForm.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        UI.getCurrent().removeWindow(menuAddForm);

                    }
                });
            }
        });
        addComponents(addMenu, addMeal);
        addMenu.addStyleName(ValoTheme.BUTTON_LINK);
        addMeal.addStyleName(ValoTheme.BUTTON_LINK);
        this.datapicker
                .addValueChangeListener(new ValueChangeListener<LocalDate>() {

                    @Override
                    public void valueChange(ValueChangeEvent<LocalDate> event) {
                        refresh(Date.valueOf(event.getValue()));
                    }
                });
    }
    /**
     * Gets the page name.
     *
     * @return the page name
     */
    public String getPageName() {
        return this.pageName;
    }

    private void refresh(Date date) {
        RestClient restClient = new RestClient();
        List<MenuDto> menuDtos = restClient.getMenuByDay(date);
        this.menus.forEach(k -> this.form.removeComponent(k));
        this.menus.clear();
        if (menuDtos.size() > 0) {
            for (MenuDto menuDto : menuDtos) {

                MenuForm menuForm = new MenuForm(menuDto);
                menuForm.addListener(this);
                this.form.addComponent(menuForm);
                this.menus.add(menuForm);

                this.form.setComponentAlignment(menuForm,
                        Alignment.MIDDLE_CENTER);

            }
        } else {
            Notification notification = new Notification(
                    "No menu for this day!",
                    Notification.TYPE_TRAY_NOTIFICATION);
            notification.setPosition(Position.BOTTOM_RIGHT);
            notification.show(UI.getCurrent().getPage());
        }
    }

    @Override
    public void update() {
        RestClient restClient = new RestClient();
        for (MenuForm menuForm : this.menus) {

            menuForm.updateForm(
                    restClient.getMenu(menuForm.getMenuDto().getMenuId()));
        }

    }

}
