package it.extrasys.tesi.tagsystem.order_service;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.tesi.tagsystem.order_service.db.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.entity.MealType;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigDaoTest.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class ConfigDaoTest {

    /** The config dao. */
    @Autowired
    private ConfigurationDao configDao;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        ConfigurationEntity config = new ConfigurationEntity();
        config.setSpecialPrice(new BigDecimal("2.0"));
        config.getMealtypes().add(MealType.DESSERT);
        config.getMealtypes().add(MealType.PASTA);
        config.getMealtypes().add(MealType.DESSERT);
        this.configDao.save(config);
        this.configDao.flush();
    }

    /**
     * Adds the configuration test.
     *
     */
    @Test
    public void addConfigTest() {
        assertTrue(this.configDao.findAll().get(0).getMealtypes().size() == 3);
    }

    /**
     * Match config test.
     */
    @Test
    public void matchConfigTest() {
        List<MealType> tyMealtypes = this.configDao.findAll().get(0)
                .getMealtypes();
        List<ConfigurationEntity> entities = this.configDao.findAll();
        entities.stream()
                .filter(e -> e.getMealtypes().containsAll(tyMealtypes));
        assertThat(entities.get(0).getConfigurationId(),
                is(equalTo(new Long("1"))));
    }
    @AfterTransaction
    public void clear() {
        this.configDao.deleteAll();
    }

}
