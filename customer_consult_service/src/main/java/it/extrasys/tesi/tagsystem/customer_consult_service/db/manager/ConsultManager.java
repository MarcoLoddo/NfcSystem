package it.extrasys.tesi.tagsystem.customer_consult_service.db.manager;

import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.common.client.OrderDetailDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;

public interface ConsultManager {

    List<OrderDto> getOrdersByNfc(String userNfc);

    List<OrderDto> getOrdersByNfc(String userNfc, Date date);

    OrderDetailDto getOrderDetailById(Long id);

}