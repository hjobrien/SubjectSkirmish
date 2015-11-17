package creature;



//example-only friend monster, can use for testing

public class Bunny extends Creature implements Friend {

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAddToBag() {
		super.setCaptivity(true);
		//maybe change its health here? 
		//its state changes from wild to captive here, change values accordingly
	}

}
