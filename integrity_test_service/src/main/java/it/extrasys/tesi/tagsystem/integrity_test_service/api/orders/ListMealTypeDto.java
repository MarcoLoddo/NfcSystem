package it.extrasys.tesi.tagsystem.integrity_test_service.api.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.common.MealType;

/**
 * The Class ListMealType.
 */
public class ListMealTypeDto {

    private List<MealType> mealTypes = new ArrayList<>();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public List<MealType> getMealtypes() {
        return this.mealTypes;
    }
}
