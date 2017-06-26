package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionEntity.
 */
@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public class TransactionEntity {

    /** The transaction id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    /** The user nfc. */
    private String userNfc;

    /** The price. */
    @Temporal(TemporalType.DATE)
    private BigDecimal price;

    /** The wallet id. */
    @ManyToOne
    @JoinTable(joinColumns = @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id"))
    private Long walletId;

    /** The date. */
    private Date date;

    /** The type. */
    private TransactionType type;

    /**
     * The Enum TransactionType.
     */
    public enum TransactionType {

        /** The add funds. */
        ADD_FUNDS,

        /** The local purchase. */
        LOCAL_PURCHASE,

        /** The lunchbox. */
        LUNCHBOX;
    }

    /**
     * Gets the transaction id.
     *
     * @return the transaction id
     */
    public Long getTransactionId() {
        return this.transactionId;
    }

    /**
     * Sets the transaction id.
     *
     * @param transactionId
     *            the new transaction id
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the user nfc.
     *
     * @return the user nfc
     */
    public String getUserNfc() {
        return this.userNfc;
    }

    /**
     * Sets the user nfc.
     *
     * @param userNfc
     *            the new user nfc
     */
    public void setUserNfc(String userNfc) {
        this.userNfc = userNfc;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Sets the price.
     *
     * @param price
     *            the new price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public TransactionType getType() {
        return this.type;
    }

    /**
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(TransactionType type) {
        this.type = type;
    }

}
