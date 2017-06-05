package it.extrasys.tesi.tagsystem.order_service.db.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

/**
 * The Class OrderDao.
 */
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

    public OrderEntity findByNfcId(String nfc);

}
