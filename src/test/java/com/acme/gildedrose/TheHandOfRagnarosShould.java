package com.acme.gildedrose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;


class TheHandOfRagnarosShould {

	@Test
	void shouldNotDecreaseInQuality() {
		Item sulfura = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		GildedRose store = GildedRose.with(sulfura);
		store.updateQuality();
		assertThat(sulfura.getQuality(), is(greaterThanOrEqualTo(80)));
	}

	@Test
	void shouldNotGetOlder() {
		Item sulfura = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
		GildedRose store = GildedRose.with(sulfura);
		store.updateQuality();
		assertThat(sulfura.getSellIn(), is(10));
	}

}
