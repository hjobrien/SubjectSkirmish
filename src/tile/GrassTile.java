package tile;

import event.Event;
import javafx.scene.paint.Color;

public class GrassTile extends Tile implements Stepable{
	
	public static double chanceOfEncounter = 0.25;
	private static Color defaultColor = Color.GREEN;

	public GrassTile(int x,int y) {
		super(x, y, defaultColor);
	}

	public Event onStep() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Grass";
	}

}
