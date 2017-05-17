package it.extrasys.tesi.tagsystem.meal_app.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import it.extrasys.tesi.tagsystem.meal_app.client.Messages;
import it.extrasys.tesi.tagsystem.meal_app.ui.view.MenuView;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationManager.
 */
@SpringUI
@Theme("mealapptheme")
public class NavigationManager extends UI {

    @Autowired
    private Messages messages;

    public Messages getMessages() {
        return this.messages;
    }
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
        MenuView menuView = new MenuView();

        this.navigator.addView(menuView.getPageName(), menuView);
    }

}
