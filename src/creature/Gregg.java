package creature;

import battle.Attack;

public class Gregg extends Creature implements Enemy {

	public static final String ICON_FILE = "/creatureImages/GreggImage.png";
	public static final String NAME = "Gregg";
	
	public Gregg() {
		super(ICON_FILE, NAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAttack(Attack a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAddToBag() {
		// TODO Auto-generated method stub

	}

}
