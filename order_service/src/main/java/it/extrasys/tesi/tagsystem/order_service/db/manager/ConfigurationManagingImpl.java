package it.extrasys.tesi.tagsystem.order_service.db.manager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationManagingImpl.
 */
@Component
public class ConfigurationManagingImpl implements ConfigurationManaging {

    /** The config dao. */
    @Autowired
    private ConfigurationDao configDao;

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging
     * #addConfiguration(it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.
     * ConfigurationEntity)
     */
    @Override
    public ConfigurationEntity addConfiguration(
            ConfigurationEntity configurationEntity) {
        return this.configDao.save(configurationEntity);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging
     * #getConfiguration(java.lang.Long)
     */
    @Override
    public ConfigurationEntity getConfiguration(Long id) {
        return this.configDao.findOne(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging
     * #updateConfiguration(it.extrasys.tesi.tagsystem.order_service.db.jpa.
     * entity.ConfigurationEntity)
     */
    @Override
    @Transactional
    public ConfigurationEntity updateConfiguration(
            ConfigurationEntity configurationEntity) {
        return this.configDao.save(configurationEntity);
    }
}
