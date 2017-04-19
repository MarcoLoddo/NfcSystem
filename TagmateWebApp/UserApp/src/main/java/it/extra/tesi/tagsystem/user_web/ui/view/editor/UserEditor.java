package it.extra.tesi.tagsystem.user_web.ui.view.editor;

import org.springframework.web.client.RestTemplate;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import client.UserDto;

public class UserEditor extends DataEditor {

	public UserEditor(Grid g, UserDto user, String userUri, Layout caller) {
		super();
		TextField name = new TextField();
		name.setValue(user.getName());
		name.setCaption("Name");
		TextField email = new TextField();
		email.setCaption("Email");
		email.setValue(user.getEmail());
		TextField password = new TextField();
		password.setCaption("Password");
		password.setValue(user.getPassword());

		addComponents(name, email, password);
		submit.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				user.setEmail(email.getValue());
				user.setName(name.getValue());
				user.setPassword(password.getValue());

				RestTemplate restTemplate = new RestTemplate();
				String uri = userUri + "/update";
				restTemplate.postForEntity(uri, user, UserDto.class);

				g.getDataProvider().refreshAll();
				g.setEnabled(true);
				caller.removeComponent(event.getComponent().getParent());
			}
		});
		
		cancel.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				event.getComponent().getParent().setVisible(false);
				g.setEnabled(true);
			}
		});

	}
}
