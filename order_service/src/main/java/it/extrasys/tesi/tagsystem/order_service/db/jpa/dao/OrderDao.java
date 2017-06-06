package it.extrasys.tesi.tagsystem.order_service.db.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderDao.
 */
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

    /**
     * Find by nfc id.
     *
     * @param nfc
     *            the nfc
     * @return the order entity
     */
    OrderEntity findByNfcId(String nfc);

}
