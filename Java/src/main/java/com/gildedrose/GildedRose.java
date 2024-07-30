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


    public void updateQuality() {
        for (Item item : items) {
            int newQuality = 0;
            String itemName  = item.name;
            newQuality = getNewQuality(item, itemName);
            item.quality = newQuality;
            if (itemName.contains(SULFURAS)) continue;
            item.sellIn--;
        }
    }

    private int getNewQuality(Item item, String itemName) {
        int newQuality;
        if (itemName.contains(AGED_BRIE)) {
            newQuality = getAgedBrieQuality(item);
        } else if (itemName.contains(BACKSTAGE_PASSES)) {
            newQuality = getBackstagePassQuality(item);
        } else if (itemName.contains(SULFURAS)) {
            newQuality = getSulfurasQuality(item);
        } else if (itemName.contains(CONJURED)) {
            newQuality = getConjuredQuality(item);
        } else {
            newQuality = getDefaultQuality(item);
        }
        return newQuality;
    }

    private int getAgedBrieQuality(Item item) {
        if (item.sellIn <= 0)
            return Math.min(item.quality + 2, 50);

        return Math.min(item.quality + 1, 50); // sellIn > 0
    }

    private int getBackstagePassQuality(Item item) {
        int sellIn = item.sellIn;

        if (sellIn > 10)
            return Math.min(item.quality + 1, 50);

        if (sellIn <= 10 && sellIn > 5)
            return Math.min(item.quality + 2, 50);

        if (sellIn <= 5 && sellIn > 0)
            return Math.min(item.quality + 3, 50);

        return 0; // sellIn <= 0
    }

    private int getSulfurasQuality(Item item) {
        return item.quality;
    }

    private int getConjuredQuality(Item item) {
        int weight = 1;
        if (item.sellIn == 0) weight = 2;
        return Math.max(item.quality - (2 * weight), 0);
    }

    private int getDefaultQuality(Item item) {
        int weight = 1;
        if (item.sellIn == 0) weight = 2;
        return Math.max(item.quality - (1 * weight), 0);
    }

}
