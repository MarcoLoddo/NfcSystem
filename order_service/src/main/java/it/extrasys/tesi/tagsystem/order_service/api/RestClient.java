package it.extrasys.tesi.tagsystem.order_service.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
public class RestClient {

    /** The rest template. */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Gets the meal.
     *
     * @param messages
     *            the messages
     * @param id
     *            the id
     * @return the meal
     */
    public MealDto getMeal(Messages messages, Long id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        String uri = messages.getMessages("get.meal");
        return this.restTemplate.getForEntity(uri, MealDto.class, map)
                .getBody();
    }
}
