package it.extrasys.tesi.tagsystem.order_app.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class OrderView extends HorizontalLayout implements View {

    public OrderView() {
        setSizeFull();
        addComponent(new Button("Prova"));
    }
    @Override
    public void enter(ViewChangeEvent event) {

    }

}
