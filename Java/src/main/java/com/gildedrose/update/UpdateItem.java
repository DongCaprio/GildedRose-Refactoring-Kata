package com.gildedrose.update;

import com.gildedrose.Item;

public interface UpdateItem {

    int DEFAULT_DECREMENT = 1;

    void updateItem(Item item);
}
