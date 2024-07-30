package com.gildedrose;

class GildedRose {
    Item[] items;

    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASSES = "Backstage passes";
    private final String SULFURAS = "Sulfuras";
    private final String CONJURED = "Conjured";


    public GildedRose(Item[] items) {
        this.items = items;
    }


    /**
     * 1. 특별 물건 / 일반 물건 구분
     * 2. sellIn 1 감소
     * 3. Quality 1 감소
     * */
    public void updateQuality() {
        for (Item item : items) {
            String itemName  = item.name;
            if (itemName.contains(AGED_BRIE)) {
                calAgedBrie(item);
            } else if (itemName.contains(BACKSTAGE_PASSES)) {
                calBackstagePass(item);
            } else if (itemName.contains(SULFURAS)) {
                calSulfuras(item);
            } else if (itemName.contains(CONJURED)) {
                calConjured(item);
            } else {
                calDefault(item);
            }

            if (itemName.contains(SULFURAS)) continue;
            item.sellIn--;
        }
    }

    /**
     * 공통 :
     * Q는 음수가 안된다.
     * Q의 최대는 50이다.
     * SellIn이 0이면, Q는 2배로 떨어진다.
     */

    /**
     * Q +1
     */
    private void calAgedBrie(Item item) {
        if (item.sellIn <= 0) {
            item.quality = Math.min(item.quality + 2, 50);
        } else if (item.sellIn > 0) {
            item.quality = Math.min(item.quality + 1, 50);
        }
    }

    /**
     * Q + 1
     * 10 -> +2
     * 5 -> + 3
     * 0 -> q = 0
     */
    private void calBackstagePass(Item item) {
        int sellIn = item.sellIn;
        if (sellIn > 10) {
            item.quality = Math.min(item.quality + 1, 50);
        } else if (sellIn <= 10 && sellIn > 5) {
            item.quality = Math.min(item.quality + 2, 50);
        } else if (sellIn <= 5 && sellIn > 0) {
            item.quality = Math.min(item.quality + 3, 50);
        } else if (sellIn == 0) {
            item.quality = 0;
        }
    }


    private void calSulfuras(Item item) {

    }

    private void calConjured(Item item) {
        int weight = 1;
        if (item.sellIn == 0) {
            weight *= 2;
        }
        item.quality = Math.max(item.quality - (2 * weight), 0);
    }

    private void calDefault(Item item) {
        int weight = 1;
        if (item.sellIn == 0) {
            weight *= 2;
        }
        item.quality = Math.max(item.quality - (1 * weight), 0);
    }

}
