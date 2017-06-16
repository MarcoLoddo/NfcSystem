package it.extrasys.tesi.tagsystem.order_service.db.jpa.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * The Class OrderEntity.
 */
@Entity(name = "Orders")
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Temporal(value = TemporalType.DATE)
    private Date data;

    private BigDecimal totalPrice;

    private String nfcId;

    @OneToMany
    private List<ConfigurationEntity> configurations = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mealId", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "mealId")
    private List<Long> mealId = new ArrayList<>();

    private boolean closed;

    public List<Long> getMealId() {
        return this.mealId;
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

    public List<ConfigurationEntity> getConfigurations() {
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

    public void setMealId(List<Long> mealId) {
        this.mealId = mealId;
    }

}
