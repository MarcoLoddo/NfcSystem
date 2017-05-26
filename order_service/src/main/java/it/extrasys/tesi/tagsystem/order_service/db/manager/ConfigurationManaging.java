package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.util.Date;
import java.util.List;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;

/**
 * The Interface OrderManaging.
 */
public interface ConfigurationManaging {

    /**
     * Adds the configuration.
     *
     * @param configurationEntity
     *            the configuration entity
     * @return the configuration entity
     */
    ConfigurationEntity addConfiguration(
            ConfigurationEntity configurationEntity);

    /**
     * Gets the configuration.
     *
     * @param id
     *            the id
     * @return the configuration
     */
    ConfigurationEntity getConfiguration(Long id);

    /**
     * Match configuration.
     *
     * @param mealtypes
     *            the mealtypes
     * @return the list
     */
    List<ConfigurationEntity> matchConfiguration(List<MealType> mealtypes,
            Date date);

}
