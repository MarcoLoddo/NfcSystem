package it.extrasys.tesi.tagsystem.common.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailDto {
    private Long orderId;

    private Date data;

    private BigDecimal totalPrice;

    private String nfcId;

    private List<ConfigurationDto> configurations = new ArrayList<>();

    private List<MealDto> meals = new ArrayList<>();

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNfcId() {
        return this.nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public List<ConfigurationDto> getConfigurations() {
        return this.configurations;
    }

    public List<MealDto> getMeals() {
        return this.meals;
    }

}
