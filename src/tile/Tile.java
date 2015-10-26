package tile;

import java.util.Random;

import event.Event;
import event.Rarity;
import javafx.scene.paint.Color;
import event.FindItem;


public abstract class Tile{
	private int x;
	private int y;
	private Color c;
	
	public Tile(int x, int y, Color c){
		this.c = c;
		this.x = x;
		this.y = y;
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
	
	public abstract String toString();
	
	public Event onStep(){
		return null;
	}
	
	//returns a random rarity appropriated both by rarity and items within that rarity grouping.
	//this solves the problem i had earlier of 10$ being as likely to find as 1$ due to the fact
	//that 1$ was found 1/5 of the time 5/15 of the time, whereas 10$ was found 1/3 of the time 3/15 of the time.
	public Rarity calculateRarity(){
		FindItem f = new FindItem("Placeholder", Rarity.COMMON); //super gross coding, but the only way i found to access the findItem class
		int commonChance = 5 * f.getCommonItemsSize();
		int uncommonChance = 4 * f.getUncommonItemsSize() + commonChance;
		int rareChance = 3 * f.getRareItemsSize() + uncommonChance;
		int veryRareChance = 2 * f.getVeryRareItemsSize() + rareChance;
		int total = f.getUniqueItemsSize() + veryRareChance;
		
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
}
