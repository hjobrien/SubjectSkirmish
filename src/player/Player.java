package player;

import javafx.scene.canvas.GraphicsContext;

public class Player {
	public static final int SIZE = 1;
	private int x;
	private int y;
	
	public Player(int startX, int startY){
		this.x = startX;
		this.y = startY;
	}
	
	public boolean advance(int deltaX, int deltaY){
		x += deltaX;
		y += deltaY;
		
		return true;
	}
	
	public void draw(GraphicsContext g){
		g.clearRect(x-3*SIZE,y-3*SIZE,SIZE*5,SIZE*5);
		g.fillRect(x, y, SIZE, SIZE);
	}
}
