package com.alexaitken.gildedrose;

public class DefaultInventory implements Inventory {

    private final Item[] items;

    public DefaultInventory(Item[] items) {
        this.items = items;
    }

    @Override
    public void updateQuality() {
        for (Item item : items) {
            item.updateQuality();
        }
    }
}
