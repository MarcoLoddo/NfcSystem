package it.extrasys.tesi.tagsystem.order_service.test.unity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

import it.extrasys.tesi.tagsystem.order_service.OrderManagingApplication;
import it.extrasys.tesi.tagsystem.order_service.api.MealDto;
import it.extrasys.tesi.tagsystem.order_service.api.RestClient;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.OrderEntity;
import it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging;
import it.extrasys.tesi.tagsystem.order_service.db.manager.OrderManaging;
import it.extrasys.tesi.tagsystem.order_service.db.manager.PriceCalculator;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderServiceIntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderManagingApplication.class)
@WebAppConfiguration
public class OrderServiceIntegrationTest {

    /** The content type. */
    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    /** The mock mvc. */
    private MockMvc mockMvc;

    /** The web application context. */
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JsonConverter jsonConverter;

    /** The conf managing. */
    @MockBean
    private ConfigurationManaging confManager;;

    @MockBean
    private OrderManaging orderManager;

    @MockBean
    private RestClient restClient;

    @MockBean
    private PriceCalculator priceCalc;

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

    /** The count. */
    private int count = 0;

    /**
     * Creates the conf.
     *
     * @return the configuration entity
     * @throws ParseException
     *             the parse exception
     */
    private ConfigurationEntity createConf() throws ParseException {
        ConfigurationEntity configurationEntity = new ConfigurationEntity();

        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        configurationEntity.setName("confTest" + this.count++);
        configurationEntity.getMealtypes().add(MealType.PASTA);
        configurationEntity.getMealtypes().add(MealType.MEAT);
        configurationEntity.getMealtypes().add(MealType.DRINK);
        configurationEntity.setSpecialPrice(new BigDecimal(4.50));
        configurationEntity.setStartDate(dFormat.parse("2017-06-28"));
        configurationEntity.setEndDate(dFormat.parse("2017-06-30"));

        return configurationEntity;
    }

    /**
     * Creates the meal dto.
     *
     * @param id
     *            the id
     * @return the meal dto
     */
    public MealDto createMealDto(Long id) {
        MealDto mealDto = new MealDto();
        mealDto.setMealId(id);
        mealDto.setDescription("prova");
        mealDto.setPrice(new BigDecimal(id));
        mealDto.setType(MealType.PASTA);
        return mealDto;
    }
    private void mockIdConf(ConfigurationEntity conf) {
        conf.setConfigurationId(1L);
    }
    private OrderEntity createOrder() throws ParseException {
        OrderEntity orderEntity = new OrderEntity();
        ConfigurationEntity testConf = createConf();
        mockIdConf(testConf);
        orderEntity.getConfigurations().add(testConf);
        orderEntity.getMealId().add(4L); // 2
        orderEntity.getMealId().add(2L); // 3
        orderEntity.getMealId().add(6L); // 2
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        orderEntity.setData(dFormat.parse("2017-06-28"));
        orderEntity.setNfcId("prova");

        return orderEntity;
    }
    private void mockIdOrder(OrderEntity orderEntity) {
        orderEntity.setOrderId(1L);
    }
    /**
     * Adds the conf test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void addConfTest() throws Exception {

        ConfigurationEntity testConf = createConf();
        mockIdConf(testConf);

        Mockito.when(this.confManager
                .addConfiguration(any(ConfigurationEntity.class)))
                .thenReturn(testConf);
        this.mockMvc
                .perform(post("/configurations/").contentType(this.contentType)
                        .content(this.jsonConverter.json(createConf())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.configurationId", is(1)));
    }

    /**
     * Gets the conf test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getConfTest() throws Exception {

        ConfigurationEntity testConf = createConf();
        testConf.setConfigurationId(1L);
        Mockito.when(this.confManager.getConfiguration(Mockito.anyLong()))
                .thenReturn(testConf);

        this.mockMvc.perform(get("/configurations/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.configurationId", is(1)));
    }

    /**
     * Adds the order.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void addOrderTest() throws Exception {

        OrderEntity orderEntity = createOrder();
        mockIdOrder(orderEntity);

        MealDto mealDto = createMealDto((long) new Random().nextInt(5));
        Mockito.when(this.orderManager.getById(Mockito.anyLong()))
                .thenReturn(orderEntity);
        Mockito.when(this.restClient.getMeal(Mockito.anyLong()))
                .thenReturn(mealDto);

        Mockito.when(this.orderManager.addOrder(any(OrderEntity.class)))
                .thenReturn(orderEntity);

        this.mockMvc
                .perform(post("/orders/").contentType(this.contentType)
                        .content(this.jsonConverter.json(createOrder())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.orderId", is(1)))
                .andExpect(jsonPath("$.totalPrice", is(nullValue())));
    }

    /**
     * Adds the order.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getOrderByIdTest() throws Exception {

        OrderEntity orderEntity = createOrder();
        mockIdOrder(orderEntity);
        Mockito.when(this.orderManager.getById(Mockito.anyLong()))
                .thenReturn(orderEntity);

        this.mockMvc.perform(get("/orders/id/1").contentType(this.contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.orderId", is(1)));
    }
    /**
     * Adds the order.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getOrderByNfcTest() throws Exception {

        OrderEntity orderEntity = createOrder();
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(orderEntity);
        mockIdOrder(orderEntity);
        Mockito.when(this.orderManager.getByNfc(Mockito.anyString()))
                .thenReturn(orders);

        this.mockMvc
                .perform(get("/orders/?nfc=" + orderEntity.getNfcId())
                        .contentType(this.contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].orderId", is(1)))
                .andExpect(jsonPath("$[0].nfcId", is(orderEntity.getNfcId())));
    }

    /**
     * Gets the order by date test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getOrderByDateTest() throws Exception {

        OrderEntity orderEntity = createOrder();
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(orderEntity);
        mockIdOrder(orderEntity);
        Mockito.when(this.orderManager.getByNfc(Mockito.anyString()))
                .thenReturn(orders);

        this.mockMvc
                .perform(get("/orders/" + orderEntity.getData())
                        .contentType(this.contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].orderId", is(1)))
                .andExpect(jsonPath("$[0].data", is(orderEntity.getData())));
    }

}
