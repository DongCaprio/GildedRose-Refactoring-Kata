package com.gildedrose;

import com.gildedrose.update.UpdateItem;

class GildedRose {
    Item[] items;
    
    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            UpdateItem updateItem = ItemProperty.findUpdateItem(item.name);
            updateItem.updateItem(item);
        }
    }
}
