package it.extrasys.tesi.tagsystem.wallet_service.test.unity.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.extrasys.tesi.tagsystem.wallet_service.WalletServiceApplication;
import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionDtoConverter;
import it.extrasys.tesi.tagsystem.wallet_service.api.TransactionType;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.OrderTransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.TransactionEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.jpa.entity.WalletEntity;
import it.extrasys.tesi.tagsystem.wallet_service.db.manager.WalletManager;
import it.extrasys.tesi.tagsystem.wallet_service.test.unity.JsonConverter;
// TODO: Auto-generated Javadoc

/**
 * The Class WalletServiceTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WalletServiceApplication.class)
@WebAppConfiguration
public class WalletServiceTest {

    /** The content type. */
    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    /** The mock mvc. */
    private MockMvc mockMvc;

    /** The web application context. */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** The json converter. */
    @Autowired
    private JsonConverter jsonConverter;

    /** The wallet manager. */
    @MockBean
    private WalletManager walletManager;

    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext).build();
    }

    /** The wallet id. */
    private long walletId = 0;

    /**
     * Creates the entity.
     *
     * @return the wallet entity
     */
    private WalletEntity createEntity() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setFunds(new BigDecimal(10L));
        walletEntity.setUserId(1L);
        return walletEntity;
    }

    /**
     * Mock id wallet.
     *
     * @param walletEntity
     *            the wallet entity
     * @return the wallet entity
     */
    private WalletEntity mockIdWallet(WalletEntity walletEntity) {
        walletEntity.setWalletId(this.walletId++);
        return walletEntity;
    }

    /**
     * Creates the order transaction.
     *
     * @param walletEntity
     *            the wallet entity
     * @return the order transaction entity
     * @throws ParseException
     *             the parse exception
     */
    private OrderTransactionEntity createOrderTransaction(
            WalletEntity walletEntity) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        OrderTransactionEntity transactionEntity = new OrderTransactionEntity();
        transactionEntity.setDate(dateFormat.parse("2017-12-19"));
        transactionEntity.setPrice(new BigDecimal(10L));
        transactionEntity.setType(TransactionType.ADD_FUNDS);
        transactionEntity.setUserNfc("prova");
        transactionEntity.setWallet(walletEntity);
        walletEntity.getTransactions().add(transactionEntity);
        return transactionEntity;

    }

    /**
     * Mock add transaction.
     *
     * @param entity
     *            the entity
     * @param transactionEntity
     *            the transaction entity
     * @return the big decimal
     */
    private BigDecimal mockAddTransaction(WalletEntity entity,
            TransactionEntity transactionEntity) {
        entity.getTransactions().add(transactionEntity);
        switch (transactionEntity.getType()) {
            case ADD_FUNDS :
                entity.setFunds(
                        entity.getFunds().add(transactionEntity.getPrice()));
                break;
            case LOCAL_PURCHASE :
            case LUNCHBOX :
                entity.setFunds(entity.getFunds()
                        .subtract(transactionEntity.getPrice()));
                break;
            default :
                break;
        }
        return entity.getFunds();
    }

    /**
     * Crate list transaction.
     *
     * @param walletEntity
     *            the wallet entity
     * @return the list
     * @throws ParseException
     *             the parse exception
     */
    private List<TransactionEntity> crateListTransaction(
            WalletEntity walletEntity) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<TransactionEntity> list = new ArrayList<>();
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setDate(dateFormat.parse("2017-12-19"));
        transactionEntity.setPrice(new BigDecimal(10L));
        transactionEntity.setType(TransactionType.ADD_FUNDS);
        transactionEntity.setUserNfc("prova");
        transactionEntity.setWallet(walletEntity);
        list.add(transactionEntity);
        return list;
    }

    /**
     * Gets the user wallet test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getUserWalletTest() throws Exception {

        WalletEntity walletEntity = createEntity();

        Mockito.when(this.walletManager.getWalletFromUserId(anyLong()))
                .thenReturn(mockIdWallet(walletEntity));
        this.mockMvc.perform(get("/wallets/1")).andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.walletId", is(0)))
                .andExpect(jsonPath("$.userId", is(1)));
    }

    /**
     * Gets the user transactions test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getUserTransactionsTest() throws Exception {

        WalletEntity walletEntity = createEntity();
        walletEntity.getTransactions()
                .addAll(crateListTransaction(walletEntity));
        List<TransactionEntity> list = crateListTransaction(walletEntity);
        Mockito.when(this.walletManager.getWalletFromUserId(anyLong()))
                .thenReturn(walletEntity);
        this.mockMvc.perform(get("/transactions/?userId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].userNfc", is("prova")));
    }

    /**
     * Gets the order transactions test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getOrderTransactionsTest() throws Exception {
        WalletEntity walletEntity = createEntity();

        OrderTransactionEntity orderTransactionEntity = createOrderTransaction(
                walletEntity);
        orderTransactionEntity.setOrderId(1L);
        Mockito.when(this.walletManager.getOrderTransactionById(anyLong()))
                .thenReturn(orderTransactionEntity);
        this.mockMvc.perform(get("/orders/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.orderId", is(1)));
    }

    /**
     * Adds the transaction to wallet test.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws Exception
     *             the exception
     */
    @Test
    public void addTransactionToWalletTest() throws IOException, Exception {
        WalletEntity walletEntity = createEntity();
        OrderTransactionEntity orderTransactionEntity = createOrderTransaction(
                walletEntity);
        orderTransactionEntity.setOrderId(1L);
        orderTransactionEntity.setTransactionId(0L);
        Mockito.when(
                this.walletManager.addTransaction(any(TransactionEntity.class)))
                .thenReturn(orderTransactionEntity);
        Mockito.when(this.walletManager.getWalletFromUserId(anyLong()))
                .thenReturn(walletEntity);
        Mockito.when(this.walletManager.updateFunds(anyLong(),
                any(TransactionEntity.class)))
                .thenReturn(mockAddTransaction(walletEntity,
                        orderTransactionEntity));
        this.mockMvc
                .perform(post("/wallets/1").contentType(this.contentType)
                        .content(this.jsonConverter.json(TransactionDtoConverter
                                .toDto(orderTransactionEntity))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", is(20)));
    }
}
