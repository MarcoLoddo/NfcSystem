package it.extrasys.tesi.tagsystem.order_service.db.jpa.dao;

import java.util.Date;
import java.util.List;

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
    List<OrderEntity> findByNfcId(String nfc);

    /**
     * Find by data.
     *
     * @param date
     *            the date
     * @return the list
     */
    List<OrderEntity> findByData(Date date);
}
