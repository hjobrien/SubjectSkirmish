package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class WaterTile extends Tile implements Stepable{

	public static double chanceOfEncounter = 0.5;
	private static Color defaultColor = Color.BLUE;
	
	
	public WaterTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub
		
	}
}
