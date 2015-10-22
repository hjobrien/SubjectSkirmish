package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class BorderTile extends Tile {
	
	private static final Color c = Color.PURPLE;

	public BorderTile(int x, int y) {
		super(x, y, c);
	}

	@Override
	public Event onStep() {
		// TODO Auto-generated method stub
		return null;
	}

}
