package it.extra.tagmate.userapp.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import it.extra.tagmate.userapp.ui.view.Login;
import it.extra.tagmate.userapp.ui.view.UserManaging;
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
	}

}
