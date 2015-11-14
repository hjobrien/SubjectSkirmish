package graphics;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import tile.BorderTile;
import tile.FireTile;
import tile.GrassTile;
import tile.Tile;
import tile.WaterTile;

public class BoardGenerator {

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	//commonly used formulas that can easily be changed now
	//Still needs to be optimized for all screens
	private static final int X_MAX = (SCREEN_WIDTH / 50);
	private static final int Y_MAX = (SCREEN_HEIGHT / 50)-3;
	
	private static final String FIRE_PATH = "/images/FireTile.jpeg";
	private static final String GRASS_PATH = "/images/GrassTile.jpeg";
	private static final String WATER_PATH = "/images/WaterTile.jpeg";
	private static final String BORDER_PATH = "/images/BorderTile.jpeg";
	
	
	public BoardGenerator(){
		
	}
	
	public static Board generate(BoardStyle style){
		switch(style){
			case FULL_RANDOM:
				return genRandBoard();
			default:
				break;
		}
		return null;
	}

	private static Board genRandBoard() {
		ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
		Random rand = new Random();
		
		//still needs to be optimized
		for(int i = 0; i < X_MAX + 1; i++){
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for(int j = 0; j < Y_MAX + 1; j++){
				//such beautiful polymorphism
				Tile tempTile = null;
				if (isOnScreenEdge(i, j)){
					tempTile = new BorderTile(i,j, BORDER_PATH);
				} else if (i == X_MAX / 2 && j == Y_MAX / 2){
					//automatically makes the tile that the player starts on a grass tile
					tempTile = new GrassTile(i, j, GRASS_PATH);
				} else {
					int x = rand.nextInt(6); //0-5
					if(x < 1){ //0
						tempTile = new FireTile(i,j, FIRE_PATH);
					} else if (x < 4){ //1-3
						tempTile = new GrassTile(i,j, GRASS_PATH);
					} else { //4-5
						tempTile = new WaterTile(i,j, WATER_PATH);
					}
					//normal tiles can also be used
				}
				temp.add(tempTile);
			}
			board.add(temp);
		}
		return new Board(board);
	}
	
	
	public static boolean isOnScreenEdge(int x, int y){
		
		//a benefit of this is that the grid could now be 
		//bigger than is actually needed, as long as the border 
		//restricts the player. 
		if (x == 0 || x == X_MAX|| y == 0|| y == Y_MAX){
			return true;
		} 
		return false;
	}
}
