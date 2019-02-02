package com.example.supermarket;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class SetPriceItemOfferTest {

    private SetPriceItemOffer offer;
    private Items             items;
    private Basket            basket;

    @Before
    public void setUp() {
        items = new Items();
        Item coke = new Item("Coke", BigDecimal.valueOf(0.8));
        Item banana = new Item("Banana", BigDecimal.valueOf(0.25));
        items.putItem(coke);
        items.putItem(banana);

        offer = new SetPriceItemOffer(items, "Coke", 2, BigDecimal.ONE);

        basket = new Basket();
    }

    @Test
    public void whenItemIsNotInTheBasket_ThenThereAreNoDiscounts() {
        assertEquals(emptyList(), offer.apply(basket));
    }

    @Test
    public void whenThereAreNotEnoughItemsInTheBasket_ThenThereAreNoDiscounts() {
        basket.addItem("Coke");
        assertEquals(emptyList(), offer.apply(basket));
    }

    @Test
    public void whenAmountOfItemsIsEqualToOfferQuantity_ThenThereIsOneDiscount() {
        basket.addItem("Coke");
        basket.addItem("Coke");

        List<Discount> discounts = offer.apply(basket);
        assertEquals(1, discounts.size());
        Discount discount = discounts.get(0);

        assertEquals(offer, discount.getOffer());
        assertEquals(BigDecimal.valueOf(0.6), discount.getAmount());
    }

    @Test
    public void whenAmountOfItemsIsAMultipleOfOfferQuantity_ThenThereAreMultipleDiscounts() {
        basket.addItem("Coke");
        basket.addItem("Coke");
        basket.addItem("Coke");
        basket.addItem("Coke");

        List<Discount> discounts = offer.apply(basket);
        assertEquals(2, discounts.size());
        for (Discount discount : discounts) {
            assertEquals(offer, discount.getOffer());
            assertEquals(BigDecimal.valueOf(0.6), discount.getAmount());
        }
    }

    @Test
    public void whenAmountOfItemsIsNotAMultipleOfOfferQuantity_ThenTheCorrectNumberOfDiscountsAreApplied() {
        basket.addItem("Coke");
        basket.addItem("Coke");
        basket.addItem("Coke");

        List<Discount> discounts = offer.apply(basket);
        assertEquals(1, discounts.size());
        Discount discount = discounts.get(0);

        assertEquals(offer, discount.getOffer());
        assertEquals(BigDecimal.valueOf(0.6), discount.getAmount());
    }

}
