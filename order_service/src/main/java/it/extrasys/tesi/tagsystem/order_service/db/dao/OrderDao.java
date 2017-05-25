package it.extrasys.tesi.tagsystem.order_service.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.order_service.db.entity.OrderEntity;

/**
 * The Class OrderDao.
 */
public interface OrderDao extends JpaRepository<OrderEntity, Long> {

}
