package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class FireTile extends Tile {

	public static double chanceOfEncounter = 1.0;
	private static Color defaultColor = Color.RED;
	
	//sw = screen width, sh = screen height
	public FireTile(int x,int y, int sw, int sh) {
		super(x, y, defaultColor, sw, sh);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub

	}


}
