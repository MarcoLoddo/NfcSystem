package it.extrasys.tesi.tagsystem.common.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;;

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

    public void setStartDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public void setMealtypes(List<MealType> types) {
        this.mealTypes = types;
    }
    public Long getConfigurationId() {
        return this.configurationId;
    }

    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }

}
