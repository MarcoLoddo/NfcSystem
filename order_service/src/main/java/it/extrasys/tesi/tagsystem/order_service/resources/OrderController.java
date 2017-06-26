package it.extrasys.tesi.tagsystem.order_service.resources;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.extrasys.tesi.tagsystem.order_service.api.ConfigurationDto;
import it.extrasys.tesi.tagsystem.order_service.api.ConfigurationDtoConverter;
import it.extrasys.tesi.tagsystem.order_service.api.ListMealTypeDto;
import it.extrasys.tesi.tagsystem.order_service.api.OrderDto;
import it.extrasys.tesi.tagsystem.order_service.api.OrderDtoConverter;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging;
import it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging;
import it.extrasys.tesi.tagsystem.order_service.db.manager.PriceCalculator;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderController.
 */
@RestController
public class OrderController {

    /** The manager. */
    @Autowired
    private ConfigurationManaging configManager;

    @Autowired
    private OrderManaging orderManager;

    @Autowired
    private PriceCalculator priceCalculator;
    /**
     * Adds the configuration.
     *
     * @param configurationDto
     *            the configuration dto
     * @return the configuration dto
     */
    @RequestMapping(value = "/configurations", method = RequestMethod.POST)
    public ConfigurationDto addConfiguration(
            @RequestBody ConfigurationDto configurationDto) {
        ConfigurationEntity entity = ConfigurationDtoConverter
                .dtoToEntity(configurationDto);
        entity = this.configManager.addConfiguration(entity);
        configurationDto.setConfigurationId(entity.getConfigurationId());
        return configurationDto;
    }

    /**
     * Gets the configuration by id.
     *
     * @param id
     *            the id
     * @return the configuration by id
     * @throws JsonProcessingException
     *             the json processing exception
     */
    @RequestMapping(value = "/configurations/{id}", method = RequestMethod.GET)
    public ConfigurationDto getConfigurationById(@PathVariable Long id) {
        ConfigurationEntity entity = this.configManager.getConfiguration(id);

        return ConfigurationDtoConverter.entityToDto(entity);
    }

    /**
     * Match configuration.
     *
     * @param listMealType
     *            the list meal type
     * @return the list
     */
    @RequestMapping(value = "/matching", method = RequestMethod.POST)
    public List<ConfigurationDto> matchConfiguration(
            @RequestBody ListMealTypeDto listMealType) {

        if (listMealType.getDate() == null) {
            listMealType.setDate(Date.from(Instant.now()));
        }

        List<ConfigurationEntity> entities = this.orderManager
                .matchConfiguration(listMealType.getMealtypes(),
                        listMealType.getDate(), false);
        List<ConfigurationDto> dtos = new ArrayList<>();

        for (ConfigurationEntity configurationEntity : entities) {
            ConfigurationDto dto = ConfigurationDtoConverter
                    .entityToDto(configurationEntity);
            if (configurationEntity.getMealtypes().size() == listMealType
                    .getMealtypes().size()) {
                dto.setPreciseMatch(true);
            }
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * Gets the order by date.
     *
     * @param date
     *            the date
     * @return the order by date
     */
    @RequestMapping(value = "/orders/{date}", method = RequestMethod.GET)
    public List<OrderDto> getOrderByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        List<OrderEntity> orders = this.orderManager.getByDate(date);
        List<OrderDto> dtos = new ArrayList<>();

        orders.forEach(order -> dtos.add(OrderDtoConverter.entityToDto(order)));
        return dtos;
    }
    /**
     * Gets the orders by nfc.
     *
     * @param nfc
     *            the date
     * @return the orders by nfc
     */
    @RequestMapping(value = "/orders/", method = RequestMethod.GET)
    public OrderDto[] getOrderByNfc(@RequestParam String nfc) {

        List<OrderEntity> orders = this.orderManager.getByNfc(nfc);

        return orders.stream()
                .map(order -> OrderDtoConverter.entityToDto(order))
                .collect(Collectors.toList())
                .toArray(new OrderDto[orders.size()]);
    }

    /**
     * Adds the order.
     *
     * @param order
     *            the order
     * @return the order dto
     */
    @RequestMapping(value = "/orders/", method = RequestMethod.POST)
    public OrderDto addOrder(@RequestBody OrderDto order) {
        OrderEntity orderEntity = OrderDtoConverter.dtoToEntity(order);

        return OrderDtoConverter
                .entityToDto(this.orderManager.addOrder(orderEntity));
    }

    /**
     * Adds the order.
     *
     * @param id
     *            the id
     * @return the order dto
     */
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public OrderDto addOrder(@PathVariable Long id) {
        return OrderDtoConverter.entityToDto(this.orderManager.getById(id));
    }

    /**
     * Gets the total price.
     *
     * @param id
     *            the order id
     * @return the total price
     */
    @RequestMapping(value = "/prices/{id}", method = RequestMethod.GET)
    public BigDecimal getTotalPrice(@PathVariable Long id) {
        OrderEntity orderEntity = this.orderManager.getById(id);
        orderEntity
                .setTotalPrice(this.orderManager.calculatePrice(orderEntity));
        this.orderManager.updateOrder(orderEntity);
        return orderEntity.getTotalPrice();
    }

    /**
     * Sets the closed.
     *
     * @param id
     *            the id
     * @return the order entity
     */
    @RequestMapping(value = "/close/{id}", method = RequestMethod.PUT)
    public OrderEntity closeOrder(@PathVariable Long id) {
        OrderEntity order = this.orderManager.getById(id);
        order.setClosed(true);
        return this.orderManager.updateOrder(order);
    }
}
