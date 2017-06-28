package it.extrasys.tesi.tagsystem.order_service.api;

import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationDtoConverter.
 */
@Component
public class ConfigurationDtoConverter implements IConfDtoConverter {

    /**
     * Entity to dto.
     *
     * @param configurationEntity
     *            the configuration entity
     * @return the configuration dto
     */
    @Override
    public ConfigurationDto toDto(ConfigurationEntity configurationEntity) {
        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto.setName(configurationEntity.getName());
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
    @Override
    public ConfigurationEntity toEntity(ConfigurationDto configurationDto) {
        ConfigurationEntity entity = new ConfigurationEntity();
        entity.setConfigurationId(configurationDto.getConfigurationId());
        entity.setSpecialPrice(configurationDto.getSpecialPrice());
        entity.getMealtypes().addAll(configurationDto.getMealtypes());
        entity.setStartDate(configurationDto.getStarDate());
        entity.setEndDate(configurationDto.getEndDate());
        entity.setName(configurationDto.getName());
        return entity;

    }
}
