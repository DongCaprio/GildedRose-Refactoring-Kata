package com.gildedrose.update;

import com.gildedrose.Item;

public class UpdateBackStage implements UpdateItem {

    @Override
    public Item updateItem(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }

        if (item.sellIn < 11) {
            if (item.quality < 50) {
                item.quality += 1;
            }
        }

        if (item.sellIn < 6) {
            if (item.quality < 50) {
                item.quality += 1;
            }
        }

        item.sellIn -= 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }

        return item;
    }
}
