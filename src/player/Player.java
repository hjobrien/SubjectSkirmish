package player;

import java.util.ArrayList;

import event.Event;
import graphics.Board;
import javafx.scene.paint.Color;

public class Player {
	public static final double SIZE = 0.8; // 0 < SIZE < TILE_SIZE
	private int x;
	private int y;
	private Color color;
	private Board board;
	private ArrayList<Item> bag = new ArrayList<Item>();
	
	public Player(int startX, int startY, Color color, Board board){
		this.x = startX;
		this.y = startY;
		this.color = color;
		this.board = board;
	}
	
	public Event advance(int deltaX, int deltaY){
		x += deltaX;
		y += deltaY;
		return board.getBoard().get(x).get(y).onStep();
	}

	
	public int[] getLocation(){
		return new int[]{x,y};
	}

	public Color getColor() {
		return color;
	}
	
	public void addToBag(Item itemToAdd){
		bag.add(itemToAdd);
	}
	
	public ArrayList<Item> getBag(){
		return bag;
	}
}
