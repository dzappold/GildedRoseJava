package com.acme.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


class GildedRoseShould {
	private static final int MIN_QUALITY = 0;
	private static final int MAX_QUALITY = 50;
	private final int bootSellIn = 100;
	private final int bootQuality = 1;
	private final int overdueMustardYoghurtQuality = 10;
	private Item oldBoots;
	private Item overdueMustardYoghurt;

	@BeforeEach
	void setUp() {
		oldBoots = new Item("Boot", bootSellIn, bootQuality);
		overdueMustardYoghurt = new Item("Mustard-Yoghurt", 0, overdueMustardYoghurtQuality);
	}

	@Test
	void beDecreasedByOneAtEndOfTheDayItemQuality() {
		GildedRose store = GildedRose.with(oldBoots);
		store.updateQuality();

		assertThat(oldBoots.getQuality(), is(bootQuality - 1));
	}

	@Test
	void beDecreasedByOneAtEndOfTheDayItemSellIn() {
		GildedRose store = GildedRose.with(oldBoots);
		store.updateQuality();

		assertThat(oldBoots.getSellIn(), is(bootSellIn - 1));
	}

	@Test
	void atEndOfTheDayItemQualityShouldBeDecreasedByTwoWhenSellInDateHasPassed() {
		GildedRose store = GildedRose.with(overdueMustardYoghurt);
		store.updateQuality();

		assertThat(overdueMustardYoghurt.getQuality(), is(overdueMustardYoghurtQuality - 2));
	}

	@Test
	void qualityCanNeverBeDecreasedBelowMinimumQuality() {
		Item trousers = new Item("Trousers", 1000, MIN_QUALITY);
		GildedRose store = GildedRose.with(trousers);
		store.updateQuality();

		assertThat(trousers.getQuality(), is(greaterThanOrEqualTo(MIN_QUALITY)));
	}

	@Test
	void qualityCannotExceedMaximumQuality() {
		Item goldenSunWatch = new Item("Golden Pocket Sun Watch", 14, MAX_QUALITY);
		GildedRose store = GildedRose.with(goldenSunWatch);
		store.updateQuality();
		assertThat(goldenSunWatch.getQuality(), is(not(greaterThan(MAX_QUALITY))));
	}

}
