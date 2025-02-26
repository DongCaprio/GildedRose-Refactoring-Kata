package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    @DisplayName("일반 상품의 날짜와 품질이 1만큼 감소했는지 확인")
    void qualityShouldDecreaseByOne() {
        Item[] items = new Item[]{new Item("normal", 10, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertAll(
            () -> assertEquals(4, items[0].quality),
            () -> assertEquals(9, items[0].sellIn)
        );
    }

    @Test
    @DisplayName("일반 상품의 날짜가 마이너스가 될 수 있다")
    void 날짜가_0보다_작아진다() {
        Item[] items = new Item[]{new Item("normal", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    @DisplayName("판매하는 나머지 일수가 없어지면, Quality 값은 2배로 떨어짐")
    void 판매하는_나머지_일수가_없어지면_Quality_값은_2배로_떨어짐() {
        Item[] items = new Item[]{new Item("normal", 1, 5)};
        GildedRose app = new GildedRose(items);
        assertAll(
            () -> assertEquals(4, updateAndGetQuality(app, items)),
            () -> assertEquals(2, updateAndGetQuality(app, items)),
            () -> assertEquals(0, updateAndGetQuality(app, items))
        );
    }

    @Test
    @DisplayName("일반 상품 품질이 0보다 낮아지지 않는다")
    void qualityShouldNotGoBelowZero() {
        Item[] items = new Item[]{new Item("normal", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    @DisplayName("\"Aged Brie\"(오래된 브리치즈)은(는) 시간이 지날수록 Quality 값이 올라간다")
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
    @DisplayName("AgedBrie 판매일이 0보다 작아지면 quality가 2가 오른다")
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

    @Nested
    @DisplayName("백스테이지 테스트")
    class backStageTest {

        private Item[] items;
        private GildedRose app;

        @Test
        @DisplayName("SellIn 가까워질수록 Quality 값 상승")
        void Qulity_1_상승() {
            items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 12, 12)};
            app = new GildedRose(items);
            assertEquals(13, updateAndGetQuality(app, items));
            assertEquals(14, updateAndGetQuality(app, items));
        }

        @Test
        @DisplayName("D-10일 부터는 Quality 값 2 증가")
        void Qulity_2_상승() {
            items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 14)};
            app = new GildedRose(items);
            assertAll(
                () -> assertEquals(16, updateAndGetQuality(app, items)),
                () -> assertEquals(18, updateAndGetQuality(app, items)),
                () -> assertEquals(20, updateAndGetQuality(app, items)),
                () -> assertEquals(22, updateAndGetQuality(app, items)),
                () -> assertEquals(24, updateAndGetQuality(app, items))
            );
        }

        @Test
        @DisplayName("D-5일 부터는 Quality 값 3 증가")
        void Qulity_3_상승() {
            items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 24)};
            app = new GildedRose(items);
            assertAll(
                () -> assertEquals(27, updateAndGetQuality(app, items)),
                () -> assertEquals(30, updateAndGetQuality(app, items)),
                () -> assertEquals(33, updateAndGetQuality(app, items)),
                () -> assertEquals(36, updateAndGetQuality(app, items)),
                () -> assertEquals(39, updateAndGetQuality(app, items))
            );
        }

        @Test
        @DisplayName("콘서트 종료 후에는 Quality 값 0이 된다 ( 그 후에도 0 유지)")
        void 콘서트_끝나면_Quality_0으로_변경() {
            items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 42)};
            app = new GildedRose(items);
            assertAll(
                () -> assertEquals(0, updateAndGetQuality(app, items), "콘서트 종료 후"),
                () -> assertEquals(0, updateAndGetQuality(app, items), "콘서트 종료 후 유지"));
        }
    }

    private int updateAndGetQuality(GildedRose app, Item[] items) {
        app.updateQuality();
        return items[0].quality;
    }

    /* 설명에는 쓰여있는데 실제 코드에는 구현 안되어있음 제외!
    @Test
    @DisplayName("Conjured 아이템은 일반 아이템의 2배의 속도로 품질(Quality)이 저하됩니다.")
    void Conjured_아이템은_일반_아이템의_2배의_속도로_품질_Quality_이_저하됩니다() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, items[0].quality);
    }
    */


}
