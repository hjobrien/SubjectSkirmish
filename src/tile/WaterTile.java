package tile;

import java.util.Random;

import event.Event;
import event.MonsterItem;
import event.Rarity;
import event.SpawnWaterMonster;
import javafx.scene.paint.Color;

public class WaterTile extends Tile implements Stepable{

	private static double chanceOfItemEncounter = 0.0;
	private static double chanceOfCreatureEncounter = 0.5;
	private static Color defaultColor = Color.BLUE;
	
	
	public WaterTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		Random r = new Random();
		double chance = r.nextDouble();
		if (chance <= chanceOfItemEncounter){
			return new MonsterItem("Ben", Rarity.COMMON);
		} else if (chance <= chanceOfCreatureEncounter + chanceOfItemEncounter){ //account for full probability
			return new SpawnWaterMonster();
		}
		return new SpawnWaterMonster();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Water";
	}
}
