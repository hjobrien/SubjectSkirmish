package creature;


import java.util.ArrayList;
import battle.Attack;
import battle.AttackWeightMatrix;


public abstract class Creature {
	private int health;
	private ArrayList<Attack> moves;
	public static final double[][] ATTACK_WEIGHT_MATRIX = AttackWeightMatrix.ATTACK_WEIGHT_MATRIX;
		
	public abstract void onDeath(); //return Event?
	
	protected void takeDamage(double damage) {
		health -= damage;
		if(health <= 0){
			onDeath();
		}
		
	}
}
