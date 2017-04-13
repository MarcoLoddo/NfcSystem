package it.extra.tesi.tagsystem.user_web.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import it.extra.tesi.tagsystem.user_web.ui.view.Login;
import it.extra.tesi.tagsystem.user_web.ui.view.MenuNavigation;
import it.extra.tesi.tagsystem.user_web.ui.view.UserManaging;
@SpringUI
@Theme("mytheme")
public class NavigationManager extends UI {
	Navigator navigator;
	@Override
	protected void init(VaadinRequest request) {
		addStyleName("background-dark");
		getPage().setTitle("Tagmate Test");
		navigator = new Navigator(this,this);
		navigator.addView("", new Login());
		navigator.addView("UserManaging", new UserManaging());
		navigator.addView("Navigation", new MenuNavigation());
	}

}
