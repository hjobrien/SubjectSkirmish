package creature;

import java.util.Random;

import battle.Attack;
import battle.Type;
import javafx.scene.image.ImageView;


public class Tanimonster extends Creature implements Enemy{

	public static final Type TYPE = Type.COMPUTER_SCIENCE;
	private static final String ICON_PATH = "/creatureImages/TanimonsterImage.png";
	private static final String NAME = "Tanimonster";
	
	//TODO: initialize (init) local health in constructor to some default value, scale based on difficulty?
	public Tanimonster(){
		super(ICON_PATH, NAME);
	}
	
	
	@Override
	public void onAttack(Attack a) {
		//first index is incoming attack type, second one is base defense levels of monster
		super.takeDamage(a.getBaseDamage() * ATTACK_WEIGHT_MATRIX[a.getType().getValue()][TYPE.getValue()]);
		
	}

	@Override
	public void onDeath() {
		super.giveReward();	 //TODO	
	}


	@Override
	public void onAddToBag() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ImageView getImage() {
		return super.getImage();
	}

	
	
}
