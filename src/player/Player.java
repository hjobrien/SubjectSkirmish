package player;

import graphics.Board;
import javafx.scene.paint.Color;

public class Player {
	public static final double SIZE = 0.8; // 0 < SIZE < TILE_SIZE
	private int x;
	private int y;
	private Color color;
	
	public Player(int startX, int startY, Color color){
		this.x = startX;
		this.y = startY;
		this.color = color;
	}
	
	public void advance(int deltaX, int deltaY, Board board, int[] location){
		x += deltaX;
		y += deltaY;
		board.getBoard().get(location[0]).get(location[1]).onStep();
	}

	
	public int[] getLocation(){
		return new int[]{x,y};
	}

	public Color getColor() {
		return color;
	}
}
