package it.extrasys.tesi.tagsystem.order_service.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * The Class OrderEntity.
 */
@Entity(name = "Orders")
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    private Date data;

    @ManyToOne
    @JoinColumn(name = "configuration_id")
    private ConfigurationEntity configuration;

    public ConfigurationEntity getConfiguration() {
        return this.configuration;
    }

    public Date getData() {
        return this.data;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setConfiguration(ConfigurationEntity configuration) {
        this.configuration = configuration;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
