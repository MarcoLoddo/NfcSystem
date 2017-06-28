package it.extrasys.tesi.tagsystem.order_service.api;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConfDtoConverter.
 */
public interface IConfDtoConverter {

    /**
     * To dto.
     *
     * @param configurationEntity
     *            the configuration entity
     * @return the configuration dto
     */
    ConfigurationDto toDto(ConfigurationEntity configurationEntity);

    /**
     * To entity.
     *
     * @param configurationDto
     *            the configuration dto
     * @return the configuration entity
     */
    ConfigurationEntity toEntity(ConfigurationDto configurationDto);

}
