package graphics;

import java.util.ArrayList;

import tile.Tile;

public class Board {
	private ArrayList<ArrayList<Tile>> board; //tile data structure, maybe wont be 2d arraylist

	//i made this a class because we might want to do more with the board object, but right now it just wraps a 2d arraylist
	
	public ArrayList<ArrayList<Tile>> getBoard() {
		return board;
	}

	public Board(ArrayList<ArrayList<Tile>> board){
		this.board = board;
	}
	
}
