package it.extra.tesi.tagsystem.user_web.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import client.UserDto;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class MenuNavigation extends HorizontalLayout implements View {
	public MenuNavigation() {
		VerticalLayout left = new VerticalLayout();
		addComponent(left);

		Button button = new Button("User Managing");
		button.addStyleName(ValoTheme.BUTTON_LINK);
		button.addStyleName("button-text");
		button.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("UserManaging");

			}
		});
		left.addComponent(button);
		VerticalLayout right = new VerticalLayout();
		addComponent(right);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		UserDto admin = (UserDto) VaadinSession.getCurrent().getAttribute("user");
		if (admin== null)
		{
			getUI().getNavigator().navigateTo("");
			
		}
		else {
			Notification notification = new Notification("Welcome "+ admin.getName()+", on the Tagmate Admin Panel ",
					Notification.TYPE_TRAY_NOTIFICATION);
			notification.setPosition(Position.BOTTOM_RIGHT);
			notification.show(Page.getCurrent());
		}
	}
}
