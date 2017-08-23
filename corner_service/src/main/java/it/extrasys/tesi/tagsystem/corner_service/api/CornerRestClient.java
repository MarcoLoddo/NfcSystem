package it.extrasys.tesi.tagsystem.corner_service.api;

public interface CornerRestClient {
    OrderDto addMealToOrderFromCorner(String userNfc, Long mealId);
}