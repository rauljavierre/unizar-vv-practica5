package com.alexaitken.gildedrose;

public class DefaultItem implements Item {

    private String name;
    private int sellIn;

    private int quality;

    public DefaultItem(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSellIn() {
        return sellIn;
    }

    @Override
    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public void updateQuality(){
        if (!getName().equals("Aged Brie")
                && !getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (getQuality() > 0) {
                if (!getName().equals("Sulfuras, Hand of Ragnaros")) {
                    setQuality(getQuality() - 1);
                }
            }
        } else {
            if (getQuality() < MAX_QUALITY) {
                setQuality(getQuality() + 1);

                if (getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (getSellIn() < 11) {
                        if (getQuality() < MAX_QUALITY) {
                            setQuality(getQuality() + 1);
                        }
                    }

                    if (getSellIn() < 6) {

                        if (getQuality() < MAX_QUALITY) {
                            setQuality(getQuality() + 1);
                        }
                    }
                }
            }
        }

        if (!getName().equals("Sulfuras, Hand of Ragnaros")) {
            setSellIn(getSellIn() - 1);
        }

        if (getSellIn() < 0) {
            if (!getName().equals("Aged Brie")) {
                if (!getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (getQuality() > 0) {
                        if (!getName().equals("Sulfuras, Hand of Ragnaros")) {
                            setQuality(getQuality() - 1);
                        }
                    }
                } else {
                    setQuality(0);
                }
            } else {
                if (getQuality() < MAX_QUALITY) {
                    setQuality(getQuality() + 1);
                }
            }
        }
    }
}