package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionType;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionDao.
 */
public interface TransactionDao extends JpaRepository<TransactionEntity, Long> {

    /**
     * Find by wallet.
     *
     * @param wallet
     *            the wallet
     * @return the list
     */
    List<TransactionEntity> findByWallet(WalletEntity wallet);

    /**
     * Find by date.
     *
     * @param date
     *            the date
     * @return the list
     */
    List<TransactionEntity> findByDate(Date date);

    /**
     * Find by type.
     *
     * @param type
     *            the type
     * @return the list
     */
    List<TransactionEntity> findByType(TransactionType type);

    /**
     * Find by type and wallet.
     *
     * @param type
     *            the type
     * @param wallet
     *            the wallet
     * @return the list
     */
    List<TransactionEntity> findByTypeAndWallet(TransactionType type,
            WalletEntity wallet);

    /**
     * Find by date and wallet.
     *
     * @param date
     *            the date
     * @param wallet
     *            the wallet
     * @return the list
     */
    List<TransactionEntity> findByDateAndWallet(Date date, WalletEntity wallet);

    /**
     * Find by transaction id and wallet.
     *
     * @param transactionid
     *            the transactionid
     * @param wallet
     *            the wallet
     * @return the transaction entity
     */
    TransactionEntity findByTransactionIdAndWallet(Long transactionid,
            WalletEntity wallet);

}
