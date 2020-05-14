package com.alexaitken.gildedrose;

public interface Item {
    int MAX_QUALITY = 50;
    int SULFURAS_QUALITY = 50;

    String getName();

    void setName(String name);

    int getSellIn();

    void setSellIn(int sellIn);

    int getQuality();

    void setQuality(int quality);

    void updateQuality();
}
