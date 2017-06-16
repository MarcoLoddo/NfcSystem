package it.extrasys.tesi.tagsystem.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import it.extrasys.tesi.tagsystem.common.client.ConfigurationDto;
import it.extrasys.tesi.tagsystem.common.client.MealDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;

public abstract class OrderForm extends VerticalLayout {

    private OrderDto order;

    public OrderDto getOrder() {
        return this.order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
        setForm();
    }

    public OrderForm(OrderDto order) {
        this.order = order;
        setForm();

    }
    public abstract MealDto getMeal(Long mealId);

    private void setForm() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = dateFormat.format(this.order.getData());
        Label title = new Label();
        title = new Label("Date: " + orderDate + " Order number #"
                + this.order.getOrderId());

        addComponent(title);
        setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        for (ConfigurationDto conf : this.order.getConfigurations()) {
            HorizontalLayout line = new HorizontalLayout();
            line.addComponent(new Label("Configuration:" + conf.getName()));
            line.addComponent(new Label(conf.getSpecialPrice() + "€"));
            addComponent(line);
        }
        List<MealDto> meals = new ArrayList<>();
        this.order.getMealId().forEach(mealId -> meals.add(getMeal(mealId)));
        for (MealDto mealDto : meals) {
            HorizontalLayout line = new HorizontalLayout();
            line.addComponent(new Label("Meal:" + mealDto.getDescription()));
            line.addComponent(new Label(mealDto.getPrice() + "€"));
            addComponent(line);
        }
        Label total = new Label(this.order.getTotalPrice().toString());
        addComponent(total);
        setComponentAlignment(total, Alignment.BOTTOM_RIGHT);
    }

}
