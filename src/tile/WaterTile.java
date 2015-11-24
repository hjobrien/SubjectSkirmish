package tile;

import java.util.Random;

import event.Event;
import event.FindItem;
import event.SpawnWaterMonster;
import javafx.scene.paint.Color;

public class WaterTile extends Tile implements Stepable{

	private static double chanceOfItemEncounter = 0.2; //0.0;
	private static double chanceOfCreatureEncounter = 0.05;//0.1; //1.0;
	private static Color defaultColor = Color.BLUE;//Color.DARKORANGE;
	
	
	public WaterTile(int x,int y) {
		super(x, y, defaultColor);
	}
	public WaterTile(int x, int y, String iconPath){
		super(x, y, iconPath);
	}

	@Override
	public Event onStep() {
//		System.out.println("Water tile");
		Random r = new Random();
		double chance = r.nextDouble();
		if (chance <= chanceOfItemEncounter){
			return new FindItem(super.calculateRarity());
		} else if (chance <= chanceOfCreatureEncounter + chanceOfItemEncounter){ //account for full probability
			return new SpawnWaterMonster();
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " Water";
	}
}
