package it.extrasys.tesi.tagsystem.corner_service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CornerRestClientImpl implements CornerRestClient {

  /** The rest template. */
  private RestTemplate restTemplate;

  @Value("${add.meal.to.order}")
  private String addMealToOrder;

  /**
   * Instantiates a new rest client.
   */
  public CornerRestClientImpl() {
    this.restTemplate = new RestTemplate();
  }

  @Override
  public OrderDto addMealToOrderFromCorner(String userNfc, Long mealId) {
    String uri = this.addMealToOrder;

    System.out.println("URI servizio: " + uri);
    AddMealDto addMealDto = new AddMealDto();
    addMealDto.setMealId(mealId);
    addMealDto.setNfc(userNfc);
    addMealDto.setTypeCaller(OrderType.LOCAL_PURCHASE);
    return this.restTemplate.postForEntity(uri, addMealDto, OrderDto.class).getBody();

  }

}
