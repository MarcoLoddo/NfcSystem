package it.extrasys.tesi.tagsystem.order_service;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.ConfigurationDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;

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
     *
     * @throws ParseException
     */
    @Before
    public void setup() throws ParseException {
        ConfigurationEntity config = new ConfigurationEntity();
        config.setSpecialPrice(new BigDecimal("2.0"));
        config.getMealtypes().add(MealType.DESSERT);
        config.getMealtypes().add(MealType.PASTA);
        config.getMealtypes().add(MealType.DESSERT);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        config.setStartDate(dateFormat.parse("05/06/2017"));
        config.setEndDate(dateFormat.parse("30/06/2017"));
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
     * Find config by date test.
     *
     * @throws ParseException
     */
    @Test
    public void findConfigByDateTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertThat(this.configDao.findByDate(dateFormat.parse("14/06/2017"))
                .size(), is(1));

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

    /**
     * Clear.
     */
    @AfterTransaction
    public void clear() {
        this.configDao.deleteAll();
    }

}
