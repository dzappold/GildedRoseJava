package com.acme.gildedrose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;


class ABackstagePassShould {

	private static final int MIN_QUALITY = 0;

	@Test
	void increaseQualityByOneWith11DaysLeft() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(31));
	}

	@Test
	void increaseQualityByTwoWith10DaysLeft() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 30);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(32));
	}

	@Test
	void increaseQualityByTwoWith6DaysLeft() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 30);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(32));
	}

	@Test
	void increaseQualityByThreeWith5DaysLeft() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(33));
	}

	@Test
	void haveZeroQualityWhenTheConcertIsOver() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 30);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(MIN_QUALITY));
	}

	@Test
	void qualityCannotExceedMaximumQuality() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(not(greaterThan(50))));
	}

	@Test
	void qualityCannotExceedMinimumQuality() {
		Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, -50);
		GildedRose store = GildedRose.with(backstagePass);
		store.updateQuality();
		assertThat(backstagePass.getQuality(), is(lessThan(0)));
	}
}
