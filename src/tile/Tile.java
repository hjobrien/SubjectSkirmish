package tile;

import java.awt.Toolkit;

import graphics.Event;
import javafx.scene.paint.Color;

public abstract class Tile{
	private int x,y;
	private Color c;
	
	public Tile(int x, int y, Color c){
		this.c = c;
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
