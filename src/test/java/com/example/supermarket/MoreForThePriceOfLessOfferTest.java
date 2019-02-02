package com.example.supermarket;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MoreForThePriceOfLessOfferTest {

    private MoreForThePriceOfLessOffer offer;
    private Items                      items;
    private Basket                     basket;

    @Before
    public void setUp() {
        items = new Items();
        Item beans = new Item("Beans", BigDecimal.valueOf(0.5));
        Item oranges = new Item("Oranges", BigDecimal.valueOf(1.99));
        items.putItem(beans);
        items.putItem(oranges);

        offer = new MoreForThePriceOfLessOffer(items, "Beans", 3, 2);

        basket = new Basket();
    }

    @Test
    public void whenItemIsNotInTheBasket_ThenThereAreNoDiscounts() {
        assertEquals(emptyList(), offer.apply(basket));
    }

    @Test
    public void whenThereAreNotEnoughItemsInTheBasket_ThenThereAreNoDiscounts() {
        basket.addItem("Beans");
        basket.addItem("Beans");
        assertEquals(emptyList(), offer.apply(basket));
    }

    @Test
    public void whenAmountOfItemsIsEqualToMoreQuantity_ThenThereIsOneDiscount() {
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");

        List<Discount> discounts = offer.apply(basket);
        assertEquals(1, discounts.size());
        Discount discount = discounts.get(0);

        assertEquals(offer, discount.getOffer());
        assertEquals(BigDecimal.valueOf(0.5), discount.getAmount());
    }

    @Test
    public void whenAmountOfItemsIsAMultipleOfMoreQuantity_ThenThereAreMultipleDiscounts() {
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");

        List<Discount> discounts = offer.apply(basket);
        assertEquals(2, discounts.size());
        for (Discount discount : discounts) {
            assertEquals(offer, discount.getOffer());
            assertEquals(BigDecimal.valueOf(0.5), discount.getAmount());
        }
    }

    @Test
    public void whenAmountOfItemsIsNotAMultipleOfMoreQuantity_ThenTheCorrectNumberOfDiscountsAreApplied() {
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");
        basket.addItem("Beans");

        List<Discount> discounts = offer.apply(basket);
        assertEquals(1, discounts.size());
        Discount discount = discounts.get(0);

        assertEquals(offer, discount.getOffer());
        assertEquals(BigDecimal.valueOf(0.5), discount.getAmount());
    }

}