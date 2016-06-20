package com.improuv.gildedrose;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		GildedRose app = new GildedRose();

		app.items = new ArrayList<Item>();
		app.items.add(new Item("+5 Dexterity Vest", 10, 20));
		app.items.add(new Item("Aged Brie", 2, 0));
		app.items.add(new Item("Elixir of the Mongoose", 5, 7));
		app.items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		app.items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15,
				20));
		app.items.add(new Item("Conjured Mana Cake", 3, 6));

		app.updateQuality();
	}

}
