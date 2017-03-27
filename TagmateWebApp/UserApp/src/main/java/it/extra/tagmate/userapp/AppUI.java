package it.extra.tagmate.userapp;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;

@SpringUI
@Theme("mytheme")
public class AppUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout view = new VerticalLayout();
        view.addComponent(new Label("Hello Vaadin!"));
        view.addStyleName("background-black");
        setContent(view);
    }
}