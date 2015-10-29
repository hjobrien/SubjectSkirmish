package event;

import java.util.Random;

import event.Rarity;
import player.Item;

public class FindItem implements Event {
	
	public static final int NUM_COMMON = 5;
	public static final int NUM_UNCOMMON = 3;
	public static final int NUM_RARE = 2;
	public static final int NUM_VERY_RARE = 3;
	public static final int NUM_UNIQUE = 1;
	//since items have names and rarities, we don't need to store those
	//fields in the FindItem class
	private Item item;
		
	/**
	 *FindItem gets a rarity passed into it and creates an item based on that rarity
	 *from a pre-existing list of items with the given rarity. the rarity is generated
	 *in the tile's onStep method and the item itself is generated here
	 * @param rarity
	 */
	public FindItem(Rarity rarity){
		Item[] items = new Item[0];
		if (rarity.equals(Rarity.COMMON)){
			items = getCommonItems();
		} else if (rarity.equals(Rarity.UNCOMMON)){
			items = getUncommonItems();
		} else if (rarity.equals(Rarity.RARE)){
			items = getRareItems();
		} else if (rarity.equals(Rarity.VERY_RARE)){
			items = getVeryRareItems();
		} else if (rarity.equals(Rarity.UNIQUE)){
			items = getUniqueItems();
		}
		Random r = new Random();
		int rand = r.nextInt(items.length);
		this.item = items[rand];
		
	}
	
	//allows us to build custom items if desired
	public FindItem(String name, Rarity rarity){
		this.item = new Item(name, rarity);
	}
	
	public Item getItem(){
		return this.item;
	}
	
	//Could potentially also differentiate between water items and grass items
	
	private static Item[] getCommonItems(){
		Item[] items = new Item[NUM_COMMON];
		items[0] = new Item("A rock", Rarity.COMMON);
		items[1] = new Item("A stick", Rarity.COMMON);
		items[2] = new Item("$1", Rarity.COMMON);
		items[3] = new Item("A feather", Rarity.COMMON);
		items[4] = new Item("Some grass", Rarity.COMMON);
		return items;
	}
	
	public static int getCommonItemsSize(){
		return NUM_COMMON;
	}
	
	private static Item[] getUncommonItems(){
		Item[] items = new Item[NUM_UNCOMMON];
		items[0] = new Item("$5", Rarity.UNCOMMON);
		items[1] = new Item("A pencil", Rarity.UNCOMMON);
		items[2] = new Item("A piece of paper", Rarity.UNCOMMON);
		return items;
	}
	
	public static int getUncommonItemsSize(){
		return NUM_UNCOMMON;
	}
	
	private static Item[] getRareItems(){
		Item[] items = new Item[NUM_RARE];
		items[0] = new Item("$10", Rarity.RARE);
		items[1] = new Item("A notebook", Rarity.RARE);
		return items;
	}
	
	public static int getRareItemsSize(){
		return NUM_RARE;
	}
	
	private static Item[] getVeryRareItems(){
		Item[] items = new Item[NUM_VERY_RARE];
		items[0] = new Item("A Norton Anthology", Rarity.VERY_RARE);
		items[1] = new Item("Antes de Ser Libres", Rarity.VERY_RARE);
		items[2] = new Item("$50", Rarity.VERY_RARE);
		return items;
	}
	
	public static int getVeryRareItemsSize(){
		return NUM_VERY_RARE;
	}
	
	private static Item[] getUniqueItems(){
		Item[] items = new Item[NUM_UNIQUE];
		items[0] = new Item("A TI-84", Rarity.UNIQUE);
		return items;
	}
	
	public static int getUniqueItemsSize(){
		return NUM_UNIQUE;
	}
	
	
}
