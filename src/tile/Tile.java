package tile;

import java.util.Random;

import event.Event;
import event.Rarity;
import javafx.scene.paint.Color;

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
	
	public Rarity calculateRarity(){
		Random r = new Random();
		int rand = r.nextInt(15);
		if (rand < 5){
			return Rarity.COMMON;
		} else if (rand < 9){
			return Rarity.UNCOMMON;
		} else if (rand < 12){
			return Rarity.RARE;
		} else if (rand < 14){
			return Rarity.VERY_RARE;
		} else {
			return Rarity.UNIQUE;
		}	
	}
}
