package com.gildedrose.update;

import com.gildedrose.Item;

public class UpdateNormal implements UpdateItem {

    @Override
    public Item updateItem(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }

        item.sellIn -= 1;

        if (item.sellIn < 0 && item.quality > 0) {
            item.quality -= 1;
        }

        return item;
    }
}
