package it.extrasys.tesi.tagsystem.corner_service.api;

public interface CornerRestClient {
    void addMealToOrder(String userNfc, Long mealId);
}