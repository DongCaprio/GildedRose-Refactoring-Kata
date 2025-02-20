package com.gildedrose;

class GildedRose {
    Item[] items;

    private static final String AGED_BRIE = "Aged Brie"; //시간 지나면 Quility 올라감
    private static final String BACK_STAGE = "Backstage passes to a TAFKAL80ETC concert"; // 시간 지나면 값 올라가다가 0
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros"; // 값이 아예 안떨어짐

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean agedBrie = item.name.equals(AGED_BRIE);
            boolean backStage = item.name.equals(BACK_STAGE);
            boolean sulfuras = item.name.equals(SULFURAS);
            int quality = item.quality;
            int sellIn = item.sellIn;

            if (!agedBrie && !backStage) { //일반적인 경우일때
                if (quality > 0) {
                    if (!sulfuras) {
                        quality -= 1;
                    }
                }
            } else { // quality가 상승해야될때
                if (quality < 50) {
                    quality += 1;

                    if (backStage) {
                        if (sellIn < 11) {
                            if (quality < 50) {
                                quality += 1;
                            }
                        }

                        if (sellIn < 6) {
                            if (quality < 50) {
                                quality += 1;
                            }
                        }
                    }
                }
            }

            if (!sulfuras) {
                sellIn -= 1;
            }

            if (sellIn < 0) {
                if (!agedBrie) {
                    if (!backStage) {
                        if (quality > 0) {
                            if (!sulfuras) {
                                quality -= 1;
                            }
                        }
                    } else {
                        quality = 0;
                    }
                } else {
                    if (quality < 50) {
                        quality += 1;
                    }
                }
            }
        }
    }
}
