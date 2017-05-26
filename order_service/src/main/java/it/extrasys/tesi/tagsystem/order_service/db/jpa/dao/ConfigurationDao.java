package it.extrasys.tesi.tagsystem.order_service.db.jpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

/**
 * The Class ConfigurationDao.
 */
public interface ConfigurationDao
        extends
            JpaRepository<ConfigurationEntity, Long> {
    /**
     * Find by date.
     *
     * @param date
     *            the date
     * @return the list
     */
    @Query("Select c from Configurations c where ?1 BETWEEN c.startDate AND c.endDate")
    List<ConfigurationEntity> findByDate(Date date);

}
