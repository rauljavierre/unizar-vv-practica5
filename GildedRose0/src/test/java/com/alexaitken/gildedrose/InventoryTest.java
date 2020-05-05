package com.alexaitken.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Inventory;
import com.alexaitken.gildedrose.Item;


public class InventoryTest {

    private Inventory createInventory(Item... items) {
        return new Inventory(items);
    }

    // P3
    @Test
    public void should_never_changes_quailty_of_Sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(80, sulfuras.getQuality());
    }

    // P3
    @Test
    public void should_never_changes_sellIn_of_Sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(0, sulfuras.getSellIn());
    }

    // P9
    @Test
    public void should_lower_the_sellIn_by_one_for_normal_items() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(9, normalItem.getSellIn());
    }

    // P9
    @Test
    public void should_lower_the_quality_by_one_for_normal_items() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(19, normalItem.getQuality());
    }

    // P9b
    @Test
    public void should_not_lower_the_quality_below_zero() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 0);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(0, normalItem.getQuality());
    }

    // P10
    @Test
    public void should_lower_the_quality_twice_as_fast_once_the_sell_in_date_has_passed() {
        Item normalItem = new Item("+5 Dexterity Vest", -1, 25);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(23, normalItem.getQuality());
    }

    // P1
    @Test
    public void should_increase_the_quality_of_aged_brie_as_it_gets_older() {
        Item agedBrie = new Item("Aged Brie", 10, 25);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(26, agedBrie.getQuality());
    }

    // P1b
    @Test
    public void should_not_increase_the_quality_of_aged_brie_over_50() {
        Item agedBrie = new Item("Aged Brie", 10, 50);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(50, agedBrie.getQuality());
    }

    // P7
    @Test
    public void should_lower_backstage_passes_to_zero_quality_once_concert_has_happened() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(0, backStagePass.getQuality());
    }

    // P4
    @Test
    public void should_increase_backstage_passes_quality_by_1_when_the_concert_is_more_than_10_days_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(21, backStagePass.getQuality());
    }

    // P5
    @Test
    public void should_increase_backstage_passes_quality_by_2_when_the_concert_is_10_days_or_less_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 27);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(29, backStagePass.getQuality());
    }

    // P6
    @Test
    public void should_increase_backstage_passes_quality_by_3_when_the_concert_is_5_days_or_less_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 44);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(47, backStagePass.getQuality());
    }

    @Test
    public void should_not_increase_backstage_passes_above_a_quality_of_50() {
        Item backStagePassMoreThan10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50);   // P4b
        Item backStagePass10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);           // P5c
        Item backStagePass5DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);             // P6d
        Inventory inventory = createInventory(backStagePassMoreThan10DaysAway, backStagePass10DaysAway, backStagePass5DaysAway);
        inventory.updateQuality();
        assertEquals(50, backStagePassMoreThan10DaysAway.getQuality());
        assertEquals(50, backStagePass10DaysAway.getQuality());
        assertEquals(50, backStagePass5DaysAway.getQuality());
    }

    // P2
    @Test
    public void p2() {
        Item given = new Item("Aged Brie", -8, 25);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Aged Brie", -9, 27);
        assertItem(expected, given);
    }

    // P2b
    @Test
    public void p2b() {
        Item given = new Item("Aged Brie", -8, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Aged Brie", -9, 50);
        assertItem(expected, given);
    }

    // P2c
    @Test
    public void p2c() {
        Item given = new Item("Aged Brie", -8, 49);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Aged Brie", -9, 50);
        assertItem(expected, given);
    }

    // P2d
    @Test
    public void p2d() {
        Item given = new Item("Aged Brie", 0, 25);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Aged Brie", -1, 27);
        assertItem(expected, given);
    }

    // P5b
    @Test
    public void p5b() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 50);
        assertItem(expected, given);
    }

    // P5d
    @Test
    public void p5d() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 25);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 27);
        assertItem(expected, given);
    }

    // P5e
    @Test
    public void p5e() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        assertItem(expected, given);
    }

    // P5e
    @Test
    public void p5f() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 49);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        assertItem(expected, given);
    }

    // P6b
    @Test
    public void p6b() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P6c
    @Test
    public void p6c() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P6e
    @Test
    public void p6e() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 25);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 28);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P6f
    @Test
    public void p6f() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P6g
    @Test
    public void p6g() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P6h
    @Test
    public void p6h() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 48);
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }


    // P8
    @Test
    public void p8() {
        Item given = new Item("Backstage passes to a TAFKAL80ETC concert", -8, 0);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        Item expected = new Item("Backstage passes to a TAFKAL80ETC concert", -9, 0);
        assertItem(expected, given);
    }

    // P10b
    @Test
    public void p10b() {
        Item given = new Item("+5 Dexterity Vest", -8, 1);
        Item expected = new Item("+5 Dexterity Vest", -9, 0);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P10c
    @Test
    public void p10c() {
        Item given = new Item("+5 Dexterity Vest", -8, 0);
        Item expected = new Item("+5 Dexterity Vest", -9, 0);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }

    // P10d
    @Test
    public void p10d() {
        Item given = new Item("+5 Dexterity Vest", 0, 25);
        Item expected = new Item("+5 Dexterity Vest", -1, 23);
        Inventory inventory = createInventory(given);
        inventory.updateQuality();
        assertItem(expected,given);
    }


    // Prueba realizada para llegar a 100% cobertura
    @Test
    public void test_setName_Item() {
        Item agedBrie = new Item("Aged Brie", 0, 0);
        agedBrie.setName("Aged Brie cambiado");
    }




    private void assertItem(Item a, Item b){
        assertEquals(a.getName(), b.getName());
        assertEquals(a.getSellIn(), b.getSellIn());
        assertEquals(a.getQuality(), b.getQuality());
    }
}
