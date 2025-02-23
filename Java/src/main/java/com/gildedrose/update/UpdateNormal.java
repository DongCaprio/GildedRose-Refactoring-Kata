package com.gildedrose.update;

import com.gildedrose.Item;

public class UpdateNormal extends AbstractUpdateItem {

    @Override
    public void updateItem(Item item) {
        
        minusSellIn(item);

        if (isQualityMoreThan(0, item)) {
            item.quality -= 1;
        }

        if (isSellInLessThan(0, item) && isQualityMoreThan(0, item)) {
            item.quality -= 1;
        }

    }
}
