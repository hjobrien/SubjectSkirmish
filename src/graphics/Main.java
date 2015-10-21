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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import player.Player;
import tile.FireTile;
import tile.GrassTile;
import tile.Tile;
import tile.WaterTile;

public class Main extends Application {

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int BORDER_WIDTH = 0;
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private static final int BORDER_HEIGHT = 0;
	private int[] location = new int[1]; //x and y coordinate 
	private Board board;
	
	public static final int TILE_SIZE = 1;
	public static final Color PLAYER_COLOR = Color.BLACK;


	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();
		Random rand = new Random();
		for(int i = -SCREEN_HEIGHT/20; i < SCREEN_WIDTH/20; i++){
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for(int j = -SCREEN_WIDTH/20; j < SCREEN_HEIGHT/20; j++){
				Tile tempTile = null;
				int x = rand.nextInt(3);
				if(x == 0){
					tempTile = new FireTile(i,j);
				}
				if(x == 1){
					tempTile = new GrassTile(i,j);
				}
				if(x == 2){
					tempTile = new WaterTile(i,j);
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
				moved = player.advance(-1,0);
			}
			if(e.getCode() == KeyCode.RIGHT){
				moved = player.advance(1,0);
			}
			if(e.getCode() == KeyCode.UP){
				moved = player.advance(0,1);
			}
			if(e.getCode() == KeyCode.DOWN){
				moved = player.advance(0,-1);
			}
			location = player.getLocation();
			update(player,g);
		});
		grid.add(canvas, 0, 2);
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
		g.clearRect(location[0]-3*size,location[1]-3*size,size*5,size*5);

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
