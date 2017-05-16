package it.extrasys.tesi.tagsystem.meal_app.ui.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

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
import it.extrasys.tesi.tagsystem.meal_app.ui.components.Externalizer;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.events.MealUpdateEventListener;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.forms.MenuAddForm;
import it.extrasys.tesi.tagsystem.meal_app.ui.components.forms.MenuForm;

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
        this.datapicker.setDateFormat("yyyy-MM-dd");
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

        Button addMenu = new Button("Add new Menu");
        addMenu.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                MenuAddForm menuAddForm = new MenuAddForm();
                UI.getCurrent().addWindow(menuAddForm);
                menuAddForm.setModal(true);
                menuAddForm.center();
                menuAddForm.focus();
                menuAddForm.addCloseListener(new CloseListener() {

                    @Override
                    public void windowClose(CloseEvent e) {
                        refresh(LocalDate.now());

                    }
                });
            }
        });
        addComponent(addMenu);
        addMenu.addStyleName(ValoTheme.BUTTON_LINK);
        this.datapicker
                .addValueChangeListener(new ValueChangeListener<LocalDate>() {

                    @Override
                    public void valueChange(ValueChangeEvent<LocalDate> event) {
                        refresh(event.getValue());
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

    private void refresh(LocalDate date) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = Externalizer.getString("getMenuByDay");
        Map<String, LocalDate> map = new HashMap<>();
        map.put("date", date);
        MenuDto[] menuDtos = restTemplate
                .getForEntity(uri, MenuDto[].class, map).getBody();

        this.menus.forEach(k -> this.form.removeComponent(k));
        this.menus.clear();
        if (menuDtos.length > 0) {
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
        for (MenuForm menuForm : this.menus) {
            RestTemplate restTemplate = new RestTemplate();

            String uri = Externalizer.getString("getMenu");
            Map<String, Integer> map = new HashMap<>();
            map.put("id", menuForm.getMenuDto().getMenuId());
            MenuDto menuDto = restTemplate.getForEntity(uri, MenuDto.class, map)
                    .getBody();

            menuForm.updateForm(menuDto);
        }

    }

}
