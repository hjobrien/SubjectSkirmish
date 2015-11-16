package creature;

import battle.Attack;
import battle.Type;

public class SiHorse extends Creature implements Enemy{
	public static final Type type = Type.COMPUTER_SCIENCE;
	
	public SiHorse() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onAttack(Attack a) {
		super.takeDamage(a.getBaseDamage() * ATTACK_WEIGHT_MATRIX[a.getType().getValue()][type.getValue()]);
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void performAttack() {
		// TODO Auto-generated method stub
		
	}

}
