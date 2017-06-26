package it.extrasys.tesi.tagsystem.order_app.ui.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import it.extrasys.tesi.tagsystem.common.OrderForm;
import it.extrasys.tesi.tagsystem.common.OrderFormGenerator;
import it.extrasys.tesi.tagsystem.common.client.MealDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;
import it.extrasys.tesi.tagsystem.order_app.client.RestClient;

public class RegisterOrderView extends HorizontalLayout implements View {

    private RestClient restClient = new RestClient();
    private List<OrderDto> orders;
    private List<OrderForm> orderForms;
    public RegisterOrderView() {
        setSizeFull();
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addStyleName(ValoTheme.LAYOUT_CARD);
        addComponent(vLayout);

        vLayout.setSizeUndefined();
        TextField nfcField = new TextField("Insert nfc:");
        Button submit = new Button("Submit");
        vLayout.addComponents(nfcField, submit);
        submit.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                OrderFormGenerator orderFormGenerator = new OrderFormGenerator() {

                    @Override
                    public List<OrderDto> restCallGetOrders(String nfc) {
                        return RegisterOrderView.this.restClient
                                .getOrdersByNfc(nfc).stream()
                                .filter(order -> DateUtils.isSameDay(new Date(),
                                        order.getData()))
                                .collect(Collectors.toList());
                    }

                    @Override
                    public MealDto restGetMeal(Long mealId) {
                        return RegisterOrderView.this.restClient
                                .getMeal(mealId);
                    }

                    @Override
                    public BigDecimal restCallGetTotal(Long orderId) {
                        // TODO Auto-generated method stub
                        return RegisterOrderView.this.restClient
                                .getTotal(orderId);
                    }
                };
                RegisterOrderView.this.orderForms = orderFormGenerator
                        .getOrderFormByNfc(nfcField.getValue());
                if (RegisterOrderView.this.orderForms.size() > 0) {
                    for (OrderForm form : RegisterOrderView.this.orderForms) {
                        Button close = new Button("Close Order");
                        if (form.getOrder().isClosed()) {
                            close.setEnabled(false);
                        } else {
                            close.addClickListener(new ClickListener() {

                                @Override
                                public void buttonClick(ClickEvent event) {
                                    RegisterOrderView.this.restClient
                                            .closeOrder(form.getOrder()
                                                    .getOrderId());
                                    close.setEnabled(false);
                                }
                            });
                        }
                        form.addComponent(close);
                        form.setComponentAlignment(close,
                                Alignment.BOTTOM_RIGHT);
                    }
                    RegisterOrderView.this.orderForms
                            .forEach(order_ -> vLayout.addComponent(order_));

                    RegisterOrderView.this.orders = orderFormGenerator
                            .getOrders();
                } else {
                    vLayout.addComponent(new Label("No orders available"));
                }

            }
        });
        setComponentAlignment(vLayout, Alignment.MIDDLE_CENTER);
    }
    @Override
    public void enter(ViewChangeEvent event) {

    }

}
