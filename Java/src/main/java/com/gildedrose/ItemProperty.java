package com.gildedrose;

import com.gildedrose.update.UpdateAgedBrie;
import com.gildedrose.update.UpdateBackStage;
import com.gildedrose.update.UpdateItem;
import com.gildedrose.update.UpdateNormal;
import com.gildedrose.update.UpdateSulfuras;
import java.util.Arrays;

public enum ItemProperty {

    AGED_BRIE("Aged Brie", new UpdateAgedBrie()),
    BACK_STAGE("Backstage passes to a TAFKAL80ETC concert", new UpdateBackStage()),
    SULFURAS("Sulfuras, Hand of Ragnaros", new UpdateSulfuras()),
    NORMAL("", new UpdateNormal());

    private String itemName;
    private UpdateItem updateItem;

    ItemProperty(String itemName, UpdateItem updateItem) {
        this.itemName = itemName;
        this.updateItem = updateItem;
    }

    public static UpdateItem findUpdateItem(String itemName) {
        return Arrays.stream(ItemProperty.values())
            .filter(item -> item.itemName.equals(itemName))
            .findFirst()
            .map(item -> item.updateItem)
            .orElse(NORMAL.updateItem);
    }


}
