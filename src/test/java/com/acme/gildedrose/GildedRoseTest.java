package com.acme.gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


class GildedRoseTest {
    private static final int ITEMS_COUNT = 10000;
    private Random random;

    @BeforeEach
    public void initialize() {
        random = new Random(4711);
    }

    @Test
    void explore() {
        CombinationApprovals.verifyAllCombinations(this::updateInventory,
                new String[]{"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"},
                new Integer[]{-1, 0, 2, 6, 11},
                new Integer[]{0, 1, 49, 50});
    }

    private String updateInventory(String name, int sellIn, int quality) {
        GildedRose inventory = GildedRose.with(new Item(name, sellIn, quality));
        inventory.updateQuality();
        return inventory.items.get(0).toString();
    }

    @Test
    void approval() {
        // arrange
        GildedRose inventory = new GildedRose();
        inventory.items = generateItems();

        // act
        inventory.updateQuality();

        // assert
        String result = inventory.items.
                stream()
                .map(this::toString)
                .collect(Collectors.joining());

        Approvals.verify(result);
    }

    private String toString(Item item) {
        return String.format("name: %s, sellIn: %d, quality: %d\n", item.name, item.sellIn, item.quality);
    }

    private List<Item> generateItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < ITEMS_COUNT; i++) {
            items.add(new Item(randomName(), randomSellIn(-10, 50), randomQuality(-10, 80)));
        }
        return items;
    }

    private int randomQuality(int minimum, int maximum) {
        return minimum + random.nextInt(maximum - minimum);
    }

    private int randomSellIn(int minimum, int maximum) {
        return minimum + random.nextInt(maximum - minimum);
    }

    private String randomName() {
        String[] names = {"Aged Brie",
                "+5 Dexterity Vest",
                "Elixir of the Mongoose",
                "Sulfuras, Hand of Ragnaros",
                "Backstage passes to a TAFKAL80ETC concert",
                "Conjured Mana Cake"};
        return names[random.nextInt(names.length)];
    }
}
