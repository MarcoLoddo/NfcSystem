package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.api.MealDto;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

public interface PriceCalculator {
    public BigDecimal calculatePrice(OrderEntity orderEntity,
            List<ConfigurationEntity> configurationEntities,
            List<MealDto> meals);
}
