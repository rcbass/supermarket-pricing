package com.example.supermarket;

import java.math.BigDecimal;

/**
 * An item available for sale.
 *
 */
public class Item {

    private final String     code;
    private final BigDecimal price;

    /**
     * Constructs an item with the given details.
     * 
     * @param code
     *            a unique identifier for this item
     * @param price
     *            the price of this item
     */
    public Item(final String code, final BigDecimal price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
