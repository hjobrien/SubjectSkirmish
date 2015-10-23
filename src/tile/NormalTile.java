package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class NormalTile extends Tile implements Stepable {

	/*
	 * I don't know the purpose of this class, it seems like grass tile should be a filler, so i don't know the role this plays
	 * I'm not going to use it for right now
	 * --Hank
	 * I was thinking that grass had monsters in it, so we needed
	 * a normal tile that wouldnt have any chance of encounter
	 * --Liam
	 */
	public static double chanceOfEncounter = 0.0;
	public static Color defaultColor = Color.DARKGREY;
	
	public NormalTile(int x,int y) {
		super(x, y, defaultColor);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:" + super.getX() + "y:" + super.getY() + " Normal";
	}

}
