package it.extrasys.tesi.tagsystem.integrity_test_service.restclients;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.integrity_test_service.Messages;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.meals.MealDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ConfigurationDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.ListMealTypeDto;
import it.extrasys.tesi.tagsystem.integrity_test_service.api.orders.OrderDto;

@Component
public class OrderRestClientImpl implements OrderRestClient {
    /** The rest template. */
    private RestTemplate restTemplate;

    @Autowired
    private Messages messages;
    /**
     * Instantiates a new rest client.
     */
    public OrderRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * OrderRestClient#addOrder(org.springframework.data.domain.jaxb.
     * SpringDataJaxb.OrderDto)
     */
    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        String uri = this.messages.getMessages("add.order");

        return this.restTemplate.postForEntity(uri, orderDto, OrderDto.class)
                .getBody();

    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * OrderRestClient#getOrdersByNfc(java.lang.String)
     */
    @Override
    public List<OrderDto> getOrdersByNfc(String nfc) {
        String uri = this.messages.getMessages("get.orders.by.nfc");
        Map<String, String> map = new HashMap<>();
        map.put("nfc", nfc);
        return Arrays.asList(this.restTemplate
                .getForEntity(uri, OrderDto[].class, map).getBody());
    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * OrderRestClient#getMeal(java.lang.Long)
     */
    @Override
    public MealDto getMeal(Long id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        String uri = this.messages.getMessages("get.meal");
        return this.restTemplate.getForEntity(uri, MealDto.class, map)
                .getBody();
    }
    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * OrderRestClient#getTotal(java.lang.Long)
     */
    @Override
    public BigDecimal getTotal(Long orderId) {
        String uri = this.messages.getMessages("get.price");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", orderId);
        return this.restTemplate.getForEntity(uri, BigDecimal.class, map)
                .getBody();
    }

    /*
     * (non-Javadoc)
     *
     * @see it.extrasys.tesi.tagsystem.integrity_test_service.restclients.
     * OrderRestClient#closeOrder(java.lang.Long)
     */
    @Override
    public void closeOrder(Long orderId) {
        String uri = this.messages.getMessages("close.order");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", orderId);
        this.restTemplate.getForEntity(uri, null, map);
    }

    @Override
    public ConfigurationDto addConfiguration(
            ConfigurationDto configurationDto) {
        String uri = this.messages.getMessages("add.configuration");
        return this.restTemplate
                .postForEntity(uri, configurationDto, ConfigurationDto.class)
                .getBody();
    }
    @Override
    public ConfigurationDto getConfigurationById(Long id) {
        String uri = this.messages.getMessages("get.configuration.by.id");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        return this.restTemplate.getForEntity(uri, ConfigurationDto.class, map)
                .getBody();
    }
    @Override
    public List<ConfigurationDto> matchConfiguration(
            ListMealTypeDto listMealType) {
        String uri = this.messages.getMessages("find.matching.configurations");
        return this.restTemplate
                .postForEntity(uri, listMealType,
                        (Class<? extends List<ConfigurationDto>>) ArrayList.class)
                .getBody();
    }
    @Override
    public ConfigurationDto updateConfiguration(ConfigurationDto conf) {
        String uri = this.messages.getMessages("update.configuration");
        this.restTemplate.put(uri, conf);
        return getConfigurationById(conf.getConfigurationId());
    }
}
