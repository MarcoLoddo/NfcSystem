package it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;

/**
 * The Interface TransactionOrderDao.
 */
public interface OrderTransactionDao
        extends
            JpaRepository<OrderTransactionEntity, Long> {

    /**
     * Find by order id.
     *
     * @param orderId
     *            the order id
     * @return the list
     */
    List<OrderTransactionEntity> findByOrderId(Long orderId);
}
