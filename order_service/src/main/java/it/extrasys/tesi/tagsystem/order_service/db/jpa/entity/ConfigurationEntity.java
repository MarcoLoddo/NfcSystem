package it.extrasys.tesi.tagsystem.order_service.db.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class ConfigurationEntity.
 */
@Entity(name = "Configurations")
@Table(name = "configurations")
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuration_id")
    private Long configurationId;

    @Enumerated
    @ElementCollection
    private List<MealType> mealTypes = new ArrayList<>();

    private BigDecimal specialPrice;
    private String name;

    @Temporal(value = TemporalType.DATE)
    private Date startDate, endDate;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStarDate(Date starDate) {
        this.startDate = starDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    public BigDecimal getSpecialPrice() {
        return this.specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }
}
