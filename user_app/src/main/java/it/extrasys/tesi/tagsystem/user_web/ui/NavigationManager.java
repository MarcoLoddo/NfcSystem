package it.extrasys.tesi.tagsystem.user_web.ui;

import org.springframework.beans.factory.annotation.Value;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import it.extrasys.tesi.tagsystem.user_web.ui.view.Login;
import it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging.EditUser;
import it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging.UserSearch;
import it.extrasys.tesi.tagsystem.user_web.ui.view.usermanaging.UserView;

/**
 * Navigation manager. Here all the pages are connected so they can be accessed.
 *
 * @author marco
 *
 */
@SpringUI
@Theme("userapptheme")
public class NavigationManager extends UI {

    /**
     * Navigator object to handle navigation between views.
     */
    private Navigator navigator;
    @Value("${uri.user}")
    private String userUri;

    public String getUserUri() {
        return this.userUri;
    }
    @Override
    protected void init(VaadinRequest request) {
        addStyleName("background-dark");
        getPage().setTitle("Nfc tag system Test");
        this.navigator = new Navigator(this, this);

        Login loginPage = new Login();
        loginPage.setUserUri(this.userUri);

        this.navigator.addView("", loginPage);

        UserSearch userSearch = new UserSearch(this.userUri);
        EditUser editUser = new EditUser(this.userUri);

        this.navigator.addView(userSearch.getPageName(), userSearch);
        this.navigator.addView(editUser.getPageName(), editUser);

        UserView userView = new UserView(this.userUri, userSearch, editUser);

        // eventi click/edit
        userSearch.addStartEditListener(userView);
        editUser.addEndEditListener(userView);
        editUser.addStartEditListener(userView);
        userView.addStartEditListener(userView);

        this.navigator.addView("UserView", userView);
    }
    public void setUserUri(String userUri) {
        this.userUri = userUri;
    }

}
