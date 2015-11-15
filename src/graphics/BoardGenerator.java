package graphics;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import tile.BorderTile;
import tile.DoorTile;
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
	
	//Adjust these weights to change how groupings form, though they should be pretty similar overall or one tile will dominate
	//the value of the weights affects the size of the groups, if they are all the same value
	//if the weights are different values the numbers of each kind of tile will likely be unequal
	private static final double WATER_PRESSURE = 0.285;
	private static final double GRASS_PRESSURE = 0.285;
	//Fire weight is high to promote large groups, but i turned down its spawn chance to promote a few large groups
	private static final double FIRE_PRESSURE = 0.285;
	
	
	public BoardGenerator(){
		
	}
	
	public static Board generate(BoardStyle style){
		switch(style){
			case RANDOM:
				return genRandBoard();
			case SMALL_GROUPS:
				return smallGroupedBoard();
			case LARGE_GROUPS:
				return largeGroupedBoard();
			case LARGER_GROUPS:
				return largerGroupedBoard();
			default:
				break;
		}
		return null;
	}

	

	private static Board smallGroupedBoard() {
		return increaseBoardGrouping(genRandBoard());
	}
	
	private static Board largeGroupedBoard() {
		return increaseBoardGrouping(smallGroupedBoard());
	}
	private static Board largerGroupedBoard() {
		return increaseBoardGrouping(largerGroupedBoard());
	}

	//presure in context of evolutionary pressure
	private static Board increaseBoardGrouping(Board seed){
		Random rand = new Random();
		ArrayList<ArrayList<Tile>> seedBoard = seed.getBoard();
		Tile north;
		Tile east;
		Tile south;
		Tile west;
		Tile center;
		double[] tileTypePressure = new double[]{0,0,0}; //water, grass, fire pressure
		//starts at 1,1 to avoid rows of borderTile, also why it ends at size()-1
		for(int i = 1; i < seedBoard.size()-1; i++){
			for(int j = 1; j < seedBoard.get(0).size()-1; j++){
				north = seed.getBoard().get(i+1).get(j);
				east = seed.getBoard().get(i).get(j+1);
				south = seed.getBoard().get(i-1).get(j);
				west = seed.getBoard().get(i).get(j-1);
				center = seed.getBoard().get(i).get(j);

				tileTypePressure = getPressure(center, north, east, south, west);
				double d = rand.nextDouble();
				if(d < tileTypePressure[2]){
					seedBoard.get(i).set(j, new FireTile(i,j,FIRE_PATH));
				}
				else if(d < tileTypePressure[1] + tileTypePressure[2]){
					seedBoard.get(i).set(j, new GrassTile(i,j,GRASS_PATH));	
				}
				else if(d < tileTypePressure[0] + tileTypePressure[1] + tileTypePressure[2]){
					seedBoard.get(i).set(j, new WaterTile(i,j,WATER_PATH));
				}
			}
		}
		//makes sure tile evolving didn't remove initial grass tile
		seedBoard.get(X_MAX / 2).set(Y_MAX / 2, new GrassTile(X_MAX / 2, Y_MAX / 2, GRASS_PATH));
		return new Board(seedBoard);
		
	}
	
	private static double[] getPressure(Tile center, Tile north, Tile east, Tile south, Tile west) {
		double[] tileTypePressure = new double[]{0,0,0};
		int grassCount = 0, waterCount = 0, fireCount = 0;
		if(north instanceof WaterTile){
			waterCount++;
		}
		else if (north instanceof FireTile){
			fireCount++;
		}
		else if (north instanceof GrassTile){
			grassCount++;
		}
		//------------------
		if(east instanceof WaterTile){
			waterCount++;
		}
		else if (east instanceof FireTile){
			fireCount++;
		}
		else if (east instanceof GrassTile){
			grassCount++;
		}
		//------------------
		if(south instanceof WaterTile){
			waterCount++;
		}
		else if (south instanceof FireTile){
			fireCount++;
		}
		else if (south instanceof GrassTile){
			grassCount++;
		}
		//-------------------
		if(west instanceof WaterTile){
			waterCount++;
		}
		else if (west instanceof FireTile){
			fireCount++;
		}
		else if (west instanceof GrassTile){
			grassCount++;
		}
		
		
		tileTypePressure[0] = WATER_PRESSURE * waterCount;
		tileTypePressure[1] = GRASS_PRESSURE * grassCount;
		tileTypePressure[2] = FIRE_PRESSURE * fireCount;
		
		return tileTypePressure;
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
				
				if ((i == 0 && j == Y_MAX / 2) || (i == X_MAX && j == Y_MAX / 2) || (i == X_MAX / 2 && j == 0) || (i == X_MAX / 2 && j == Y_MAX)){
					tempTile = new DoorTile(i, j);
				} else if (isOnScreenEdge(i, j)){
					tempTile = new BorderTile(i,j, BORDER_PATH);
				} else if (i == X_MAX / 2 && j == Y_MAX / 2){
					//automatically makes the tile that the player starts on a grass tile
					tempTile = new GrassTile(i, j, GRASS_PATH);
				} else {
					int x = rand.nextInt(8); //0-7
					if(x < 1){ //0
						tempTile = new FireTile(i,j, FIRE_PATH);
					} else if (x < 5){ //1-4
						tempTile = new GrassTile(i,j, GRASS_PATH);
					} else { //5-7
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
