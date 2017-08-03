package it.extrasys.tesi.tagsystem.corner_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CornerRestClientImpl implements CornerRestClient {

    /** The rest template. */
    private RestTemplate restTemplate;

    @Autowired
    private Messages messages;
    /**
     * Instantiates a new rest client.
     */
    public CornerRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }
    @Override
    public OrderDto addMealToOrder(String userNfc, Long mealId) {
        String uri = this.messages.getMessages("add.meal.to.order");

        AddMealDto addMealDto = new AddMealDto();
        addMealDto.setMealId(mealId);
        addMealDto.setNfc(userNfc);
        addMealDto.setTypeCaller(OrderType.LOCAL_PURCHASE);
        return this.restTemplate.postForEntity(uri, addMealDto, OrderDto.class)
                .getBody();

    }

}
