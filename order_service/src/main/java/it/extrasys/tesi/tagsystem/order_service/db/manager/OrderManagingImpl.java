package it.extrasys.tesi.tagsystem.order_service.db.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.order_service.api.MealDto;
import it.extrasys.tesi.tagsystem.order_service.api.Messages;
import it.extrasys.tesi.tagsystem.order_service.api.RestClient;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.OrderDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderManagingImpl.
 */
@Component
public class OrderManagingImpl implements OrderManaging {

    /** The order dao. */
    @Autowired
    private OrderDao orderDao;

    /** The config dao. */
    @Autowired
    private ConfigurationDao configDao;

    /** The messages. */
    @Autowired
    private Messages messages;

    /** The rest client. */
    private RestClient restClient = new RestClient();

    /** The price calc. */
    @Autowired
    private PriceCalculator priceCalc;

    /*
     * (non-Javadoc)
     * 
     * @see it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging#
     * addOrder(it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.
     * OrderEntity)
     */
    @Override
    @Transactional
    public OrderEntity addOrder(OrderEntity order) {
        return this.orderDao.save(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging#
     * calculatePrice(it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.
     * OrderEntity)
     */
    @Override
    @Transactional
    public BigDecimal calculatePrice(OrderEntity orderEntity) {
        List<MealDto> meals = new ArrayList<>();
        // recupero i meal dal servizio esposto
        for (Long id : orderEntity.getMealId()) {
            meals.add(this.restClient.getMeal(this.messages, id));
        }
        // ne derivo i mealtypes
        List<MealType> mealTypes = meals.stream().map(meal -> meal.getType())
                .collect(Collectors.toList());
        // passo al calcolo del prezzo e lo restituisco
        return this.priceCalc.calculatePrice(orderEntity, meals);
    }

    /**
     * Match configuration.
     *
     * @param mealtypes
     *            the mealtypes
     * @param date
     *            the date
     * @return the list
     */
    private List<ConfigurationEntity> matchConfiguration(
            List<MealType> mealtypes, Date date) {
        List<ConfigurationEntity> entities = this.configDao.findByDate(date);

        return entities.stream()
                .filter(e -> e.getMealtypes().containsAll(mealtypes))
                .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging#
     * matchConfiguration(java.util.List, java.util.Date, boolean)
     */
    @Override
    @Transactional
    public List<ConfigurationEntity> matchConfiguration(
            List<MealType> mealtypes, Date date, boolean precise) {
        if (precise) {
            List<ConfigurationEntity> configs = matchConfiguration(mealtypes,
                    date);
            return configs.stream().filter(
                    config -> config.getMealtypes().size() == mealtypes.size())
                    .collect(Collectors.toList());
        }
        return matchConfiguration(mealtypes, date);
    }
}
