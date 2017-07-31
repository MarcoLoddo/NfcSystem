package it.extrasys.tesi.tagsystem.integrity_test_service.api.orders;

import java.math.BigDecimal;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.common.MealType;

/**
 * The Class MealDto.
 *
 */
public class MealDto {

    private Long mealId;

    /** The description. */
    private String description;

    /** The price. */
    private BigDecimal price;

    private MealType type;
    public Long getMealId() {
        return this.mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MealType getType() {
        return this.type;
    }

    public void setType(MealType type) {
        this.type = type;
    }
}
