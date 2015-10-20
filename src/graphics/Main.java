package graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	//is this standard for all screens or just for our macbooks?
	private static int screenWidth = 1440;
	private static final int BORDER_WIDTH = 0;
	private static int screenHeight = 900;
	private static final int BORDER_HEIGHT = 0;

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane grid = new GridPane();
		addGraphicOutput(grid);
		
		Scene scene = new Scene(grid, screenWidth, screenHeight);

		stage.setScene(scene);
		//make escape close window
		stage.addEventFilter(KeyEvent.KEY_PRESSED,e -> {
			if(e.getCode() == KeyCode.ESCAPE)
				System.exit(0);
		});
//		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		
		stage.show();

	}
	
	public void addGraphicOutput(GridPane grid){
		Canvas canvas = new Canvas(screenWidth - BORDER_WIDTH, screenHeight - BORDER_HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		//puts 0,0 in center of display
		//are you sure this is the center? because it doesn't seem centered
		g.scale(1,-1);
		g.translate(0, (-1) * screenHeight);
		g.translate(screenWidth / 2, screenHeight / 2);
		g.scale(50,50);
		g.setLineWidth(0.05);
		
		//messing around with the drawing constructors
		/*Draws a rectangle with 4 lines
		g.strokeLine(2, 3, 2, 4);
		g.strokeLine(2, 3, 3, 3);
		g.strokeLine(3, 3, 3, 4);
		g.strokeLine(2, 4, 3, 4);
		g.strokeLine(0, 0, 0, 0);
		*/
		
		/*	
		//Draws 2 squares originating from 0,0
		g.strokeRect(0, 0, 1, 1);
		g.strokeRect(0, 0, 2, 2);
		*/
		
		//draws four squares around the point passed in
		fourSquares(0, 0, g);
		fourSquares(-3, -3, g);
		fourSquares(3, 3, g);		
		fourSquares(-3, 3, g);
		fourSquares(3, -3, g);
		
		//adds the already-drawn-on canvas to the screen
		grid.add(canvas, 0, 2);
	}

	private void fourSquares(int startX, int startY, GraphicsContext g) {
		g.strokeRect(startX - 1, startY - 1, 1, 1);
		g.strokeRect(startX - 1, startY - 1, 2, 2);
		g.strokeRect(startX, startY, 1, 1);		
	}



}
