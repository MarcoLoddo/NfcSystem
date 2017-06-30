package it.extrasys.tesi.tagsystem.wallet_service.api;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionOrderEntity.
 */
public class OrderTransactionDto extends TransactionDto {

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
