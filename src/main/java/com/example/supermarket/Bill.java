package com.example.supermarket;

import java.math.BigDecimal;
import java.util.List;

/**
 * The pricing details of a {@link Basket}. For example the total cost
 * and the discounts applied.
 *
 */
public class Bill {

    private final Basket         basket;
    private final BigDecimal     price;
    private final BigDecimal     priceBeforeDiscounts;
    private final List<Discount> discounts;
    private final BigDecimal     totalDiscount;

    /**
     * Constructs a {@link Bill} with the given pricing details.
     * 
     * @param basket
     *            the basket for which this bill is for
     * @param price
     *            the final price payable by the customer
     * @param priceBeforeDiscounts
     *            the price before any discounts were applied
     * @param discounts
     *            a list of the discounts that were applied
     * @param totalDiscount
     *            the total amount discounted
     */
    public Bill(final Basket basket, final BigDecimal price, final BigDecimal priceBeforeDiscounts,
            final List<Discount> discounts, final BigDecimal totalDiscount) {
        this.basket = basket;
        this.price = price;
        this.priceBeforeDiscounts = priceBeforeDiscounts;
        this.discounts = discounts;
        this.totalDiscount = totalDiscount;
    }

    public Basket getBasket() {
        return basket;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceBeforeDiscounts() {
        return priceBeforeDiscounts;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

}
