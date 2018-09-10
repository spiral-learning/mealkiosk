package com.welltestedlearning.mealkiosk.api;

import com.welltestedlearning.mealkiosk.adapter.MealBuilder;
import com.welltestedlearning.mealkiosk.domain.MealOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealOrderApiController {

  @PostMapping(value = "/api/mealorder")
  public MealOrderResponse mealOrder(@RequestBody MealOrderRequest mealOrderRequest) {

    MealBuilder mealBuilder = MealBuilder.builder();
    MealOrder mealOrder = mealOrderRequest.build(mealBuilder);

    int price = mealOrder.price();

    MealOrderResponse mealOrderResponse = new MealOrderResponse();
    mealOrderResponse.setPrice(price);

    return mealOrderResponse;
  }

}
