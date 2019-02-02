package com.example.supermarket;

import static java.util.Collections.unmodifiableMap;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private final Map<String, Integer>    items;
    private final Map<String, BigDecimal> weightedItems;

    public Basket() {
        items = new HashMap<>();
        weightedItems = new HashMap<>();
    }

    public void addItem(final String itemCode) {
        if (items.containsKey(itemCode)) {
            int quantity = items.get(itemCode) + 1;
            items.put(itemCode, quantity);
        } else {
            items.put(itemCode, 1);
        }
    }

    public void addWeightedItem(final String itemCode, final BigDecimal weight) {
        if (weightedItems.containsKey(itemCode)) {
            BigDecimal totalWeight = weightedItems.get(itemCode)
                                                  .add(weight);
            weightedItems.put(itemCode, totalWeight);
        } else {
            weightedItems.put(itemCode, weight);
        }
    }

    public Map<String, Integer> getItems() {
        return unmodifiableMap(items);
    }

    public Map<String, BigDecimal> getWeightedItems() {
        return unmodifiableMap(weightedItems);
    }

}
