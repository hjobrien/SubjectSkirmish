package tile;

public class DoorTile extends Tile {

	public DoorTile(int x, int y, String iconPath) {
		super(x, y, iconPath);
	}

	@Override
	public String toString() {
		return super.toString() + "Door";
	}

}
