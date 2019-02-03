package com.example.supermarket;

import static java.util.Collections.unmodifiableMap;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * A shopping basket.
 *
 */
public class Basket {

    private final Map<String, Integer>    items;
    private final Map<String, BigDecimal> weightedItems;

    /**
     * Constructs an initially empty basket.
     */
    public Basket() {
        items = new HashMap<>();
        weightedItems = new HashMap<>();
    }

    /**
     * Adds one item with the given code to this basket.
     * 
     * @param itemCode
     *            the item code
     */
    public void addItem(final String itemCode) {
        addItem(itemCode, 1);
    }

    /**
     * Adds the specified quantity of an item with the given code to this basket.
     * 
     * @param itemCode
     *            the item code
     * @param quantity
     *            the quantity to add
     */
    public void addItem(final String itemCode, int quantity) {
        if (items.containsKey(itemCode)) {
            int newQuantity = items.get(itemCode) + quantity;
            items.put(itemCode, newQuantity);
        } else {
            items.put(itemCode, quantity);
        }
    }

    /**
     * Adds the specified amount of a weighted item with the given code to this basket.
     * 
     * @param itemCode
     *            the item code
     * @param weight
     *            the weight to add
     */
    public void addWeightedItem(final String itemCode, final BigDecimal weight) {
        if (weightedItems.containsKey(itemCode)) {
            BigDecimal totalWeight = weightedItems.get(itemCode)
                                                  .add(weight);
            weightedItems.put(itemCode, totalWeight);
        } else {
            weightedItems.put(itemCode, weight);
        }
    }

    /**
     * Gets an unmodifiable view of the items in this basket.
     * 
     * @return a Map of item code to quantity
     */
    public Map<String, Integer> getItems() {
        return unmodifiableMap(items);
    }

    /**
     * Gets an unmodifiable view of the weighted items in this basket.
     * 
     * @return a Map of item code to weight
     */
    public Map<String, BigDecimal> getWeightedItems() {
        return unmodifiableMap(weightedItems);
    }

}
