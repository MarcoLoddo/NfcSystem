package it.extrasys.tesi.tagsystem.corner_service.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class OrderDto.
 */
public class OrderDto {

    private Long orderId;

    private Date data;

    private BigDecimal totalPrice;

    private String nfcId;

    private List<ConfigurationDto> configurations = new ArrayList<>();

    private List<Long> mealId = new ArrayList<>();

    private boolean closed;

    public List<Long> getMealId() {
        return this.mealId;
    }

    public void setMealId(List<Long> mealId) {
        this.mealId = mealId;
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

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}
