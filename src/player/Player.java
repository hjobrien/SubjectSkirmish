package player;

import javafx.scene.paint.Color;

public class Player {
	public static final double SIZE = 0.8;
	private int x;
	private int y;
	private Color color;
	
	public Player(int startX, int startY, Color color){
		this.x = startX;
		this.y = startY;
		this.color = color;
	}
	
	public boolean advance(int deltaX, int deltaY){
		x += deltaX;
		y += deltaY;
		
		return true;
	}

	
	public int[] getLocation(){
		return new int[]{x,y};
	}

	public Color getColor() {
		return color;
	}
}
