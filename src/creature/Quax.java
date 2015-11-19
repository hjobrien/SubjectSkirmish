package creature;

import battle.Attack;

public class Quax extends Creature implements Enemy {

	public static final String ICON_FILE = "/creatureImages/QuaxImage.png";
	public static final String NAME = "Quax";
	
	public Quax() {
		super(ICON_FILE, NAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performAttack() {
		// TODO Auto-generated method stub

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
