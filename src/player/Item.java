package player;

import event.Rarity;
import javafx.scene.image.Image;

public class Item {

	//testing
	private String name;
	private Rarity rarity;
	private Image icon = new Image("/images/defaultImage.png" , true);
	private String iconName;
	
	public static final int ICON_SIZE = 75; //used for item output in inventory, i need to know how big each item is. this restricts the size of icon we can use.
	
	public Item(String name, Rarity rarity){
		this.name = name;
		this.rarity = rarity;
	}
	
	public Item(String name, Rarity rarity, String iconFile){
		this.name = name;
		this.rarity = rarity;
		//testing for inventory
		//source path, width, height, keep aspect ratio, smooth
		this.icon = new Image("/images/" + iconFile, ICON_SIZE, ICON_SIZE, false, true);
		this.iconName = "images/" + iconFile;
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
	
	public String getIconName() {
		return iconName;
	}

	public Image getIcon() {
		return icon;
	}

}
