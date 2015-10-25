package player;

import event.Rarity;

public class Item {

	
	private String name;
	private Rarity rarity;
	
	public Item(String name, Rarity rarity){
		this.name = name;
		this.rarity = rarity;
	}

	public String toString (){
		return name + " "  + rarity;
	}
	
	//getters for later use in the bag of the player, etc
	public String getName() {
		return name;
	}

	public Rarity getRarity() {
		return rarity;
	}

}
