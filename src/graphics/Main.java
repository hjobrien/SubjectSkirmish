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
	private int[] location = new int[1]; //x and y coordinate 
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
		for(int i = 0; i < SCREEN_HEIGHT / 50 - 1; i++){
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for(int j = 0; j < SCREEN_WIDTH / 50 + 1; j++){
				//such beautiful polymorphism
				Tile tempTile = null;
				int x = rand.nextInt(4);
				if(x == 0){
					tempTile = new FireTile(j - (SCREEN_WIDTH / 100) - 1,i - (SCREEN_HEIGHT / 100) + 1, SCREEN_WIDTH, SCREEN_HEIGHT);
				}
				if(x == 1){
					tempTile = new GrassTile(j - (SCREEN_WIDTH / 100) - 1,i - (SCREEN_HEIGHT / 100) + 1, SCREEN_WIDTH, SCREEN_HEIGHT);
				}
				if(x == 2){
					tempTile = new WaterTile(j - (SCREEN_WIDTH / 100) - 1,i - (SCREEN_HEIGHT / 100) + 1, SCREEN_WIDTH, SCREEN_HEIGHT);
				}
				if (x == 3){
					tempTile = new NormalTile(j - (SCREEN_WIDTH / 100) - 1,i - (SCREEN_HEIGHT / 100) + 1, SCREEN_WIDTH, SCREEN_HEIGHT);
				}
				temp.add(tempTile);
			}
			board.add(temp);
		}
		this.board = new Board(board);

		
		GridPane grid = new GridPane();
		Canvas canvas = new Canvas(SCREEN_WIDTH - BORDER_WIDTH, SCREEN_HEIGHT - BORDER_HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();
		Player player = new Player(0,0,Color.GREY);
		
//		update(player, g);
		
		addGraphicOutput(grid, g);
		
		Scene scene = new Scene(grid, SCREEN_WIDTH, SCREEN_HEIGHT);

		stage.setScene(scene);
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
				moved = player.advance(-TILE_SIZE,0);
			}
			if(e.getCode() == KeyCode.RIGHT){
				moved = player.advance(TILE_SIZE,0);
			}
			if(e.getCode() == KeyCode.UP){
				moved = player.advance(0,TILE_SIZE);
			}
			if(e.getCode() == KeyCode.DOWN){
				moved = player.advance(0,-TILE_SIZE);
			}
			location = player.getLocation();
			update(player,g);
		});
		grid.add(canvas, 0, 2); //why 2?
		stage.show();

	}
	
	public void addGraphicOutput(GridPane grid, GraphicsContext g){
		
		//puts 0,0 in center of display
		g.scale(1,-1);
		g.translate(0, (-1) * SCREEN_HEIGHT);
		g.translate(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		g.scale(50,50);
		g.setLineWidth(0.05);

	}
	
	public void update(Player player, GraphicsContext g){
		//draw player
		double size = Player.SIZE;
		
		//optimized this
		//--Liam
		g.clearRect(location[0]-TILE_SIZE,location[1]-TILE_SIZE, TILE_SIZE * 3,TILE_SIZE * 3);

		for(ArrayList<Tile> row : board.getBoard()){
			for(Tile tempTile : row){
				g.setFill(tempTile.getColor());
				g.fillRect(tempTile.getX(), tempTile.getY(), TILE_SIZE, TILE_SIZE);
			}
		}

		g.setFill(player.getColor());
		g.fillRect(location[0] + (1-size)/2, location[1] + (1-size)/2, size, size);
		
	}
	
//	public void initBoard(){
//		fo
//	}



}
