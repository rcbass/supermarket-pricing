package com.example.supermarket;

import static java.util.stream.Collectors.toList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map.Entry;

/**
 * A class responsible for producing a {@link Bill} for a {@link Basket}.
 *
 */
public class BasketPricer {

    private final Items       items;
    private final List<Offer> offers;

    /**
     * Constructs a {@link BasketPricer} with the given details.
     * 
     * @param items
     *            the items available for sale
     * @param offers
     *            a list of active offers
     */
    public BasketPricer(final Items items, final List<Offer> offers) {
        this.items = items;
        this.offers = offers;
    }

    /**
     * Prices the given basket by producing a bill.
     * 
     * @param basket
     *            a shopping basket
     * 
     * @return the bill
     */
    public Bill price(final Basket basket) {
        BigDecimal itemsPrice = sumItems(basket);
        BigDecimal weightedItemsPrice = sumWeightedItems(basket);
        BigDecimal priceBeforeDiscounts = itemsPrice.add(weightedItemsPrice);

        List<Discount> discounts = calculateDiscounts(basket);
        BigDecimal totalDiscount = discounts.stream()
                                            .map(Discount::getAmount)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal price = priceBeforeDiscounts.subtract(totalDiscount);
        return new Bill(basket, price, priceBeforeDiscounts, discounts, totalDiscount);
    }

    private BigDecimal sumItems(final Basket basket) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Entry<String, Integer> basketEntry : basket.getItems()
                                                        .entrySet()) {
            Item item = items.getItem(basketEntry.getKey());
            BigDecimal itemPrice = item.getPrice()
                                       .multiply(BigDecimal.valueOf(basketEntry.getValue()));
            sum = sum.add(itemPrice);
        }
        return sum;
    }

    BigDecimal sumWeightedItems(final Basket basket) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Entry<String, BigDecimal> basketEntry : basket.getWeightedItems()
                                                           .entrySet()) {
            Item item = items.getItem(basketEntry.getKey());
            BigDecimal itemPrice = item.getPrice()
                                       .multiply(basketEntry.getValue())
                                       .setScale(2, RoundingMode.HALF_UP);
            sum = sum.add(itemPrice);
        }
        return sum;
    }

    private List<Discount> calculateDiscounts(final Basket basket) {
        return offers.stream()
                     .flatMap(offer -> offer.apply(basket)
                                            .stream())
                     .collect(toList());
    }

}
