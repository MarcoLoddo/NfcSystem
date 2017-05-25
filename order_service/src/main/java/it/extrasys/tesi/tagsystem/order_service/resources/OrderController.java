package it.extrasys.tesi.tagsystem.order_service.resources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.extrasys.tesi.tagsystem.order_service.api.ConfigurationDto;
import it.extrasys.tesi.tagsystem.order_service.api.ConfigurationDtoConverter;
import it.extrasys.tesi.tagsystem.order_service.api.ListMealTypeDto;
import it.extrasys.tesi.tagsystem.order_service.db.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderController.
 */
@RestController
public class OrderController {

    /** The manager. */
    @Autowired
    private ConfigurationManaging manager;

    /**
     * Adds the configuration.
     *
     * @param configurationDto
     *            the configuration dto
     * @return the configuration dto
     */
    @RequestMapping(value = "/configuration", method = RequestMethod.POST)
    public ConfigurationDto addConfiguration(
            @RequestBody ConfigurationDto configurationDto) {
        ConfigurationEntity entity = ConfigurationDtoConverter
                .dtoToEntity(configurationDto);
        entity = this.manager.addConfiguration(entity);
        configurationDto.setConfigurationId(entity.getConfigurationId());
        return configurationDto;
    }

    /**
     * Gets the configuration by id.
     *
     * @param id
     *            the id
     * @return the configuration by id
     * @throws JsonProcessingException
     *             the json processing exception
     */
    @RequestMapping(value = "/configuration/{id}", method = RequestMethod.GET)
    public ConfigurationDto getConfigurationById(@PathVariable Long id) {
        ConfigurationEntity entity = this.manager.getConfiguration(id);

        return ConfigurationDtoConverter.entityToDto(entity);
    }

    /**
     * Match configuration.
     *
     * @param listMealType
     *            the list meal type
     * @return the list
     */
    @RequestMapping(value = "/matching", method = RequestMethod.POST)
    public List<ConfigurationDto> matchConfiguration(
            @RequestBody ListMealTypeDto listMealType) {

        if (listMealType.getDate() == null) {
            listMealType.setDate(Date.from(Instant.now()));
        }

        List<ConfigurationEntity> entities = this.manager.matchConfiguration(
                listMealType.getMealtypes(), listMealType.getDate());
        List<ConfigurationDto> dtos = new ArrayList<>();

        for (ConfigurationEntity configurationEntity : entities) {
            ConfigurationDto dto = ConfigurationDtoConverter
                    .entityToDto(configurationEntity);
            if (configurationEntity.getMealtypes().size() == listMealType
                    .getMealtypes().size()) {
                dto.setPreciseMatch(true);
            }
            dtos.add(dto);
        }
        return dtos;
    }

}
