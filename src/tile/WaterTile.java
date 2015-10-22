package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class WaterTile extends Tile {

	public static double chanceOfEncounter = 0.5;
	private static Color defaultColor = Color.BLUE;
	
	
	//sw = screen width, sh = screen height
	public WaterTile(int x,int y, int sw, int sh) {
		super(x, y, defaultColor, sw, sh);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub
		
	}
}
