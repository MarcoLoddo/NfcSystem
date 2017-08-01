package it.extrasys.tesi.tagsystem.integrity_test_service.api.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.integrity_test_service.api.common.MealType;

/**
 * The Class ConfigurationDto.
 */
public class ConfigurationDto {
    private Long configurationId;
    private List<MealType> mealTypes = new ArrayList<>();

    private BigDecimal specialPrice;

    private Date starDate, endDate;

    private boolean preciseMatch;

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPreciseMatch() {
        return this.preciseMatch;
    }

    public void setPreciseMatch(boolean preciseMatch) {
        this.preciseMatch = preciseMatch;
    }

    public Date getStarDate() {
        return this.starDate;
    }

    public void setStartDate(Date date) {
        this.starDate = date;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

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
