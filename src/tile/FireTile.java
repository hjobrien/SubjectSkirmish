package tile;

import javafx.scene.paint.Color;

public class FireTile extends Tile {

//	private static double chanceOfCreatureEncounter = 1.0;
	private static Color defaultColor = Color.GREY;//Color.RED;
	

	public FireTile(int x,int y) {
		super(x, y, defaultColor);
	}
	
	public FireTile(int x, int y, String iconPath){
		super(x, y, iconPath);
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Fire";
	}


}
