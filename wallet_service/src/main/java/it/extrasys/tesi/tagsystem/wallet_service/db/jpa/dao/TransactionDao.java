package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity.TransactionType;

/**
 * The Interface TransactionDao.
 */
public interface TransactionDao extends JpaRepository<TransactionEntity, Long> {

    /**
     * Find by wallet id.
     *
     * @param walletId
     *            the wallet id
     * @return the transaction entity
     */
    TransactionEntity findByWalletId(Long walletId);

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
     * Find by type and wallet id.
     *
     * @param type
     *            the type
     * @param walletId
     *            the wallet id
     * @return the list
     */
    List<TransactionEntity> findByTypeAndWalletId(TransactionType type,
            Long walletId);

}
