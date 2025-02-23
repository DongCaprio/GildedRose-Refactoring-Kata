package com.gildedrose.update;

import com.gildedrose.Item;

public class UpdateBackStage extends AbstractUpdateItem {

    @Override
    public void updateItem(Item item) {
        if (isQualityLessThan(50, item)) {
            item.quality += 1;
        }

        if (isSellInLessThan(11, item) && isQualityLessThan(50, item)) {
            item.quality += 1;
        }

        if (isSellInLessThan(6, item) && isQualityLessThan(50, item)) {
            item.quality += 1;
        }

        minusSellIn(item);

        if (isSellInLessThan(0, item)) {
            item.quality = 0;
        }
    }
}
