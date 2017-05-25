package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.order_service.db.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.entity.MealType;

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

    /**
     * Match configuration.
     *
     * @param mealtypes
     *            the mealtypes
     * @return the list
     */
    @Override
    public List<ConfigurationEntity> matchConfiguration(
            List<MealType> mealtypes, Date date) {
        List<ConfigurationEntity> entities = this.configDao.findByDate(date);

        return entities.stream()
                .filter(e -> e.getMealtypes().containsAll(mealtypes))
                .collect(Collectors.toList());
    }

}
