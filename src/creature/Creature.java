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
	private Attack[] moves = new Attack[4];
	private ArrayList<Item> possibleDrops;
	private boolean alive = true;
	private boolean isCaptive = false;
	
	public static final double[][] ATTACK_WEIGHT_MATRIX = AttackWeightMatrix.ATTACK_WEIGHT_MATRIX;
		
	public abstract void onDeath(); //return Event? 
	
	public abstract void onAddToBag();
	
	public Creature(String iconFilePath, String name){
		this.icon = new ImageView(iconFilePath);
		this.name = name;
	}
	
	//why did you make this protected? i changed it to public
	public void takeDamage(double damage) {
		if (this.health - damage <= 0){
			this.health = 0;
			alive = false;
			onDeath();
		} else {
			this.health -= damage;
		}
		
	}
	
	public boolean isAlive(){
		return this.alive;
	}

	public void setCaptivity(boolean b) {
		this.isCaptive = b;
	}


	public Attack[] getMoves() {
		return this.moves;
	}

	public ArrayList<Item> getPossibleDrops() {
		return this.possibleDrops;
	}

	public void giveReward() {
		// TODO Auto-generated method stub
		
	}
	
	public ImageView getImage(){
		return this.icon;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	//for debugging, probably wont use this in the real game
	public void setMoves(ArrayList<Attack> m){
		for (int i = 0; i < m.size(); i++){
			moves[i % 4] = m.get(i);				//mod 4 in case the arrayList has more than 4 values
		}
	}
	
	//for debugging, probably wont use this in the real game
	public void setHealth(int health){
		this.health = health;
	}

}
