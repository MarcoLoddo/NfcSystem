package it.extrasys.tesi.tagsystem.corner_service.api;

public interface CornerRestClient {
    OrderDto addMealToOrder(String userNfc, Long mealId);
}