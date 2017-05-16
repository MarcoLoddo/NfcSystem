package it.extrasys.tesi.tagsystem.meal_app.ui;

import org.springframework.beans.factory.annotation.Value;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import it.extrasys.tesi.tagsystem.meal_app.ui.components.Externalizer;
import it.extrasys.tesi.tagsystem.meal_app.ui.view.MenuView;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationManager.
 */
@SpringUI
@Theme("mealapptheme")
public class NavigationManager extends UI {

    /** The get menu. */
    @Value("${get.menu}")
    private String getMenu;

    /** The get menu by day. */
    @Value("${get.menu.by.day}")
    private String getMenuByDay;

    /** The add menu. */
    @Value("${add.menu}")
    private String addMenu;

    /** The add meal. */
    @Value("${add.meal}")
    private String addMeal;

    /** The add mealto menu. */
    @Value("${add.meal.to.menu}")
    private String addMealtoMenu;

    /** The update menu. */
    @Value("${update.menu}")
    private String updateMenu;

    /** The update meal. */
    @Value("${update.meal}")
    private String updateMeal;

    @Value("${get.all.meals}")
    private String getAllMeals;
    /** The navigator. */
    private Navigator navigator;

    /*
     * (non-Javadoc)
     *
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(VaadinRequest request) {
        // TODO Auto-generated method stub
        addStyleName("background-dark");
        getPage().setTitle("Nfc tag system Test");
        this.navigator = new Navigator(this, this);
        Externalizer.addString("getMenuByDay", this.getMenuByDay);
        Externalizer.addString("getMenu", this.getMenu);
        Externalizer.addString("addMenu", this.addMenu);
        Externalizer.addString("addMeal", this.addMeal);
        Externalizer.addString("addMealtoMenu", this.addMealtoMenu);
        Externalizer.addString("updateMenu", this.updateMenu);
        Externalizer.addString("updateMeal", this.updateMeal);
        Externalizer.addString("getAllMeals", this.getAllMeals);
        MenuView menuView = new MenuView();

        this.navigator.addView(menuView.getPageName(), menuView);
    }

}
