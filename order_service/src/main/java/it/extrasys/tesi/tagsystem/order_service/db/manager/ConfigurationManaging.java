package it.extrasys.tesi.tagsystem.order_service.db.manager;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

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

}
