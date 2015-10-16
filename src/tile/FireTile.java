package tile;

public class FireTile extends Tile {

	public static double chanceOfEncounter = 1.0;
	public static boolean stepable = false;
	
	public FireTile() {
		super(chanceOfEncounter, stepable);
	}

	@Override
	public void onStep() {
		// TODO Auto-generated method stub

	}

}
