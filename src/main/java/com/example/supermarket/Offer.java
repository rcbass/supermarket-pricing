package com.example.supermarket;

import java.util.List;

public abstract class Offer {

    private final Items items;

    protected Offer(final Items items) {
        this.items = items;
    }

    protected Items getItems() {
        return items;
    }

    public abstract List<Discount> apply(Basket basket);

}
