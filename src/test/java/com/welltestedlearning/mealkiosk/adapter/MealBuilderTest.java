package com.welltestedlearning.mealkiosk.adapter;

import com.welltestedlearning.mealkiosk.domain.BurgerTopping;
import com.welltestedlearning.mealkiosk.domain.MealOrder;
import com.welltestedlearning.mealkiosk.domain.Toppings;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.fail;

public class MealBuilderTest {

  @Test
  public void orderWithBurgerNoneRegularDrinkCosts6() throws Exception {
    MealOrder mealOrder = MealBuilder.builder()
                                     .burger("none")
                                     .drink("regular")
                                     .build();

    assertThat(mealOrder.price())
        .isEqualTo(6);
  }

  @Test
  public void orderWithBurgerCheeseAndLargeFriesIs11Dollars() throws Exception {
    MealOrder order = MealBuilder.builder()
                                 .burger("cheese")
                                 .fries("large")
                                 .build();

    // assert that the price is burger (5) + cheese (1) + large-fries (5)
    assertThat(order.price())
        .isEqualTo(11);
  }

  @Test
  public void orderWithNoneStringCosts5() throws Exception {
    MealBuilder mealBuilder = new MealBuilder();
    mealBuilder.burger("none");

    MealOrder mealOrder = mealBuilder.build();

    assertThat(mealOrder.price())
        .isEqualTo(5);
  }

  @Test
  public void orderWithCheeseStringCosts6() throws Exception {
    MealBuilder mealBuilder = new MealBuilder();
    mealBuilder.burger("cheese");

    MealOrder mealOrder = mealBuilder.build();

    assertThat(mealOrder.price())
        .isEqualTo(6);
  }

  @Test
  public void orderWithBaconAndCheeseParsedInto2Toppings() throws Exception {
    String order = "bacon, cheese";
    Toppings toppings = MealBuilder.parseToppings(order);

    assertThat(toppings.items())
        .containsExactlyInAnyOrder(BurgerTopping.BACON, BurgerTopping.CHEESE);
  }

  @Test
  public void emptyOrderStringThrowsInvalidOrderException() throws Exception {
    try {
      MealBuilder.parseToppings("");
      fail("Expected exception to be thrown");
    } catch (InvalidOrderTextException e) {
      // test passed
    }
  }

  @Test
  public void invalidToppingThrowsInvalidOrderException() throws Exception {
    assertThatThrownBy(() -> {
      MealBuilder.parseToppings("chesee");
    })
        .isInstanceOf(InvalidOrderTextException.class)
        .hasMessage("Topping 'chesee' is not a valid topping.");

  }
}
