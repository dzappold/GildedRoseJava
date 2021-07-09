package com.acme.gildedrose;


import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Property(tries = 10_000)
    void generic(@ForAll("itemNames") String name, @ForAll @IntRange(min = -500, max = 500) Integer sellIn, @ForAll @IntRange(min = -500, max = 500) Integer quality) {
        Item item = new Item(name, sellIn, quality);
        GildedRose with = GildedRose.with(item);
        with.updateQuality();
        assertThat(with.items.get(0).getName()).isEqualTo(name);
        assertThat(with.items.get(0).getSellIn()).isLessThanOrEqualTo(sellIn);
        assertThat(with.items.get(0).getQuality()).isLessThanOrEqualTo(50);
    }

    @Provide
    Arbitrary<String> itemNames() {
        return Arbitraries.of("Aged Brie", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "7kyy6s");
    }
}
