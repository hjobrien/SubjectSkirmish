package tile;

public class NormalTile extends Tile {

	public static double chanceOfEncounter = 0.0;
	public static boolean stepable = true;
	
	public NormalTile() {
		super(chanceOfEncounter, stepable);
	}

	@Override
	public void onStep() {
		// TODO Auto-generated method stub

	}

}
