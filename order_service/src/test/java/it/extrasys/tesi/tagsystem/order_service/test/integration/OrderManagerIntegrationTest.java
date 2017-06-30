package it.extrasys.tesi.tagsystem.order_service.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging;
import it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderManagerTest.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderManagerIntegrationTest {

    /** The order manager. */
    @Autowired
    private OrderManaging orderManager;

    /** The config manager. */
    @Autowired
    private ConfigurationManaging configManager;

    /**
     * Setup.
     */
    @Before
    public void setup() {

    }

    /**
     * Calculate price test.
     */
    public void calculatePriceTest() {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setData(Date.from(Instant.now()));
        ConfigurationEntity configurationEntity = new ConfigurationEntity();

        configurationEntity.getMealtypes().add(MealType.PASTA);
        configurationEntity.getMealtypes().add(MealType.MEAT);
        configurationEntity.getMealtypes().add(MealType.DRINK);
        configurationEntity.setSpecialPrice(new BigDecimal(4.50));
        configurationEntity.setStartDate(Date.from(Instant.now()));
        configurationEntity.setEndDate(Date.from(Instant.now()));

        ConfigurationEntity configurationEntity2 = new ConfigurationEntity();

        configurationEntity2.getMealtypes().add(MealType.PASTA);
        configurationEntity2.setSpecialPrice(new BigDecimal(2.00));
        configurationEntity2.setStartDate(Date.from(Instant.now()));
        configurationEntity2.setEndDate(Date.from(Instant.now()));

        this.configManager.addConfiguration(configurationEntity);
        this.configManager.addConfiguration(configurationEntity2);

        orderEntity.getConfigurations().add(configurationEntity);
        orderEntity.getConfigurations().add(configurationEntity2);

        this.orderManager.addOrder(orderEntity);
        orderEntity.getMealId().add(new Long(4)); // 2
        orderEntity.getMealId().add(new Long(2)); // 3
        orderEntity.getMealId().add(new Long(6)); // 2
        // menu 4.5
        orderEntity.getMealId().add(new Long(1)); // 2.50
        // menu 2.00
        // totale 6.50
        this.orderManager.addOrder(orderEntity);
        assertThat(this.orderManager.calculatePrice(orderEntity),
                is(equalTo(new BigDecimal(6.50))));
    }
}
