package it.extrasys.tesi.tagsystem.wallet_service.api;

import java.math.BigDecimal;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionEntity.
 */
public class TransactionDto {

    /** The transaction id. */

    private Long transactionId;

    /** The user nfc. */
    private String userNfc;

    /** The price. */
    private BigDecimal price;

    /** The wallet id. */
    private WalletDto walletDto;

    /** The date. */
    private Date date;

    /** The type. */
    private TransactionType type;

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
    public WalletDto getWalletDto() {
        return this.walletDto;
    }

    public void setWalletDto(WalletDto walletDto) {
        this.walletDto = walletDto;
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
