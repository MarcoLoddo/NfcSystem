package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderType;

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
     * Gets the by date.
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
     * Gets the by id.
     *
     * @param id
     *            the id
     * @return the by id
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

    /**
     * Gets the order by status.
     *
     * @param status
     *            the status
     * @return the order by status
     */
    List<OrderEntity> getOrderByStatus(Boolean status);

    /**
     * Gets the order by status and nfc.
     *
     * @param status
     *            the status
     * @param nfc
     *            the nfc
     * @return the order by status and nfc
     */
    OrderEntity getOrderByStatusAndNfc(Boolean status, String nfc);

    /**
     * Gets the order by status and nfc and type.
     *
     * @param status
     *            the status
     * @param nfc
     *            the nfc
     * @param type
     *            the type
     * @return the order by status and nfc and type
     */
    OrderEntity getOrderByStatusAndNfcAndType(Boolean status, String nfc,
            OrderType type);
}
