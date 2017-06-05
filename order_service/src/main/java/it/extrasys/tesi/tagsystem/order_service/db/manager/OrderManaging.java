package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

public interface OrderManaging {

    List<ConfigurationEntity> matchConfiguration(List<MealType> mealtypes,
            Date date);
    OrderEntity addOrder(OrderEntity order);
    BigDecimal calculatePrice(OrderEntity orderEntity);

}
