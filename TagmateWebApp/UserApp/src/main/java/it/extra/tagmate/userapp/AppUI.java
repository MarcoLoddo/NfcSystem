package it.extra.tagmate.userapp;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import javax.enterprise.inject.New;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SpringUI
@Theme("mytheme")
public class AppUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		setContent(layout);

		CssLayout topbar = new CssLayout();
		topbar.addStyleName("top");
		Label title = new Label("Admin board");
		title.addStyleName("h1");
		topbar.addComponent(title);
		layout.addComponent(topbar);

		HorizontalLayout menuAndContent = new HorizontalLayout();
		menuAndContent.setSizeFull();
		layout.addComponent(menuAndContent);

		CssLayout menu = new CssLayout();
		menu.setWidth("100%");
		menu.addStyleName("menu");
		menuAndContent.addComponent(menu);
		
		
		VerticalLayout contnt = new VerticalLayout();
		contnt.setSpacing(true);
		contnt.setMargin(true);
		menuAndContent.addComponent(contnt);

		menuAndContent.setExpandRatio(menu, 2);
		menuAndContent.setExpandRatio(contnt, 8);

		Label header = new Label("Titolo");
		header.setStyleName("h2");
		
		Label text = new Label("<p>bla bla bla</p>"); 
		menuAndContent.addComponent(header);
		menuAndContent.addComponent(text);
		
		
		/*
		 * view.addComponent(new Label("Hello Vaadin!"));
		 * view.addStyleName("background-black");
		 */

	}
}