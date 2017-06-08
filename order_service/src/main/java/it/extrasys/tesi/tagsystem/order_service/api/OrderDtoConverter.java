package it.extrasys.tesi.tagsystem.order_service.api;

import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

/**
 * The Class OrderDtoConverter.
 */
public final class OrderDtoConverter {

    private OrderDtoConverter() {

    }
    /**
     * Entity to dto.
     *
     * @param entity
     *            the entity
     * @return the order dto
     */
    public static OrderDto entityToDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        List<ConfigurationDto> configurationDtos = new ArrayList<>();
        entity.getConfigurations().forEach(conf -> configurationDtos
                .add(ConfigurationDtoConverter.entityToDto(conf)));
        dto.getConfigurations().addAll(configurationDtos);
        dto.setClosed(entity.isClosed());
        dto.setData(entity.getData());
        dto.setMealId(entity.getMealId());
        dto.setNfcId(entity.getNfcId());
        dto.setOrderId(entity.getOrderId());
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }
}
