package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderManaging.
 */
public interface OrderManaging {

    /**
     * Match configuration.
     *
     * @param mealtypes
     *            the mealtypes
     * @param date
     *            the date
     * @param precise
     *            the precise
     * @return the list
     */
    List<ConfigurationEntity> matchConfiguration(List<MealType> mealtypes,
            Date date, boolean precise);

    /**
     * Adds the order.
     *
     * @param order
     *            the order
     * @return the order entity
     */
    OrderEntity addOrder(OrderEntity order);

    /**
     * Calculate price.
     *
     * @param orderEntity
     *            the order entity
     * @return the big decimal
     */
    BigDecimal calculatePrice(OrderEntity orderEntity);

    /**
     * Gets the orders by date.
     *
     * @param date
     *            the date
     * @return the by date
     */
    List<OrderEntity> getByDate(Date date);

    /**
     * Gets the by nfc.
     *
     * @param nfc
     *            the nfc
     * @return the by nfc
     */
    List<OrderEntity> getByNfc(String nfc);

    /**
     * Gets the order by id.
     *
     * @param id
     *            the id
     * @return the order by id
     */
    OrderEntity getById(Long id);

    /**
     * Update order.
     *
     * @param order
     *            the order
     * @return the order entity
     */
    OrderEntity updateOrder(OrderEntity order);

}