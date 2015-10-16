package graphics;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1440;
	private static final int BORDER_WIDTH = 0;
	private static final int SCREEN_HEIGHT = 900;
	private static final int BORDER_HEIGHT = 0;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		
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
		g.strokeLine(0, 0, 1, 1);
		grid.add(canvas, 0, 2);
	}



}
