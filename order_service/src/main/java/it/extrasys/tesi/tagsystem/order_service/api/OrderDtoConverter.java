package it.extrasys.tesi.tagsystem.order_service.api;

import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
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

    /**
     * Dto to entity.
     *
     * @param orderDto
     *            the order dto
     * @return the order entity
     */
    public static OrderEntity dtoToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        List<ConfigurationEntity> configurationEntities = new ArrayList<>();
        orderDto.getConfigurations().forEach(conf -> configurationEntities
                .add(ConfigurationDtoConverter.dtoToEntity(conf)));
        orderEntity.getConfigurations().addAll(configurationEntities);
        orderEntity.setClosed(orderDto.isClosed());
        orderEntity.setData(orderDto.getData());
        orderEntity.setMealId(orderDto.getMealId());
        orderEntity.setNfcId(orderDto.getNfcId());
        orderEntity.setOrderId(orderDto.getOrderId());
        orderEntity.setTotalPrice(orderDto.getTotalPrice());
        return orderEntity;
    }
}
