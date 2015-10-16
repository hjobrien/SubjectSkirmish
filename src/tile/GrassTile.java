package tile;

public class GrassTile extends Tile{
	
	public static double chanceOfEncounter = 0.25;
	public static boolean stepable = true;
	
	public GrassTile() {
		super(chanceOfEncounter, stepable);
	}

	@Override
	public void onStep() {
		// TODO Auto-generated method stub
		
	}
}
