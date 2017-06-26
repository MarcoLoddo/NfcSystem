package it.extrasys.tesi.tagsystem.wallet_service.db.manager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.TransactionDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.TransactionOrderDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.WalletDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionManagerImpl.
 */
@Component
public class WalletManagerImpl {

    /** The transaction dao. */
    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionOrderDao transactionOrderDao;

    @Autowired
    private WalletDao walletDao;
    /**
     * Adds the transaction.
     *
     * @param transaction
     *            the transaction
     * @return the transaction entity
     */

    private TransactionEntity addTransaction(TransactionEntity transaction) {
        return this.transactionDao.save(transaction);
    }

    /**
     * Adds the order transaction.
     *
     * @param transaction
     *            the transaction
     * @return the order transaction entity
     */
    private OrderTransactionEntity addOrderTransaction(
            OrderTransactionEntity transaction) {
        return this.transactionOrderDao.save(transaction);
    }
    /**
     * Update transaction.
     *
     * @param transaction
     *            the transaction
     * @return the transaction entity
     */
    @Transactional
    public TransactionEntity updateTransaction(TransactionEntity transaction) {
        return this.transactionDao.save(transaction);
    }

    /**
     * Gets the transaction by id.
     *
     * @param id
     *            the id
     * @return the by id
     */
    @Transactional
    public TransactionEntity getTransactionById(Long id) {
        return this.transactionDao.findOne(id);
    }

    /**
     * Gets the order transaction by id.
     *
     * @param id
     *            the id
     * @return the order transaction by id
     */
    @Transactional
    public OrderTransactionEntity getOrderTransactionById(Long id) {
        return this.transactionOrderDao.findOne(id);
    }

    /**
     * Gets the order transactions by order id.
     *
     * @param orderId
     *            the order id
     * @return the order transactions by order id
     */
    @Transactional
    public List<OrderTransactionEntity> getOrderTransactionsByOrderId(
            Long orderId) {

        return this.transactionOrderDao.findByOrderId(orderId);
    }

    /**
     * Find transactions by date.
     *
     * @param date
     *            the date
     * @return the list
     * @throws ParseException
     *             the parse exception
     */
    @Transactional
    public List<TransactionEntity> findTransactionsByDate(Date date)
            throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        return this.transactionDao
                .findByDate(dFormat.parse(dFormat.format(date)));

    }

    /**
     * Update funds.
     *
     * @param walletId
     *            the wallet id
     * @param transaction
     *            the transaction
     * @return the big decimal
     */
    public BigDecimal updateFunds(Long walletId,
            TransactionEntity transaction) {
        WalletEntity wallet = this.walletDao.findOne(walletId);
        switch (transaction.getType()) {
            case ADD_FUNDS :
                wallet.setFunds(wallet.getFunds().add(transaction.getPrice()));
                break;
            case LOCAL_PURCHASE :
            case LUNCHBOX :
                wallet.setFunds(
                        wallet.getFunds().subtract(transaction.getPrice()));
                break;
            default :
                break;
        }
        return wallet.getFunds();
    }

    /**
     * Adds the transaction to wallet.
     *
     * @param walletId
     *            the wallet id
     * @param transaction
     *            the transaction
     */
    @Transactional
    public void addTransactionToWallet(Long walletId,
            TransactionEntity transaction) {
        TransactionEntity transactionSaved = addTransaction(transaction);
        WalletEntity wallet = this.walletDao.findOne(walletId);
        wallet.getTransactions().add(transactionSaved);
    }

    /**
     * Adds the order transaction to wallet.
     *
     * @param walletId
     *            the wallet id
     * @param transaction
     *            the transaction
     */
    @Transactional
    public void addOrderTransactionToWallet(Long walletId,
            OrderTransactionEntity transaction) {
        OrderTransactionEntity transactionSaved = addOrderTransaction(
                transaction);
        WalletEntity wallet = this.walletDao.findOne(walletId);
        wallet.getTransactions().add(transactionSaved);
    }
}
