package tile;

import graphics.Event;
import javafx.scene.paint.Color;

public class NormalTile extends Tile implements Stepable {

	/*
	 * I don't know the purpose of this class, it seems like grass tile should be a filler, so i don't know the role this plays
	 * I'm not going to use it for right now
	 * --Hank
	 */
	public static double chanceOfEncounter = 0.0;
	
	public NormalTile(int x,int y, Color c) {
		super(x,y,c);
	}

	@Override
	public Event onStep() {
		return null;
		// TODO Auto-generated method stub

	}

}
