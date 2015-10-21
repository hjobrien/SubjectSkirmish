package tile;

import graphics.Event;

public abstract class Tile{
	private boolean isOnScreenEdge;
	
	public abstract Event onStep();
}
