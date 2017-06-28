package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

/**
 * The Interface WalletDao.
 */
public interface WalletDao extends JpaRepository<WalletEntity, Long> {

    /**
     * Find by user id.
     *
     * @param userid
     *            the userid
     * @return the list
     */
    @Query("Select w from Wallets w LEFT JOIN FETCH w.transactions where w.userId=?1")
    WalletEntity findByUserId(Long userid);

    @Override
    @Query("Select w from Wallets w LEFT JOIN FETCH w.transactions where w.walletId=?1")
    WalletEntity findOne(Long id);
}
