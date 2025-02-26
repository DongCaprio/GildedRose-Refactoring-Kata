package com.gildedrose;

import com.gildedrose.update.UpdateAgedBrie;
import com.gildedrose.update.UpdateBackStage;
import com.gildedrose.update.UpdateItem;
import com.gildedrose.update.UpdateNormal;
import com.gildedrose.update.UpdateSulfuras;
import java.util.HashMap;
import java.util.Map;

public enum ItemProperty {

    AGED_BRIE("Aged Brie", new UpdateAgedBrie()),
    BACK_STAGE("Backstage passes to a TAFKAL80ETC concert", new UpdateBackStage()),
    SULFURAS("Sulfuras, Hand of Ragnaros", new UpdateSulfuras()),
    NORMAL("NORMAL_ITEM", new UpdateNormal());

    private final String itemName;
    private final UpdateItem updateItem;
    private static final Map<String, UpdateItem> itemStrategies;

    ItemProperty(String itemName, UpdateItem updateItem) {
        this.itemName = itemName;
        this.updateItem = updateItem;
    }

    static {
        itemStrategies = new HashMap<>();
        for (ItemProperty itemProperty : values()) {
            itemStrategies.put(itemProperty.itemName, itemProperty.updateItem);
        }
    }

    public static UpdateItem findUpdateItem(String itemName) {
        return itemStrategies.getOrDefault(itemName, NORMAL.updateItem);
    }
}
