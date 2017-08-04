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
import it.extrasys.tesi.tagsystem.order_service.api.RestClient;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.OrderDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderType;

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

    /** The rest client. */
    @Autowired
    private RestClient restClient;

    /** The price calc. */
    private PriceCalculator priceCalc = new PriceCalculatorImpl();

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
    public BigDecimal calculatePrice(OrderEntity orderEntity) {
        List<MealDto> meals = new ArrayList<>();
        // recupero i meal dal servizio esposto
        for (Long id : orderEntity.getMealId()) {
            meals.add(this.restClient.getMeal(id));
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

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging#
     * getByDate(java.util.Date)
     */
    @Override
    @Transactional
    public List<OrderEntity> getByDate(Date date) {
        return this.orderDao.findByData(date);
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging#
     * getByNfc(java.lang.String)
     */
    @Override
    @Transactional
    public List<OrderEntity> getByNfc(String nfc) {
        return this.orderDao.findByNfcId(nfc);
    }

    @Override
    @Transactional
    public OrderEntity getById(Long id) {
        return this.orderDao.findOne(id);
    }

    @Override
    @Transactional
    public OrderEntity updateOrder(OrderEntity order) {
        return this.orderDao.save(order);
    }

    @Override
    @Transactional
    public List<OrderEntity> getOrderByStatus(Boolean status) {
        // TODO Auto-generated method stub
        return this.orderDao.findByClosed(status);
    }

    /**
     * Gets the order by status and nfc.
     *
     * @param status
     *            the status
     * @param nfc
     *            the nfc
     * @return the order by status and nfc
     */
    @Override
    public OrderEntity getOrderByStatusAndNfc(Boolean status, String nfc) {
        return this.orderDao.findByClosedAndNfcId(status, nfc);
    }

    @Override
    public OrderEntity getOrderByStatusAndNfcAndType(Boolean status, String nfc,
            OrderType type) {
        // TODO Auto-generated method stub
        return null;
    }
}
