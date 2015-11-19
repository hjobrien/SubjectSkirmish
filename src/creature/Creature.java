package creature;


import java.util.ArrayList;

import battle.Attack;
import battle.AttackWeightMatrix;
import javafx.scene.image.ImageView;
import player.Item;


public abstract class Creature {
	
	private ImageView icon; //should set to a default 'img. not found' picture
	private int health;
	private String name;
	private ArrayList<Attack> moves;
	private ArrayList<Item> possibleDrops;
	private boolean isCaptive = false;
	
	public static final double[][] ATTACK_WEIGHT_MATRIX = AttackWeightMatrix.ATTACK_WEIGHT_MATRIX;
		
	public abstract void onDeath(); //return Event? 
	
	public abstract void onAddToBag();
	
	public Creature(String iconFilePath, String name){
		this.icon = new ImageView(iconFilePath);
		this.name = name;
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


	public ArrayList<Attack> getMoves() {
		return moves;
	}

	public ArrayList<Item> getPossibleDrops() {
		return possibleDrops;
	}

	public void giveReward() {
		// TODO Auto-generated method stub
		
	}
	
	public ImageView getImage(){
		return icon;
	}
	
	public String getName(){
		return name;
	}

}
