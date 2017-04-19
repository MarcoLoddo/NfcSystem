package it.extra.tesi.tagsystem.user_web.ui;

import org.springframework.beans.factory.annotation.Value;

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
	@Value("${uri.user}")
	private String userUri;
	
	public String getUserUri() {
		return userUri;
	}
	public void setUserUri(String userUri) {
		this.userUri = userUri;
	}
	@Override
	protected void init(VaadinRequest request) {
		addStyleName("background-dark");
		getPage().setTitle("Tagmate Test");
		navigator = new Navigator(this,this);
		
		Login loginPage=new Login();
		loginPage.setUserUri(userUri);
		navigator.addView("", loginPage);
		
		UserManaging userManaging=new UserManaging(userUri);
		navigator.addView("UserManaging", userManaging);

		navigator.addView("Navigation", new MenuNavigation());
	}

}