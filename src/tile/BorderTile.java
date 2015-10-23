package tile;

import javafx.scene.paint.Color;

public class BorderTile extends Tile {
	
	private static final Color c = Color.PURPLE;

	public BorderTile(int x, int y) {
		super(x, y, c);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Border";
	}

}
