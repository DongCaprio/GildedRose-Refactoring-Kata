package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    @DisplayName("일반 상품의 날짜와 품질이 1만큼 감소했는지 확인")
    void qualityShouldDecreaseByOne() {
        Item[] items = new Item[]{new Item("normal", 10, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].quality); // 품질이 정확히 1만큼 감소했는지 확인
        assertEquals(9, items[0].sellIn); // 품질이 정확히 1만큼 감소했는지 확인
    }

    @Test
    @DisplayName("일반 상품의 날짜가 0보다 작아질 수 있는지 확인")
    void 날짜가_마이너스가_될까() {
        Item[] items = new Item[]{new Item("normal", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(items[0].sellIn, -1);
    }

    @Test
    @DisplayName("판매하는 나머지 일수가 없어지면, Quality 값은 2배로 떨어지는지 확인")
    void 진짜_2배로_떨어질까() {
        Item[] items = new Item[]{new Item("normal", 1, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(items[0].quality, 4);
        app.updateQuality();
        assertEquals(items[0].quality, 2);
        app.updateQuality();
        assertEquals(items[0].quality, 0);
    }

    @Test
    @DisplayName("일반 상품 품질이 0보다 낮아지지 않는지 확인")
    void qualityShouldNotGoBelowZero() {
        Item[] items = new Item[]{new Item("normal", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    @DisplayName("\"Aged Brie\"(오래된 브리치즈)은(는) 시간이 지날수록 Quality 값이 올라가는지 확인")
    void 오래된_브리치즈는_시간이_지나면_Quility가_올라감() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, items[0].quality);
    }

    @Test
    @DisplayName("Quality 값은 50를 초과 할 수 없습니다.")
    void Quality_값은_50를_초과_할_수_없습니다() {
        Item[] items = new Item[]{new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }

    @Test
    @DisplayName("BACK_STAGE가 판매일이 0보다 작아지면 quality가 0이 된다?")
    void BACK_STAGE가_판매일이_0보다_작아지면_quality가_0이_된다() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    @DisplayName("AgedBrie 판매일이 0보다 작아지면 quality가 2가 오른다?")
    void AgedBrie_판매일이_0보다_작아지면_quality가_2가_오른다() {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, items[0].quality);
    }

    @Test
    @DisplayName("Sulfuras의 Quality 값은 떨어지지 않습니다.")
    void Sulfuras의_Quality_값은_떨어지지_않습니다() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, items[0].quality);
    }

    @Test
    @DisplayName("Backstage passes(백스테이지 입장권)는 SellIn 값에 가까워 질수록 "
        + "Quality 값이 상승하고, "
        + "10일 부터는 매일 2 씩 증가하다, 5일 부터는이 되면 매일 3 씩 증가하지만, "
        + "콘서트 종료 후에는 0으로 떨어집니다.")
    void 백스테이지_값이_상승하다가_콘서트_종료하면_0이_된다() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 12, 12)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, items[0].quality);
        app.updateQuality();
        assertEquals(14, items[0].quality);
        app.updateQuality();
        assertEquals(16, items[0].quality);
        app.updateQuality();
        assertEquals(18, items[0].quality);
        app.updateQuality();
        assertEquals(20, items[0].quality);
        app.updateQuality();
        assertEquals(22, items[0].quality);
        app.updateQuality();
        assertEquals(24, items[0].quality);
        app.updateQuality();
        assertEquals(27, items[0].quality);
        app.updateQuality();
        assertEquals(30, items[0].quality);
        app.updateQuality();
        assertEquals(33, items[0].quality);
        app.updateQuality();
        assertEquals(36, items[0].quality);
        app.updateQuality();
        assertEquals(39, items[0].quality);
        app.updateQuality();
        assertEquals(0, items[0].quality);
        app.updateQuality();
        assertEquals(0, items[0].quality);
        app.updateQuality();
    }

    /*@Test
    @DisplayName("Conjured 아이템은 일반 아이템의 2배의 속도로 품질(Quality)이 저하됩니다.")
    void Conjured_아이템은_일반_아이템의_2배의_속도로_품질_Quality_이_저하됩니다() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
    }*/


}
