package it.extrasys.tesi.tagsystem.order_service.api;

import java.text.ParseException;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface IOrderDtoConverter.
 */
public interface IOrderDtoConverter {

  /**
   * Entity to dto.
   *
   * @param entity
   *          the entity
   * @return the order dto
   * @throws ParseException
   */
  OrderDto entityToDto(OrderEntity entity);

  /**
   * Dto to entity.
   *
   * @param orderDto
   *          the order dto
   * @return the order entity
   */
  OrderEntity dtoToEntity(

      OrderDto orderDto);

}
