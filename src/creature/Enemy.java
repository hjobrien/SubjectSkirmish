package creature;

import battle.Attack;
import javafx.scene.image.ImageView;

public interface Enemy {

	public void performAttack();
	
	public void onAttack(Attack a);
	
	public ImageView getImage();
}
