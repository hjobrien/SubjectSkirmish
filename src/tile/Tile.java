package tile;

import java.awt.Toolkit;

import graphics.Event;
import javafx.scene.paint.Color;

public abstract class Tile{
	private boolean isOnScreenEdge;
	private int x,y;
	private Color c;
	
	public Tile(int x, int y, Color c, int SCREEN_WIDTH, int SCREEN_HEIGHT){
		this.c = c;
		
		//should be a more screen-encompassing formula
		//on my screen the values are -13 and 12 for x, -7 and 7 for y
		//and i verified that these formulas produce those outputs
		if (x == - (SCREEN_WIDTH / 100) - 1 || x == (SCREEN_WIDTH / 50) - (SCREEN_WIDTH / 100) - 1 || y == -1 * (SCREEN_HEIGHT / 100) + 1 || y == SCREEN_HEIGHT / 50 - 2 - (SCREEN_HEIGHT / 100) + 1){
			isOnScreenEdge = true;
			//i tried marking these with a different color but it didnt
			//work because the type of tile overrides it. any ideas
			//how to fix that?
			//--Liam
		} else {
			isOnScreenEdge = false;
		}
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return c;
	}

	public abstract Event onStep();
	
	
}