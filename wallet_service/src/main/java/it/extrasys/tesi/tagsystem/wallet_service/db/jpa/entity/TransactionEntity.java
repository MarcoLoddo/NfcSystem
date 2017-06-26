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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionType;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionEntity.
 */
@Entity(name = "Transactions")
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.INTEGER)
public class TransactionEntity {

    /** The transaction id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    /** The user nfc. */
    private String userNfc;

    /** The price. */

    private BigDecimal price;

    /** The wallet id. */
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private WalletEntity wallet;

    /** The date. */
    @Temporal(TemporalType.DATE)
    private Date date;

    /** The type. */
    private TransactionType type;

    /**
     * The Enum TransactionType.
     */

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
    public WalletEntity getWallet() {
        return this.wallet;
    }

    public void setWallet(WalletEntity walletEntity) {
        this.wallet = walletEntity;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
