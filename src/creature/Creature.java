package creature;


import java.util.ArrayList;

import battle.Attack;
import battle.AttackWeightMatrix;
import javafx.scene.image.Image;
import player.Item;


public abstract class Creature {
	
	private Image icon; //should set to a default 'img. not found' picture
	private int health;
	private ArrayList<Attack> moves;
	private ArrayList<Item> possibleDrops;
	private boolean isCaptive = false;
	
	public static final double[][] ATTACK_WEIGHT_MATRIX = AttackWeightMatrix.ATTACK_WEIGHT_MATRIX;
		
	public abstract void onDeath(); //return Event? 
	
	public abstract void onAddToBag();
	
	public Creature(String iconFilePath){
		icon = new Image(iconFilePath);
	}
	
	protected void takeDamage(double damage) {
		health -= damage;
		if(health <= 0){
			onDeath();
		}
		
	}

	public void setCaptivity(boolean b) {
		isCaptive = b;
	}
	
	
	public Image getIcon() {
		return icon;
	}

	public ArrayList<Attack> getMoves() {
		return moves;
	}

	public ArrayList<Item> getPossibleDrops() {
		return possibleDrops;
	}

	public void giveReward() {
		// TODO Auto-generated method stub
		
	}
	
	public Image getImage(){
		return icon;
	}

}
