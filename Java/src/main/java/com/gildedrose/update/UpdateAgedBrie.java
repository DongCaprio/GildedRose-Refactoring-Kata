package com.gildedrose.update;

import com.gildedrose.Item;

public class UpdateAgedBrie implements UpdateItem {

    @Override
    public Item updateItem(Item item) {

        if (item.quality < 50) {
            item.quality += 1;
        }

        item.sellIn -= 1;

        if (item.sellIn < 0) {
            if (item.quality < 50) {
                item.quality += 1;
            }
        }

        return item;
    }

}
