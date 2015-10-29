package player;

import event.Rarity;
import javafx.scene.image.Image;

public class Item {

	
	private String name;
	private Rarity rarity;
	private Image icon = new Image(getClass().getResourceAsStream("defaultImage.png"));
	
	public static final int ICON_SIZE = 25; //used for item output in inventory, i need to know how bug each item is. this restricts the size of icon we can use.
	
	public Item(String name, Rarity rarity){
		this.name = name;
		this.rarity = rarity;
	}
	
	public Item(String name, Rarity rarity, String iconFile){
		this.name = name;
		this.rarity = rarity;
		//testing for inventory
//		this.icon = new Image(getClass().getResourceAsStream(iconFile));
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
	
	public Image getIcon() {
		return icon;
	}

}
