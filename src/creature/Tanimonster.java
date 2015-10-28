package creature;

import battle.Attack;
import battle.Type;


public class Tanimonster extends Creature implements Enemy{

	public static final Type type = Type.COMPUTER_SCIENCE;
	
	//TODO: initialize (init) local health in constructor to some default value, scale based on difficulty?
	
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
