package it.extrasys.tesi.tagsystem.customer_consult_service.api;

import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.common.client.MealDto;
import it.extrasys.tesi.tagsystem.common.client.MenuDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;

public interface RestClient {

    MealDto getMeal(Long id);

    List<OrderDto> getOrderByNfc(String nfc);

    List<MenuDto> getMenuOfDay(Date date);

    OrderDto getOrderById(Long id);

}