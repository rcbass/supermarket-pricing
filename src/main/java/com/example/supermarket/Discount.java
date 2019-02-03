package com.example.supermarket;

import java.math.BigDecimal;
import com.example.supermarket.offers.Offer;

/**
 * A discount is a saving made by the customer as a result of an {@link Offer} being applied.
 *
 */
public class Discount {

    private final Offer      offer;
    private final BigDecimal amount;

    /**
     * Constructs a {@link Discount} with the given details.
     * 
     * @param offer
     *            the offer that produced this discount
     * @param amount
     *            the amount saved by the customer
     */
    public Discount(final Offer offer, final BigDecimal amount) {
        this.offer = offer;
        this.amount = amount;
    }

    public Offer getOffer() {
        return offer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
