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

@Component
public class OrderManagingImpl implements OrderManaging {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ConfigurationDao configDao;

    @Autowired
    private Messages messages;

    private RestClient restClient = new RestClient();

    @Autowired
    private PriceCalculator priceCalc;

    @Override
    @Transactional
    public OrderEntity addOrder(OrderEntity order) {
        return this.orderDao.save(order);
    }

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

        // cerco se c'è una configurazione in database ESATTAMENTE
        // corrispondente
        List<ConfigurationEntity> configurationEntities = matchConfiguration(
                mealTypes, orderEntity.getData());

        // passo al calcolo del prezzo e lo restituisco
        return this.priceCalc.calculatePrice(orderEntity, configurationEntities,
                meals);
    }
    /**
     * Match configuration.
     *
     * @param mealtypes
     *            the mealtypes
     * @return the list
     */
    @Override
    @Transactional
    public List<ConfigurationEntity> matchConfiguration(
            List<MealType> mealtypes, Date date) {
        List<ConfigurationEntity> entities = this.configDao.findByDate(date);

        return entities.stream()
                .filter(e -> e.getMealtypes().containsAll(mealtypes))
                .collect(Collectors.toList());
    }
}
