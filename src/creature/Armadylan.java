package creature;

import battle.Attack;

public class Armadylan extends Creature implements Enemy {

	public static final String ICON_PATH = "/creatureImages/ArmadylanImage.png";
	public static final String NAME = "Armadylan";
	
	public Armadylan() {
		super(ICON_PATH, NAME);
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
