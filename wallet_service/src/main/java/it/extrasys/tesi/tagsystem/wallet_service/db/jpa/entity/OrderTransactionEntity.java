package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionOrderEntity.
 */
@Entity
@Table(name = "transactionorders")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
@PrimaryKeyJoinColumn(name = "transaction_id")
public class OrderTransactionEntity extends TransactionEntity {

    /** The order id. */
    private Long orderId;

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
}
