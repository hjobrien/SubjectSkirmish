package tile;

import java.util.Random;

import event.Event;
import event.FindItem;
import event.Rarity;
import javafx.scene.paint.Color;


public abstract class Tile{
	private int x;
	private int y;
	private Color c;
	private String imagePath;
	
	public Tile(int x, int y, Color c){
		this.c = c;
		this.x = x;
		this.y = y;
	}
	
	public Tile(int x, int y, String iconPath){
		this.x = x;
		this.y = y;
		this.imagePath = iconPath;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return c;
	}
	
	@Override
	public String toString(){
		return "x:" + x + " y:" + y;
	}
	
	public Event onStep(){
		return null;
	}
	
	//returns a random rarity appropriated both by rarity and items within that rarity grouping.
	//this solves the problem i had earlier of 10$ being as likely to find as 1$ due to the fact
	//that 1$ was found 1/5 of the time 5/15 of the time, whereas 10$ was found 1/3 of the time 3/15 of the time.
	public Rarity calculateRarity(){
		int commonChance = 5 * FindItem.getCommonItemsSize();
		int uncommonChance = 4 * FindItem.getUncommonItemsSize() + commonChance;
		int rareChance = 3 * FindItem.getRareItemsSize() + uncommonChance;
		int veryRareChance = 2 * FindItem.getVeryRareItemsSize() + rareChance;
		int total = FindItem.getUniqueItemsSize() + veryRareChance;
		
		Random r = new Random();
		int rand = r.nextInt(total);
		
		if (rand < commonChance){
			return Rarity.COMMON;
		} else if (rand < uncommonChance){
			return Rarity.UNCOMMON;
		} else if (rand < rareChance){
			return Rarity.RARE;
		} else if (rand < veryRareChance){
			return Rarity.VERY_RARE;
		} else {
			return Rarity.UNIQUE;
		}	
	}

	public String getImagePath() {
		return imagePath;
	}
}
