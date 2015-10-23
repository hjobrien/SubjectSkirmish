package tile;

import javafx.scene.paint.Color;

public abstract class Tile{
	private int x;
	private int y;
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
	
	public abstract String toString();
}
