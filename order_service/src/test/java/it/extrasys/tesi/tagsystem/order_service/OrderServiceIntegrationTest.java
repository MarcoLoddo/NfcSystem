package it.extrasys.tesi.tagsystem.order_service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
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
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.ConfigurationEntity;
import it.extrasys.tesi.tagsystem.order_service.db.jpa.entity.MealType;
import it.extrasys.tesi.tagsystem.order_service.db.manager.ConfigurationManaging;

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

    /** The mapping jackson 2 http message converter. */
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    /** The conf managing. */
    @Autowired
    private ConfigurationManaging confManaging;

    /**
     * Sets the converters.
     *
     * @param converters
     *            the new converters
     */
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters)
                .stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny().orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

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

    /**
     * Gets the conf test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void getConfTest() throws Exception {
        String jsonExpected = "{configurationId:4,specialPrice:2.00,starDate:2017-06-01,endDate:2017-06-30,preciseMatch:false,name:conf1,mealtypes:[MEAT,FISH]}";

        String jsonResponse = this.mockMvc.perform(get("/configurations/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType)).andReturn()
                .getResponse().getContentAsString();
        JSONAssert.assertEquals(jsonExpected, jsonResponse,
                JSONCompareMode.LENIENT);
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
    public ConfigurationEntity createConf() throws ParseException {
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
     * Adds the conf test.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    @Rollback(true)
    public void addConfTest() throws Exception {
        String jsonExpected = "{configurationId:4,specialPrice:2.00,starDate:2017-06-01,endDate:2017-06-30,preciseMatch:false,name:conf1,mealtypes:[MEAT,FISH]}";

        this.mockMvc
                .perform(post("/configurations/").contentType(this.contentType)
                        .content(json(createConf())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.contentType))
                .andExpect(jsonPath("$.configurationId", is(not(nullValue()))));
    }

    /**
     * Json.
     *
     * @param o
     *            the o
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o,
                MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
