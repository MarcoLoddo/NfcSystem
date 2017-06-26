package it.extrasys.tesi.tagsystem.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.common.client.MealDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;

public abstract class OrderFormGenerator {

    private List<OrderDto> orders;
    public List<OrderDto> getOrders() {
        return this.orders;
    }
    public abstract List<OrderDto> restCallGetOrders(String nfc);
    public abstract MealDto restGetMeal(Long mealId);
    public abstract BigDecimal restCallGetTotal(Long orderId);
    private List<OrderForm> orderForms;
    public List<OrderForm> getOrderFormByNfc(String nfc) {
        this.orders = restCallGetOrders(nfc);
        this.orders.forEach(order -> {
            if (order.getTotalPrice() == null) {
                order.setTotalPrice(restCallGetTotal(order.getOrderId()));
            }
        });

        this.orderForms = new ArrayList<>();
        for (OrderDto orderDto : this.orders) {
            orderDto.setTotalPrice(restCallGetTotal(orderDto.getOrderId()));
            OrderForm orderForm = new OrderForm(orderDto) {

                @Override
                public MealDto getMeal(Long mealId) {
                    return restGetMeal(mealId);
                }

            };
            this.orderForms.add(orderForm);

        }
        return this.orderForms;
    }
}
