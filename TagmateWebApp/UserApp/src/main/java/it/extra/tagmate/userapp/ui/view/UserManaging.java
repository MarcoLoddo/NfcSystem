package it.extra.tagmate.userapp.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import data.UserDto;

@SuppressWarnings("serial")
public class UserManaging extends VerticalLayout implements View {

	private UserDto admin;
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		admin=(UserDto)VaadinSession.getCurrent().getAttribute("user");
		if(admin==null)
			getUI().getNavigator().navigateTo("");
		setStyleName("background-white");
		
	}

}
