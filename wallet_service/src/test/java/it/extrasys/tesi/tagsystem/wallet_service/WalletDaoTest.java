package it.extrasys.tesi.tagsystem.wallet_service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;

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
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.dao.WalletDao;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;

/**
 * The Class WalletDaoTest.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class WalletDaoTest {

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private OrderTransactionDao orderTransactionDao;
    /**
     * Setup.
     */
    @Before
    public void setup() {

    }

    private WalletEntity testWalletEntity = createTestEntity();

    private WalletEntity createTestEntity() {
        WalletEntity entity = new WalletEntity();
        entity.setFunds(new BigDecimal(100));
        entity.setUserId(new Long(1));

        return entity;
    }

    private void clear() {
        this.walletDao.deleteAll();
    }
    /**
     * Test add wallet.
     */
    @Test
    public void testAddWallet() {
        clear();
        System.out.println("\n\n\n\n testAddWallet \n\n\n\n");
        this.walletDao.save(this.testWalletEntity);
        assertNotNull(this.testWalletEntity.getWalletId());
    }

    private TransactionEntity createTransaction() throws ParseException {
        TransactionEntity transactionEntity = new TransactionEntity();
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
        transactionEntity.setDate(dataformat.parse("2017-06-10"));
        transactionEntity.setPrice(new BigDecimal("5.00"));
        transactionEntity.setType(TransactionType.LUNCHBOX);
        transactionEntity.setUserNfc("prova");
        return transactionEntity;
    }
    private TransactionEntity createOrderTransaction() throws ParseException {
        OrderTransactionEntity transactionEntity = new OrderTransactionEntity();
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
        transactionEntity.setDate(dataformat.parse("2017-06-10"));
        transactionEntity.setPrice(new BigDecimal("5.00"));
        transactionEntity.setType(TransactionType.LUNCHBOX);
        transactionEntity.setUserNfc("prova");
        transactionEntity.setOrderId((long) 1);
        return transactionEntity;
    }
    /**
     * Test add transaction to wallet.
     *
     * @throws ParseException
     */
    @Test
    public void testAddTransactionToWallet() throws ParseException {
        clear();
        System.out.println("\n\n\n\n testAddTransactionToWallet \n\n\n\n");
        this.walletDao.save(this.testWalletEntity);
        TransactionEntity transactionEntity = createTransaction();

        this.transactionDao.save(transactionEntity);
        this.testWalletEntity.getTransactions().add(transactionEntity);
        this.walletDao.save(this.testWalletEntity);
        transactionEntity.setWallet(this.testWalletEntity);
        this.transactionDao.save(transactionEntity);
        assertThat(this.walletDao.findOne(this.testWalletEntity.getWalletId())
                .getTransactions().size(), is(1));
    }

    /**
     * Test find by user id.
     */
    @Test
    public void testFindByUserId() {
        clear();
        System.out.println("\n\n\n\n testFindByUserId \n\n\n\n");
        this.walletDao.save(this.testWalletEntity);
        WalletEntity walletEntity = createTestEntity();
        walletEntity.setUserId((long) 9);
        this.walletDao.save(walletEntity);
        assertThat(this.walletDao.findByUserId((long) 1), is(notNull()));
        assertThat(this.walletDao.findByUserId((long) 9), is(notNull()));
        assertThat(this.walletDao.findByUserId((long) 10), is(nullValue()));
    }

}
