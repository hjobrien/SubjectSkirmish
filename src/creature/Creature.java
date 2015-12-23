package creature;


import java.util.ArrayList;
import java.util.Random;

import battle.Attack;
import battle.AttackWeightMatrix;
import javafx.scene.image.ImageView;
import player.Item;


public abstract class Creature {
	
	private ImageView icon; //should set to a default 'img. not found' picture
	private int currentHealth;
	private int originalHealth;

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
		if (this.currentHealth - damage <= 0){
			this.currentHealth = 0;
			alive = false;
			onDeath();
		} else {
			this.currentHealth -= damage;
		}
		
	}
	
	public Attack performAttack(){
		// TODO make an AI, maybe change based on level?
		//1) simple case should be random moves
		return randomMove();
		//1.5) half the time random, half the time complex?
		//2) complex case should play based on you effects, i.e. if you have some effect a, 
		//and it has a move good against a, it should use that one
		//3) challenge case should plan move combo's
		//maybe a predictive algorithm based on probability of what the opponent has? could run in background
			
	}

	private Attack randomMove() {
		Random r = new Random();
		int n = r.nextInt(4);
		return getMoves()[n];
	}
	
	public boolean isAlive(){
		return this.alive;
	}
	
	public void setAlive(){
		this.alive = true;
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
	
	public int getCurrentHealth(){
		return this.currentHealth;
	}
	
	public int getOriginalHealth(){
		return this.originalHealth;
	}
	
	//for debugging, probably wont use this in the real game
	//it would be better if the creature was implemented with a move set
	//or maybe even a move set that is generated based on its level (if we assign creatures levels)?
	public void setMoves(ArrayList<Attack> m){
		for (int i = 0; i < m.size(); i++){
			moves[i % 4] = m.get(i);				//mod 4 in case the arrayList has more than 4 values
		}
	}
	
	//for debugging, probably wont use this in the real game
	public void setHealth(int health){
		this.originalHealth = health;
		this.currentHealth = health;
	}

}
