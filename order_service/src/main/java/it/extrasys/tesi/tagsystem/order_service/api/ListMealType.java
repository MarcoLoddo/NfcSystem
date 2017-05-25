package it.extrasys.tesi.tagsystem.order_service.api;

import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.entity.MealType;

/**
 * The Class ListMealType.
 */
public class ListMealType {

    private List<MealType> mealTypes = new ArrayList<>();

    public List<MealType> getMealtypes() {
        return this.mealTypes;
    }
}
