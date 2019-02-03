package com.example.supermarket;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents all items available for sale.
 *
 */
public class Items {

    private final Map<String, Item> items;

    /**
     * Constructs an initially empty {@link Items} instance.
     */
    public Items() {
        items = new HashMap<>();
    }

    /**
     * Adds the given item to the collection of items available for sale. If the
     * item is already available, it is updated.
     * 
     * @param item
     *            an item
     */
    public void putItem(final Item item) {
        items.put(item.getCode(), item);
    }

    /**
     * Gets the item with the given code.
     * 
     * @param code
     *            the item code
     * 
     * @return the item with the given code if there is one available for
     *         sale, otherwise null
     */
    public Item getItem(final String code) {
        return items.get(code);
    }

}
