package tile;

import event.Event;
import javafx.scene.paint.Color;

public class WaterTile extends Tile implements Stepable{

	public static double chanceOfCreatureEncounter = 0.5;
	private static Color defaultColor = Color.BLUE;
	
	
	public WaterTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Water";
	}
}
