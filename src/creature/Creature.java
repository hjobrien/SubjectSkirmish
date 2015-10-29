package creature;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

import battle.Attack;
import battle.AttackWeightMatrix;
import player.Item;


public abstract class Creature {
	
	private BufferedImage selfPic; //should set to a default 'img. not found' picture
	private int health;
	private ArrayList<Attack> moves;
	private ArrayList<Item> possibleDrops;
	
	public static final double[][] ATTACK_WEIGHT_MATRIX = AttackWeightMatrix.ATTACK_WEIGHT_MATRIX;
		
	public abstract void onDeath(); //return Event? 
	
	protected void takeDamage(double damage) {
		health -= damage;
		if(health <= 0){
			onDeath();
		}
		
	}
}
