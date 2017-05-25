package it.extrasys.tesi.tagsystem.order_service.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.extrasys.tesi.tagsystem.order_service.db.entity.ConfigurationEntity;

/**
 * The Class ConfigurationDao.
 */
public interface ConfigurationDao
        extends
            JpaRepository<ConfigurationEntity, Long> {

}
