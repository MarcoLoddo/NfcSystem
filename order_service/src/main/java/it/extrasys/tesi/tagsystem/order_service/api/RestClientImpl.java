package it.extrasys.tesi.tagsystem.order_service.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// TODO: Auto-generated Javadoc
/**
 * The Class RestClient.
 */
@Component
public class RestClientImpl implements RestClient {

  /** The rest template. */
  private RestTemplate restTemplate = new RestTemplate();

  @Value("${get.meal}")
  private String getMeal;

  /*
   * (non-Javadoc)
   *
   * @see it.extrasys.tesi.tagsystem.order_service.api.RestClient#getMeal(it.
   * extrasys.tesi.tagsystem.order_service.api.Messages, java.lang.Long)
   */

  @Override
  public MealDto getMeal(Long id) {
    Map<String, Long> map = new HashMap<>();
    map.put("id", id);
    String uri = this.getMeal;
    return this.restTemplate.getForEntity(uri, MealDto.class, map).getBody();
  }
}
