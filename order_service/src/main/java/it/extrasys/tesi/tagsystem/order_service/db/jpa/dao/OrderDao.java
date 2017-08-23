package it.extrasys.tesi.tagsystem.order_service.db.jpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderType;

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
     * @return the list
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

    /**
     * Find by closed.
     *
     * @param status
     *            the status
     * @return the list
     */
    List<OrderEntity> findByClosed(Boolean status);

    /**
     * Find by closed and nfc id.
     *
     * @param status
     *            the status
     * @param nfc
     *            the nfc
     * @return the order entity
     */
    OrderEntity[] findByClosedAndNfcId(Boolean status, String nfc);

    /**
     * Find by closed and nfc id and type.
     *
     * @param status
     *            the status
     * @param nfc
     *            the nfc
     * @param type
     *            the type
     * @return the order entity
     */
    OrderEntity findByClosedAndNfcIdAndType(Boolean status, String nfc,
            OrderType type);
}
