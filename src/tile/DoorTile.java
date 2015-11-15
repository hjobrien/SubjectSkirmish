package tile;

import javafx.scene.paint.Color;

public class DoorTile extends Tile implements Stepable{

	private static final Color c = Color.BROWN;//Color.PINK;
	
	
	public DoorTile(int x, int y) {
		super(x, y, c);
	}
	
	public DoorTile(int x, int y, String iconPath) {
		super(x, y, iconPath);
	}

	@Override
	public String toString() {
		return super.toString() + "Door";
	}

}
