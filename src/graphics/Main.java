package graphics;

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

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int BORDER_WIDTH = 0;
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private static final int BORDER_HEIGHT = 0;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane grid = new GridPane();
		addGraphicOutput(grid);
		
		Scene scene = new Scene(grid, SCREEN_WIDTH, SCREEN_HEIGHT);

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
		Canvas canvas = new Canvas(SCREEN_WIDTH - BORDER_WIDTH, SCREEN_HEIGHT - BORDER_HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		//puts 0,0 in center of display
		g.scale(1,-1);
		g.translate(0, (-1) * SCREEN_HEIGHT);
		g.translate(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
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
