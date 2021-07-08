package com.acme.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


class AnAgedBrieShould {

	private final int oldQuality = 15;
	private Item agedBrie;

	@BeforeEach
	void setUp() {
		agedBrie = new Item("Aged Brie", 0, oldQuality);
	}

	@Test
	void increaseInQualityWhileAging() {
		agedBrie.setSellIn(15);
		GildedRose store = GildedRose.with(agedBrie);
		store.updateQuality();

		assertThat(agedBrie.getQuality(), is(oldQuality + 1));
	}

	@Test
	void doubleItsQualityIncreaseWhenOverdue() {
		agedBrie.setSellIn(-5);
		GildedRose store = GildedRose.with(agedBrie);
		store.updateQuality();

		assertThat(agedBrie.getQuality(), is(oldQuality + 2));
	}

	@Test
	void qualityCannotExceedMaximumQuality() {
		agedBrie.setQuality(50);
		GildedRose store = GildedRose.with(agedBrie);
		store.updateQuality();
		assertThat(agedBrie.getQuality(), is(not(greaterThan(50))));
	}
}
