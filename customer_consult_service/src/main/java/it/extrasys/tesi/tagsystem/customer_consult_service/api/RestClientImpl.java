package it.extrasys.tesi.tagsystem.customer_consult_service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.common.client.MealDto;
import it.extrasys.tesi.tagsystem.common.client.MenuDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
@Component
public class RestClientImpl implements RestClient {

    /** The rest template. */
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Messages messages;

    public MealDto getMeal(Long id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        String uri = this.messages.getMessages("get.meal");
        return this.restTemplate.getForEntity(uri, MealDto.class, map)
                .getBody();
    }
    public List<OrderDto> getOrderByNfc(String nfc) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("nfc", nfc);
        String uri = this.messages.getMessages("get.order");
        return Arrays.asList(this.restTemplate
                .getForEntity(uri, OrderDto[].class, map).getBody());
    }
    public OrderDto getOrderById(Long id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        String uri = this.messages.getMessages("get.order");
        return this.restTemplate.getForEntity(uri, OrderDto.class, map)
                .getBody();
    }
    public List<MenuDto> getMenuOfDay(Date date) {
        Map<String, Date> map = new HashMap<String, Date>();
        map.put("date", date);
        String uri = this.messages.getMessages("get.order");
        return this.restTemplate.getForEntity(uri,
                (Class<? extends ArrayList<MenuDto>>) ArrayList.class, map)
                .getBody();
    }

}
