package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class GrassTile extends Tile implements Stepable{
	
	public static double chanceOfEncounter = 0.25;
	private static Color defaultColor = Color.GREEN;

	//sw = screen width, sh = screen height
	public GrassTile(int x,int y, int sw, int sh) {
		super(x, y, defaultColor, sw, sh);
	}

	public Event onStep() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
