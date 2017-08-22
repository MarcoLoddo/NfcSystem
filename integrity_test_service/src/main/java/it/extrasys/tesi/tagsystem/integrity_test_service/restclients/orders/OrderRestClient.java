package it.extrasys.tesi.tagsystem.integrity_test_service.restclients.orders;

import java.math.BigDecimal;
import java.util.List;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.AddMealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ConfigurationDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ListMealTypeDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderDto;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderRestClient.
 */
public interface OrderRestClient {

    /**
     * Adds the order.
     *
     * @param orderDto
     *            the order dto
     * @return the order dto
     */
    OrderDto addOrder(OrderDto orderDto);

    /**
     * Gets the orders by nfc.
     *
     * @param nfc
     *            the nfc
     * @return the orders by nfc
     */
    List<OrderDto> getOrdersByNfc(String nfc);

    /**
     * Gets the meal.
     *
     * @param id
     *            the id
     * @return the meal
     */
    MealDto getMeal(Long id);

    /**
     * Gets the total.
     *
     * @param orderId
     *            the order id
     * @return the total
     */
    BigDecimal getTotal(Long orderId);

    /**
     * Close order.
     *
     * @param orderId
     *            the order id
     */
    void closeOrder(Long orderId);

    /**
     * Match configuration.
     *
     * @param listMealType
     *            the list meal type
     * @return the list
     */
    List<ConfigurationDto> matchConfiguration(ListMealTypeDto listMealType);

    /**
     * Gets the configuration by id.
     *
     * @param id
     *            the id
     * @return the configuration by id
     */
    ConfigurationDto getConfigurationById(Long id);

    /**
     * Adds the configuration.
     *
     * @param configurationDto
     *            the configuration dto
     * @return the configuration dto
     */
    ConfigurationDto addConfiguration(ConfigurationDto configurationDto);

    /**
     * Update configuration.
     *
     * @param conf
     *            the conf
     * @return the configuration dto
     */
    ConfigurationDto updateConfiguration(ConfigurationDto conf);

    OrderDto addMealToOrder(AddMealDto mealDto);
}