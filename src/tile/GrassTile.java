package tile;

import java.util.Random;

import event.Event;
import event.FindItem;
import event.SpawnGrassMonster;
import javafx.scene.paint.Color;

public class GrassTile extends Tile implements Stepable{
	
	private static double chanceOfItemEncounter = 0.20;
	private static double chanceOfCreatureEncounter = 0.20;
	private static Color defaultColor = Color.RED;//Color.GREEN;

	public GrassTile(int x,int y) {
		super(x, y, defaultColor);
	}
	public GrassTile(int x, int y, String iconPath){
		super(x, y, iconPath);
	}

	@Override
	public Event onStep() {
		Random r = new Random();
		double chance = r.nextDouble();
		if (chance <= chanceOfItemEncounter){
			return new FindItem(super.calculateRarity());	//can be changed based on actual rarity of rare
		} else if (chance <= chanceOfCreatureEncounter + chanceOfItemEncounter){ //account for full probability
			return new SpawnGrassMonster();
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Grass";
	}

}
