package com.example.supermarket;

import java.util.List;

/**
 * An offer saves the customer money if their basket meets certain criteria. For example,
 * if it contains a specific quantity of a specific item.
 *
 */
public abstract class Offer {

    private final Items items;

    protected Offer(final Items items) {
        this.items = items;
    }

    protected Items getItems() {
        return items;
    }

    /**
     * Applies this offer to the given basket and returns any resulting discounts.
     * 
     * @param basket
     *            a shopping basket
     * 
     * @return a list of discounts if the basket qualified for this offer,
     *         otherwise an empty list
     */
    public abstract List<Discount> apply(Basket basket);

}
