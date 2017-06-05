package it.extrasys.tesi.tagsystem.order_service.db.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

/**
 * The Class OrderManagingImpl.
 */
@Component
public class ConfigurationManagingImpl implements ConfigurationManaging {

    @Autowired
    private ConfigurationDao configDao;
    @Override
    public ConfigurationEntity addConfiguration(
            ConfigurationEntity configurationEntity) {
        return this.configDao.save(configurationEntity);
    }
    @Override
    public ConfigurationEntity getConfiguration(Long id) {
        return this.configDao.findOne(id);
    }

}
