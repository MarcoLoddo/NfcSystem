package it.extra.tagmate.userapp.ui.view;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

import data.RestManager;
import data.UserDto;

public class Login extends HorizontalLayout implements View {

	public static final String NAME = "login";

    private final TextField user;

    private final PasswordField password;

    private final Button loginButton;
    
    private WebTarget target;
    
    private Client client;
    
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
				// TODO Auto-generated method stub
				UserDto userDto=new UserDto();
				userDto.setEmail(user.getValue());
				userDto.setPassword(password.getValue());
				UserDto response= new RestManager().sendUserDto("http://localhost:8090/login", userDto);
				Notification.show("Hello "+response.getName());
				try {
				    VaadinSession.getCurrent().getLockInstance().lock();
				    VaadinSession.getCurrent().setAttribute("user", response);
				} finally {
				    VaadinSession.getCurrent().getLockInstance().unlock();
				}
				getUI().getNavigator().navigateTo("UserManaging");
				
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
		// TODO Auto-generated method stub
		user.focus();
	}

}
