package com.example.supermarket.offers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.example.supermarket.Basket;
import com.example.supermarket.Discount;
import com.example.supermarket.Items;

/**
 * An offer where the customer receives a higher quantity of an item than they pay for.
 * For example, three cans of beans for the price of two.
 *
 */
public class MoreForThePriceOfLessOffer extends Offer {

    private final String itemCode;
    private final int    more;
    private final int    less;

    /**
     * Constructs an offer with the given details.
     * 
     * @param items
     *            the items available for sale
     * @param itemCode
     *            the code of the item that this offer is applicable to
     * @param more
     *            the "more" quantity in "more for the price of less"
     * @param less
     *            the "less" quantity in "more for the price of less"
     */
    public MoreForThePriceOfLessOffer(final Items items, final String itemCode, final int more, final int less) {
        super(items);
        this.itemCode = itemCode;
        this.more = more;
        this.less = less;
    }

    @Override
    public List<Discount> apply(Basket basket) {
        if (!basket.getItems()
                   .containsKey(itemCode)) {
            return new ArrayList<>();
        }

        int itemCount = basket.getItems()
                              .get(itemCode);
        int discountCount = itemCount / more;
        if (discountCount == 0) {
            return new ArrayList<>();
        }

        BigDecimal individualItemPrice = getItems().getItem(itemCode)
                                                   .getPrice();

        BigDecimal priceBeforeDiscount = individualItemPrice.multiply(BigDecimal.valueOf(more));
        BigDecimal priceAfterDiscount = individualItemPrice.multiply(BigDecimal.valueOf(less));
        BigDecimal discountAmount = priceBeforeDiscount.subtract(priceAfterDiscount);

        List<Discount> discounts = new ArrayList<>();
        for (int i = 0; i < discountCount; i++) {
            discounts.add(new Discount(this, discountAmount));
        }
        return discounts;
    }

}
