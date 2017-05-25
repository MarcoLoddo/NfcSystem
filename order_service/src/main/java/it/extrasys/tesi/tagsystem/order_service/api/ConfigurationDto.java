package it.extrasys.tesi.tagsystem.order_service.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.entity.MealType;

/**
 * The Class ConfigurationDto.
 */
public class ConfigurationDto {
    private Long configurationId;
    private List<MealType> mealTypes = new ArrayList<>();
    public void setMealtypes(List<MealType> types) {
        this.mealTypes = types;
    }

    private BigDecimal specialPrice;

    public BigDecimal getSpecialPrice() {
        return this.specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    public List<MealType> getMealtypes() {
        return this.mealTypes;
    }

    public Long getConfigurationId() {
        return this.configurationId;
    }

    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }

}
