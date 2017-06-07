package it.extrasys.tesi.tagsystem.order_service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.extrasys.tesi.tagsystem.order_service.api.MealDto;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.manager.PriceCalculator;

/**
 * The Class PriceCalculatorTest.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PriceCalculatorTest {
    @Autowired
    private PriceCalculator priceCalculator;

    private OrderEntity order = createTestingOrder();
    private List<MealDto> meals = createTestingMeals();

    private OrderEntity createTestingOrder() {
        OrderEntity orderEntity = new OrderEntity();

        ConfigurationEntity conf1 = new ConfigurationEntity();
        ConfigurationEntity conf2 = new ConfigurationEntity();

        conf1.getMealtypes().add(MealType.PASTA);
        conf1.getMealtypes().add(MealType.MEAT);
        conf1.getMealtypes().add(MealType.DRINK);
        conf1.setSpecialPrice(new BigDecimal(4.50));

        conf2.getMealtypes().add(MealType.PASTA);
        conf2.setSpecialPrice(new BigDecimal(2.00));

        orderEntity.getConfigurations().add(conf1);
        orderEntity.getConfigurations().add(conf2);
        return orderEntity;
    }
    private List<MealDto> createTestingMeals() {
        List<MealDto> meals = new ArrayList<>();
        MealDto meal1 = new MealDto();
        MealDto meal2 = new MealDto();
        MealDto meal3 = new MealDto();
        MealDto meal4 = new MealDto();
        MealDto meal5 = new MealDto();
        meal1.setType(MealType.PASTA);
        meal1.setPrice(new BigDecimal(2.00));
        meal4.setType(MealType.PASTA);
        meal4.setPrice(new BigDecimal(2.50));
        meal2.setType(MealType.MEAT);
        meal3.setType(MealType.DRINK);
        meal3.setPrice(new BigDecimal(2.00));
        meal5.setType(MealType.DRINK);
        meal5.setPrice(new BigDecimal(3.00));
        meals.add(meal1);
        meals.add(meal2);
        meals.add(meal3);
        meals.add(meal4);
        meals.add(meal5);
        return meals;
    }

    /**
     * Price calculation test.
     */
    @Test
    public void priceCalculationTest() {
        BigDecimal price = this.priceCalculator.calculatePrice(this.order,
                this.meals);
        assertThat(price, is(equalTo(new BigDecimal(9.50))));
    }

}
