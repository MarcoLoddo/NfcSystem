package it.extrasys.tesi.tagsystem.wallet_service.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionType;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.OrderTransactionDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.TransactionDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuDaoTest.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class TransactionDaoTest {

    /** The menu dao. */
    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private OrderTransactionDao orderTransactionDao;

    /** The test meal entity. */
    private TransactionEntity testTransactionEntity = createTestTransactionEntity();

    private OrderTransactionEntity testOrderTransactionEntity = createTestTransactionEntity2();

    /**
     * Setup.
     */
    @Before
    public void setup() {

    }
    private TransactionEntity createTestTransactionEntity() {
        TransactionEntity entity = new TransactionEntity();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            entity.setDate(dFormat.parse("2017-10-02"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setPrice(new BigDecimal("5.00"));
        entity.setType(TransactionType.ADD_FUNDS);
        entity.setUserNfc("prova");
        return entity;
    }
    private OrderTransactionEntity createTestTransactionEntity2() {
        OrderTransactionEntity entity = new OrderTransactionEntity();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            entity.setDate(dFormat.parse("2017-10-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setPrice(new BigDecimal("5.00"));
        entity.setType(TransactionType.LUNCHBOX);
        entity.setUserNfc("prova");
        entity.setOrderId(new Long(9));
        return entity;
    }
    private void clear() {

        this.orderTransactionDao.deleteAll();
        this.transactionDao.deleteAll();
    }
    /**
     * Test find meny by date.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testAddOrderTransaction() throws Exception {

        System.out.println("\n\n\n\n testAddOrderTransaction \n\n\n\n");
        clear();
        OrderTransactionEntity saved = this.orderTransactionDao
                .save(this.testOrderTransactionEntity);

        this.orderTransactionDao.flush();
        assertThat(saved.getTransactionId(), is(not(equalTo(0))));
    }

    /**
     * Test add transaction.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testAddTransaction() throws Exception {
        System.out.println("\n\n\n\n testAddTransaction \n\n\n\n");
        clear();
        TransactionEntity saved = this.transactionDao
                .save(this.testTransactionEntity);

        this.transactionDao.flush();
        assertThat(saved.getTransactionId(), is(not(equalTo(0))));
    }

}
