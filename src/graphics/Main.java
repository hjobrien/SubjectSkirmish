package graphics;

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
	private static final int SCREEN_WIDTH = 1440;
	private static final int BORDER_WIDTH = 0;
	private static final int SCREEN_HEIGHT = 900;
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
		g.strokeLine(2, 3, 2, 4);
		g.strokeLine(2, 3, 3, 3);
		g.strokeLine(3, 3, 3, 4);
		g.strokeLine(2, 4, 3, 4);
		g.strokeLine(0, 0, 0, 0);
		
		//adds the already-drawn-on canvas to the screen
		grid.add(canvas, 0, 2);
	}



}
