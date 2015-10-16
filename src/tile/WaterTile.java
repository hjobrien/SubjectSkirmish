package tile;

public class WaterTile extends Tile {

	public static double chanceOfEncounter = 0.5;
	public static boolean stepable = false;
	
	public WaterTile() {
		super(chanceOfEncounter, stepable);
	}

	@Override
	public void onStep() {
		// TODO Auto-generated method stub
		
	}

}
