package tile;

import java.util.Random;

import event.Event;
import event.FindItem;
import javafx.scene.paint.Color;

public class GrassTile extends Tile implements Stepable{
	
	public static double chanceOfCreatureEncounter = 0.25;
	public static double chanceOfItemEncounter = 0.50;
	private static Color defaultColor = Color.GREEN;

	public GrassTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		Random r = new Random();
		double itemChance = r.nextDouble();
		if (itemChance <= chanceOfItemEncounter){
			return new FindItem();
		}
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Grass";
	}

}
