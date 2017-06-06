package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.api.MealDto;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface PriceCalculator.
 */
public interface PriceCalculator {

    /**
     * Calculate price.
     *
     * @param orderEntity
     *            the order entity
     * @param meals
     *            the meals
     * @return the big decimal
     */
    BigDecimal calculatePrice(OrderEntity orderEntity, List<MealDto> meals);
}
