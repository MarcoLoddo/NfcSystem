package it.extrasys.tesi.tagsystem.order_service.api;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationDtoConverter.
 */
public final class ConfigurationDtoConverter {

    private ConfigurationDtoConverter() {

    }
    /**
     * Entity to dto.
     *
     * @param configurationEntity
     *            the configuration entity
     * @return the configuration dto
     */
    public static ConfigurationDto entityToDto(
            ConfigurationEntity configurationEntity) {
        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto
                .setConfigurationId(configurationEntity.getConfigurationId());
        configurationDto.setSpecialPrice(configurationEntity.getSpecialPrice());
        configurationDto.getMealtypes()
                .addAll(configurationEntity.getMealtypes());
        configurationDto.setStartDate(configurationEntity.getStartDate());
        configurationDto.setEndDate(configurationEntity.getEndDate());
        return configurationDto;

    }

    /**
     * Dto to entity.
     *
     * @param configurationDto
     *            the configuration dto
     * @return the configuration entity
     */
    public static ConfigurationEntity dtoToEntity(
            ConfigurationDto configurationDto) {
        ConfigurationEntity entity = new ConfigurationEntity();
        entity.setConfigurationId(configurationDto.getConfigurationId());
        entity.setSpecialPrice(configurationDto.getSpecialPrice());
        entity.getMealtypes().addAll(configurationDto.getMealtypes());
        return entity;

    }
}
