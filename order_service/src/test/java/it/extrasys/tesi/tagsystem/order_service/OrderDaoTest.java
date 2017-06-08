package it.extrasys.tesi.tagsystem.order_service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import it.extrasys.tesi.tagsystem.order_service.db.jpa.dao.OrderDao;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderDaoTest.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    private List<OrderEntity> founded;

    private enum Verify {

        NOTHING, FIND_BY_DATE, FIND_BY_NFC
    }
    private Verify flag = Verify.NOTHING;
    /**
     * Setup.
     *
     * @throws ParseException
     */
    @Before
    public void setup() throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setData(dataFormat.parse("2016-06-08"));
        orderEntity.setNfcId("prova");
        this.orderDao.save(orderEntity);
        this.orderDao.flush();

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setData(dataFormat.parse("2016-06-09"));
        orderEntity2.setNfcId("prova");
        this.orderDao.save(orderEntity2);
        this.orderDao.flush();

    }

    /**
     * Find by date test.
     *
     * @throws ParseException
     */
    @Test
    public void findByDateTest() throws ParseException {
        System.out.println("\n testFindOrderByDate \n");
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.founded = this.orderDao.findByData(dataFormat.parse("2016-06-08"));
        assertThat(this.founded.size(), is(equalTo(1)));
    }

    /**
     * Find by nfc test.
     */

    public void findByNfcTest() {
        System.out.println("\n testFindOrderByNfc \n");

        this.founded = this.orderDao.findByNfcId("prova");
        assertThat(this.founded.size(), is(equalTo(2)));
    }

    /**
     * Verify final database state.
     */
    @AfterTransaction
    void verifyFinalDatabaseState() {
        switch (this.flag) {
            case FIND_BY_DATE :

                break;
            case FIND_BY_NFC :

                break;
            default :
        }
    }
}
