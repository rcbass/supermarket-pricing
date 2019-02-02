package com.example.supermarket;

import java.util.List;

public class BasketPricer {

    private final Items       items;
    private final List<Offer> offers;

    public BasketPricer(final Items items, final List<Offer> offers) {
        this.items = items;
        this.offers = offers;
    }

    public Bill price(final Basket basket) {
        // TODO method stub
        return null;
    }

}
