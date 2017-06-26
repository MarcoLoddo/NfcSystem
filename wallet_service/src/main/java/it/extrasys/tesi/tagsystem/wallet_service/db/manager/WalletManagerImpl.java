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
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.OrderTransactionDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.WalletDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionManagerImpl.
 */
@Component
public class WalletManagerImpl implements WalletManager {

    /** The transaction dao. */
    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private OrderTransactionDao transactionOrderDao;

    @Autowired
    private WalletDao walletDao;
    /**
     * Adds the transaction.
     *
     * @param transaction
     *            the transaction
     * @return the transaction entity
     */
    @Override
    @Transactional
    public TransactionEntity addTransaction(TransactionEntity transaction) {
        return this.transactionDao.save(transaction);
    }

    /**
     * Adds the order transaction.
     *
     * @param transaction
     *            the transaction
     * @return the order transaction entity
     */
    @Override
    @Transactional
    public OrderTransactionEntity addOrderTransaction(
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

    private TransactionEntity updateTransaction(TransactionEntity transaction) {
        return this.transactionDao.save(transaction);
    }

    /**
     * Gets the transaction by id.
     *
     * @param id
     *            the id
     * @return the by id
     */

    @Override
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
    @Override
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

    private List<OrderTransactionEntity> getOrderTransactionsByOrderId(
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

    private List<TransactionEntity> findTransactionsByDate(Date date)
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
    @Override
    @Transactional
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
    @Override
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
    @Override
    @Transactional
    public void addOrderTransactionToWallet(Long walletId,
            OrderTransactionEntity transaction) {
        OrderTransactionEntity transactionSaved = addOrderTransaction(
                transaction);
        WalletEntity wallet = this.walletDao.findOne(walletId);
        wallet.getTransactions().add(transactionSaved);
    }

    /**
     * Gets the wallet from transaction id.
     *
     * @param transactionId
     *            the transaction id
     * @return the wallet from transaction id
     */
    @Override
    @Transactional
    public WalletEntity getWalletFromTransactionId(Long transactionId) {
        return this.transactionDao.findOne(transactionId).getWallet();
    }

    /**
     * Gets the transactions from wallet.
     *
     * @param walletId
     *            the wallet id
     * @return the transactions from wallet
     */
    @Override
    @Transactional
    public List<TransactionEntity> getTransactionsFromWallet(Long walletId) {
        return this.transactionDao
                .findByWallet(this.walletDao.findOne(walletId));
    }

    /**
     * Gets the transactions from wallet by date.
     *
     * @param walletId
     *            the wallet id
     * @param date
     *            the date
     * @return the transactions from wallet by date
     */
    @Override
    @Transactional
    public List<TransactionEntity> getTransactionsFromWalletByDate(
            Long walletId, Date date) {

        return this.transactionDao.findByDateAndWallet(date,
                this.walletDao.findOne(walletId));
    }

    /**
     * Gets the wallet from user id.
     *
     */
    @Override
    @Transactional
    public WalletEntity getWalletFromUserId(Long id) {
        // TODO Auto-generated method stub
        return this.walletDao.findByUserId(id);
    }
}
