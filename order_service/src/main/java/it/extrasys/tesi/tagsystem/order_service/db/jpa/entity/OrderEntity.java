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
// TODO: Auto-generated Javadoc

/**
 * The Class OrderEntity.
 */
@Entity(name = "Orders")
@Table(name = "orders")
public class OrderEntity {

    /** The order id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    /** The data. */
    @Temporal(value = TemporalType.DATE)
    private Date data;

    /** The total price. */
    private BigDecimal totalPrice;

    /** The nfc id. */
    private String nfcId;

    /** The configurations. */
    @OneToMany
    private List<ConfigurationEntity> configurations = new ArrayList<>();

    /** The meal id. */
    @ElementCollection
    @CollectionTable(name = "mealId", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "mealId")
    private List<Long> mealId = new ArrayList<>();

    /** The closed. */
    private boolean closed;

    /** The type. */
    private OrderType type;

    /**
     * Gets the meal id.
     *
     * @return the meal id
     */
    public List<Long> getMealId() {
        return this.mealId;
    }

    /**
     * Gets the total price.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Sets the total price.
     *
     * @param totalPrice
     *            the new total price
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the nfc id.
     *
     * @return the nfc id
     */
    public String getNfcId() {
        return this.nfcId;
    }

    /**
     * Sets the nfc id.
     *
     * @param nfcId
     *            the new nfc id
     */
    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    /**
     * Gets the configurations.
     *
     * @return the configurations
     */
    public List<ConfigurationEntity> getConfigurations() {
        return this.configurations;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Date getData() {
        return this.data;
    }

    /**
     * Sets the data.
     *
     * @param data
     *            the new data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Gets the order id.
     *
     * @return the order id
     */
    public Long getOrderId() {
        return this.orderId;
    }

    /**
     * Sets the order id.
     *
     * @param orderId
     *            the new order id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Checks if is closed.
     *
     * @return true, if is closed
     */
    public boolean isClosed() {
        return this.closed;
    }

    /**
     * Sets the closed.
     *
     * @param closed
     *            the new closed
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Sets the meal id.
     *
     * @param mealId
     *            the new meal id
     */
    public void setMealId(List<Long> mealId) {
        this.mealId = mealId;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public OrderType getType() {
        return this.type;
    }

    /**
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(OrderType type) {
        this.type = type;
    }
}
