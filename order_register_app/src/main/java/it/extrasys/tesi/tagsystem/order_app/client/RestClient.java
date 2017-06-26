package it.extrasys.tesi.tagsystem.order_app.client;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import it.extrasys.tesi.tagsystem.common.client.MealDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;
import it.extrasys.tesi.tagsystem.order_app.ui.i18n.Messages;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
public class RestClient {

    /** The rest template. */
    private RestTemplate restTemplate;

    /**
     * Instantiates a new rest client.
     */
    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Adds the menu.
     *
     * @param menuDto
     *            the menu dto
     * @return the menu dto
     */
    public OrderDto addOrder(OrderDto orderDto) {
        String uri = Messages.get("add.order");

        return this.restTemplate.postForEntity(uri, orderDto, OrderDto.class)
                .getBody();

    }
    public List<OrderDto> getOrdersByNfc(String nfc) {
        String uri = Messages.get("get.orders.by.nfc");
        Map<String, String> map = new HashMap<>();
        map.put("nfc", nfc);
        return Arrays.asList(this.restTemplate
                .getForEntity(uri, OrderDto[].class, map).getBody());
    }
    public MealDto getMeal(Long id) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        String uri = Messages.get("get.meal");
        return this.restTemplate.getForEntity(uri, MealDto.class, map)
                .getBody();
    }
    public BigDecimal getTotal(Long orderId) {
        String uri = Messages.get("get.price");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", orderId);
        return this.restTemplate.getForEntity(uri, BigDecimal.class, map)
                .getBody();
    }

    public void closeOrder(Long orderId) {
        String uri = Messages.get("close.order");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", orderId);
        this.restTemplate.getForEntity(uri, null, map).getBody();
    }

}
