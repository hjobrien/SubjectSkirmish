package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class FireTile extends Tile {

	public static double chanceOfEncounter = 1.0;
	private static Color defaultColor = Color.RED;
	

	public FireTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub

	}


}
