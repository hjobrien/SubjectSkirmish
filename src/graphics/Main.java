package graphics;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import player.Player;
import tile.*;

public class Main extends Application {

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int BORDER_WIDTH = 0;
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private static final int BORDER_HEIGHT = 0;
	private int[] location = new int[]{7,7}; //x and y coordinate 
	private Board board;

	//I tried changing this and nothing happened. Does it do
	//anything at the moment?
	//---update, i changed the moving so now tile size affects the player,
	//but it still doesnt affect the tiles
	public static final int TILE_SIZE = 1; 
	public static final Color PLAYER_COLOR = Color.BLACK;


	public static void main(String[] args) {	
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
		Random rand = new Random();
		
		//should be the perfect number of tiles now (26 x 15), but 
		//might be different for other computers
		for(int i = 0; i < SCREEN_WIDTH / 50 + 1; i++){
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for(int j = 0; j < SCREEN_HEIGHT / 50 - 2; j++){
				//such beautiful polymorphism
				Tile tempTile = null;
				if (isOnScreenEdge(i, j)){
					tempTile = new BorderTile(i,j);
				} else {
					int x = rand.nextInt(5);
					if(x == 0){
						tempTile = new FireTile(i,j);
					}
					if(x == 1 || x == 2){
						tempTile = new GrassTile(i,j);
					}
					if(x >= 3){
						tempTile = new WaterTile(i,j);
					}
	//				if (x == 3){
	//					tempTile = new NormalTile(j - (SCREEN_WIDTH / 100) - 1,i - (SCREEN_HEIGHT / 100) + 1, SCREEN_WIDTH, SCREEN_HEIGHT);
	//				}
				}
				temp.add(tempTile);
			}
			board.add(temp);
		}
		this.board = new Board(board);

		
		GridPane grid = new GridPane();
		Canvas canvas = new Canvas(SCREEN_WIDTH - BORDER_WIDTH, SCREEN_HEIGHT - BORDER_HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();
		Player player = new Player(location[0],location[1],PLAYER_COLOR);
		scaleGraphics(grid, g);
		update(player, g);
				
		Scene boardScene = new Scene(grid, SCREEN_WIDTH, SCREEN_HEIGHT);

		stage.setScene(boardScene);
		//make escape close window
		stage.addEventFilter(KeyEvent.KEY_PRESSED,e -> {
			if(e.getCode() == KeyCode.ESCAPE)
				System.exit(0);
		});
//		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		
		stage.addEventFilter(KeyEvent.KEY_PRESSED, e ->{
			boolean moved; //to test if player moved
			if(e.getCode() == KeyCode.LEFT){
//				System.out.println((this.board.getBoard()).get(location[0]-1).get(location[1]).toString());
				if((this.board.getBoard()).get(location[0] - 1).get(location[1]) instanceof Stepable){
					moved = player.advance(-TILE_SIZE,0);
				}
			}
			if(e.getCode() == KeyCode.RIGHT){
//				System.out.println((this.board.getBoard()).get(location[0]+1).get(location[1]).toString());
				if((this.board.getBoard()).get(location[0] + 1).get(location[1]) instanceof Stepable){
					moved = player.advance(TILE_SIZE,0);
				}
			}
			if(e.getCode() == KeyCode.UP){
//				System.out.println((this.board.getBoard()).get(location[0]).get(location[1]-1).toString());
				if((this.board.getBoard()).get(location[0]).get(location[1] - 1) instanceof Stepable){
					moved = player.advance(0,-TILE_SIZE);
				}
			}
			if(e.getCode() == KeyCode.DOWN){
//				System.out.println((this.board.getBoard()).get(location[0]).get(location[1]+1).toString());
				if((this.board.getBoard()).get(location[0]).get(location[1] + 1) instanceof Stepable){
					moved = player.advance(0,TILE_SIZE);
				}
			}
			location = player.getLocation();
			update(player,g);
		});
		grid.add(canvas, 0, 0); //why 2?
		stage.show();

	}
	
	public boolean isOnScreenEdge(int x, int y){
		//should be a more screen-encompassing formula
		//on my screen the values are -13 and 12 for x, -7 and 7 for y
		//and i verified that these formulas produce those outputs
		//another semi-benefit of this is that the grid could now be 
		//bigger than is actually needed, as long as the border 
		//restricts the player. 
		if (x == 0 || x == (SCREEN_WIDTH / 50)|| y == 0|| y == (SCREEN_HEIGHT / 50) - 3){
			return true;
		} 
		return false;
	}
	
	public void scaleGraphics(GridPane grid, GraphicsContext g){
		
		//puts 0,0 in center of display
//		g.scale(1,-1);
//		g.translate(0, (-1) * SCREEN_HEIGHT);
//		g.translate(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		g.scale(50,50);
		g.setLineWidth(0.05);

	}
	
	public void update(Player player, GraphicsContext g){
		//draw player
		double size = Player.SIZE;
		
		//clears old player
		g.clearRect(location[0]-TILE_SIZE,location[1]-TILE_SIZE, TILE_SIZE * 3,TILE_SIZE * 3);

		for(ArrayList<Tile> row : board.getBoard()){
			for(Tile tempTile : row){
				g.setFill(tempTile.getColor());
				g.fillRect(tempTile.getX(), tempTile.getY(), TILE_SIZE, TILE_SIZE);
			}
		}

		g.setFill(player.getColor());
		g.fillRect(location[0] + (TILE_SIZE-size)/2, location[1] + (TILE_SIZE-size)/2, size, size);
		
	}
	



}
