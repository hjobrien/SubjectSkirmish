package tile;

public abstract class Tile {
	public double chanceOfEncounter;
	public boolean stepable;
	
	public Tile(double chanceOfEncounter, boolean stepable) {
		super();
		this.chanceOfEncounter = chanceOfEncounter;
		this.stepable = stepable;
	}



	public abstract void onStep();
}
