package tile;

import java.util.Random;

import event.Event;
import event.FindItem;
import event.SpawnGrassMonster;
import javafx.scene.paint.Color;

public class GrassTile extends Tile implements Stepable{
	
	private static double chanceOfCreatureEncounter = 0.25;
	private static double chanceOfItemEncounter = 0.50;
	private static Color defaultColor = Color.GREEN;

	public GrassTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		Random r = new Random();
		double chance = r.nextDouble();
		if (chance <= chanceOfItemEncounter){
			return new FindItem();
		} else if (chance <= chanceOfCreatureEncounter + chanceOfItemEncounter){ //account for full probability
			return new SpawnGrassMonster();
		}
		return new SpawnGrassMonster();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Grass";
	}

}
