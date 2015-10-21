package player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player {
	public static final int SIZE = 1;
	private int x;
	private int y;
	private Color color;
	private GraphicsContext g;
	
	public Player(int startX, int startY, Color color, GraphicsContext g){
		this.x = startX;
		this.y = startY;
		this.color = color;
		this.g = g;
		g.setFill(this.color);
		this.draw();
	}
	
	public boolean advance(int deltaX, int deltaY){
		x += deltaX;
		y += deltaY;
		
		return true;
	}
	
	public void draw(){
		g.clearRect(x-3*SIZE,y-3*SIZE,SIZE*5,SIZE*5);
		g.fillRect(x, y, SIZE, SIZE);
	}
}
