package com.welltestedlearning.mealkiosk.kitchen;

import com.welltestedlearning.mealkiosk.api.OrderResponse;
import com.welltestedlearning.mealkiosk.domain.KitchenService;
import com.welltestedlearning.mealkiosk.domain.MealOrder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Profile("prod")
public class HttpKitchenService implements KitchenService {

  private final RestTemplate restTemplate = new RestTemplate();
  private static final String KITCHEN_POST_URL = "http://spiral-burger-kitchen-kiosk.herokuapp.com/api/orders";
  private static final String KITCHEN_GET_URL = "http://spiral-burger-kitchen-kiosk.herokuapp.com/api/orders/{orderNumber}";

  @Override
  public Long sendOrder(MealOrder mealOrder) {
    return 1L;
  }

  @Override
  public String statusFor(Long orderNumber) {
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("orderNumber", orderNumber.toString());

    // use the RestTemplate to make the request to the URL for the given orderNumber
    OrderResponse orderResponse = restTemplate.getForObject(KITCHEN_GET_URL, OrderResponse.class, uriVariables);

    // extract the Status from the response
    String status = orderResponse.getStatus();
    // return the status
    return status;
  }
}
