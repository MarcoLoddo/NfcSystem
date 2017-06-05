package it.extrasys.tesi.tagsystem.order_service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging;
import it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderManagerTest {

    @Autowired
    private OrderManaging orderManager;
    @Autowired
    private ConfigurationManaging configManager;

    @Before
    public void setup() {

    }
    @Test
    public void calculatePriceTest() {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setData(new Date().from(Instant.now()));
        ConfigurationEntity configurationEntity = new ConfigurationEntity();

        configurationEntity.getMealtypes().add(MealType.PASTA);
        configurationEntity.getMealtypes().add(MealType.MEAT);
        configurationEntity.getMealtypes().add(MealType.DRINK);
        configurationEntity.setSpecialPrice(new BigDecimal(2));
        configurationEntity.setStarDate(new Date().from(Instant.now()));
        configurationEntity.setEndDate(new Date().from(Instant.now()));

        this.configManager.addConfiguration(configurationEntity);

        orderEntity.getMealId().add(new Long(1));
        orderEntity.getMealId().add(new Long(4));
        orderEntity.getMealId().add(new Long(6));
        orderEntity.getMealId().add(new Long(2));
        System.out.println(
                "Prezzo: " + this.orderManager.calculatePrice(orderEntity));
    }
}
