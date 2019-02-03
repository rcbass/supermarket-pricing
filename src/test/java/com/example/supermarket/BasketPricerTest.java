package com.example.supermarket;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasketPricerTest {

    private BasketPricer basketPricer;
    private Basket       basket;
    private List<Offer>  offers;

    @Mock
    private Offer        offer;

    @Before
    public void setUp() {
        Items items = new Items();
        items.putItem(new Item("Bran Flakes", BigDecimal.valueOf(2)));
        items.putItem(new Item("Orange Juice", BigDecimal.valueOf(1.29)));
        items.putItem(new Item("Onions", BigDecimal.valueOf(0.7)));
        items.putItem(new Item("Potatoes", BigDecimal.valueOf(0.8)));
        items.putItem(new Item("Oranges", BigDecimal.valueOf(1.99)));

        offers = new ArrayList<>();

        basketPricer = new BasketPricer(items, offers);

        basket = new Basket();
        basket.addItem("Bran Flakes", 2);
        basket.addItem("Orange Juice");
        basket.addWeightedItem("Onions", BigDecimal.valueOf(0.6));
        basket.addWeightedItem("Potatoes", BigDecimal.valueOf(1.5));
    }

    @Test
    public void itemsAreSummedCorrectly() {
        Bill bill = basketPricer.price(basket);

        assertEquals(BigDecimal.valueOf(6.91), bill.getPrice());
        assertEquals(BigDecimal.valueOf(6.91), bill.getPriceBeforeDiscounts());
        assertEquals(BigDecimal.ZERO, bill.getTotalDiscount());
        assertEquals(emptyList(), bill.getDiscounts());
        assertEquals(basket, bill.getBasket());
    }

    @Test
    public void discountsAreAppliedCorrectly() {
        offers.add(offer);
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(offer, BigDecimal.valueOf(0.7)));
        when(offer.apply(basket)).thenReturn(discounts);

        Bill bill = basketPricer.price(basket);

        assertEquals(BigDecimal.valueOf(6.21), bill.getPrice());
        assertEquals(BigDecimal.valueOf(6.91), bill.getPriceBeforeDiscounts());
        assertEquals(BigDecimal.valueOf(0.7), bill.getTotalDiscount());
        assertEquals(discounts, bill.getDiscounts());
        assertEquals(basket, bill.getBasket());
    }

    @Test
    public void pricesAreRoundedIfNecessary() {
        basket = new Basket();
        basket.addWeightedItem("Oranges", BigDecimal.valueOf(0.2));

        assertEquals(0, basketPricer.sumWeightedItems(basket)
                                    .compareTo(BigDecimal.valueOf(0.4)));
    }

}
