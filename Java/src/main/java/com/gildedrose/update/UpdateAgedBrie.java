package com.gildedrose.update;

import com.gildedrose.Item;

public class UpdateAgedBrie extends AbstractUpdateItem {

    @Override
    public void updateItem(Item item) {
        minusSellIn(item);

        if (isQualityLessThan(50, item)) {
            item.quality += 1;
        }

        if (isSellInLessThan(0, item) && isQualityLessThan(50, item)) {
            item.quality += 1;
        }

    }

}
