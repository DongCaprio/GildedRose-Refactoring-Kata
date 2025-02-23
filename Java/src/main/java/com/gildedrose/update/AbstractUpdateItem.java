package com.gildedrose.update;

import com.gildedrose.Item;

public abstract class AbstractUpdateItem implements UpdateItem {
    
    public void minusSellIn(Item item) {
        item.sellIn -= 1;
    }

    public boolean isQualityLessThan(int quality, Item item) {
        return item.quality < quality;
    }

    public boolean isSellInLessThan(int sellIn, Item item) {
        return item.sellIn < sellIn;
    }

    public boolean isQualityMoreThan(int quality, Item item) {
        return item.quality > quality;
    }
}
