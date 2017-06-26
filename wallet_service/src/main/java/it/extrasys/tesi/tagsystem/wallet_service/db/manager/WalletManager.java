package it.extrasys.tesi.tagsystem.wallet_service.db.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

/**
 * The Interface WalletManager.
 */
public interface WalletManager {

    /**
     * Adds the transaction.
     *
     * @param transaction
     *            the transaction
     * @return the transaction entity
     */
    TransactionEntity addTransaction(TransactionEntity transaction);

    /**
     * Adds the order transaction.
     *
     * @param transaction
     *            the transaction
     * @return the order transaction entity
     */
    OrderTransactionEntity addOrderTransaction(
            OrderTransactionEntity transaction);

    /**
     * Gets the transaction by id.
     *
     * @param id
     *            the id
     * @return the by id
     */

    TransactionEntity getTransactionById(Long id);

    /**
     * Gets the order transaction by id.
     *
     * @param id
     *            the id
     * @return the order transaction by id
     */
    OrderTransactionEntity getOrderTransactionById(Long id);

    /**
     * Update funds.
     *
     * @param walletId
     *            the wallet id
     * @param transaction
     *            the transaction
     * @return the big decimal
     */
    BigDecimal updateFunds(Long walletId, TransactionEntity transaction);

    /**
     * Adds the transaction to wallet.
     *
     * @param walletId
     *            the wallet id
     * @param transaction
     *            the transaction
     */
    void addTransactionToWallet(Long walletId, TransactionEntity transaction);

    /**
     * Adds the order transaction to wallet.
     *
     * @param walletId
     *            the wallet id
     * @param transaction
     *            the transaction
     */
    void addOrderTransactionToWallet(Long walletId,
            OrderTransactionEntity transaction);

    /**
     * Gets the wallet from transaction id.
     *
     * @param transactionId
     *            the transaction id
     * @return the wallet from transaction id
     */
    WalletEntity getWalletFromTransactionId(Long transactionId);

    /**
     * Gets the transactions from wallet.
     *
     * @param walletId
     *            the wallet id
     * @return the transactions from wallet
     */
    List<TransactionEntity> getTransactionsFromWallet(Long walletId);

    /**
     * Gets the transactions from wallet by date.
     *
     * @param walletId
     *            the wallet id
     * @param date
     *            the date
     * @return the transactions from wallet by date
     */
    List<TransactionEntity> getTransactionsFromWalletByDate(Long walletId,
            Date date);

    /**
     * Gets the wallet from user id.
     *
     */
    WalletEntity getWalletFromUserId(Long id);

}
