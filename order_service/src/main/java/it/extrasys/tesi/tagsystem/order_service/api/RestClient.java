package it.extrasys.tesi.tagsystem.order_service.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class RestClient {
    private RestTemplate restTemplate = new RestTemplate();

    public MealDto getMeal(Messages messages, Long id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        String uri = messages.getMessages("get.meal");
        return this.restTemplate.getForEntity(uri, MealDto.class, map)
                .getBody();
    }
}
