package creature;

import battle.Attack;

public interface Enemy {

	public void performAttack();
	
	public void onAttack(Attack a);
}
