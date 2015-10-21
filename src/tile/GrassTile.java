package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class GrassTile extends Tile implements Stepable{
	
	public static double chanceOfEncounter = 0.25;
	private static Color defaultColor = Color.GREEN;

	
	public GrassTile(int x, int y) {
		super(x,y, defaultColor);
	}

	public Event onStep() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
