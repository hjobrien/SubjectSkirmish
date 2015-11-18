package creature;

import battle.Attack;
import javafx.scene.image.Image;

public interface Enemy {

	public void performAttack();
	
	public void onAttack(Attack a);
	
	public Image getImage();
}
