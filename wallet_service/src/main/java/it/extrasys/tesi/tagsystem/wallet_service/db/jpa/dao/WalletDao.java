package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

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
    WalletEntity findByUserId(Long userid);
}
