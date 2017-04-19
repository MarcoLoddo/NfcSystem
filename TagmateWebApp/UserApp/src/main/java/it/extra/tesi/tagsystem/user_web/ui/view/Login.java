package it.extra.tesi.tagsystem.user_web.ui.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import client.LoginDto;
import client.UserDto;

public class Login extends HorizontalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396611984540369299L;

	private String userUri;

	private final TextField user;
	private final PasswordField password;
	private final Button loginButton;

	public Login() {
		setSizeFull();

		// Create the user input field
		user = new TextField("User:");

		user.setWidth("300px");
		user.setRequiredIndicatorVisible(true);

		// Create the password input field
		password = new PasswordField("Password:");
		password.setWidth("300px");
		password.setRequiredIndicatorVisible(true);
		password.setValue("");

		// Create login button
		loginButton = new Button("Login");
		loginButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				LoginDto userDto = new LoginDto();
				userDto.setEmail(user.getValue());
				userDto.setPassword(password.getValue());
				RestTemplate restTemplate = new RestTemplate();

				String uri = userUri + "/session";
				UserDto response = restTemplate.postForEntity(uri, userDto, UserDto.class).getBody();
				if (response != null) {
					try {
						VaadinSession.getCurrent().getLockInstance().lock();
						VaadinSession.getCurrent().setAttribute("user", response);
					} finally {
						VaadinSession.getCurrent().getLockInstance().unlock();
					}
					getUI().getNavigator().navigateTo("Navigation");
				} else {
					Notification notification = new Notification("Login error, try again",
							Notification.TYPE_WARNING_MESSAGE);
					notification.setPosition(Position.BOTTOM_RIGHT);
					notification.show(Page.getCurrent());
				}
			}
		});

		// Add both to a panel
		VerticalLayout fields = new VerticalLayout(user, password, loginButton);
		fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, true));
		fields.setSizeUndefined();
		fields.setStyleName(ValoTheme.LAYOUT_CARD);

		// The view root layout
		VerticalLayout viewLayout = new VerticalLayout(fields);
		viewLayout.setSizeFull();
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		addComponent(viewLayout);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		UserDto admin = (UserDto) VaadinSession.getCurrent().getAttribute("user");
		if (admin != null) {
			getUI().getNavigator().navigateTo("Navigation");
		}
		user.focus();
	}

	public String getUserUri() {
		return userUri;
	}

	public void setUserUri(String userUri) {
		this.userUri = userUri;
	}

}