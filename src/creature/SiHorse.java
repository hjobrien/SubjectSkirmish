package creature;

import battle.Attack;

public class SiHorse extends Creature implements Enemy {

	public static final String ICON_PATH = "/creatureImages/SiHorseImage.png";
	private static final String NAME = "SiHorse";
	
	
	public SiHorse() {
		super(ICON_PATH, NAME);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Attack performAttack() {
		return null;
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
