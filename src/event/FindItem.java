package event;

import java.util.Random;

public class FindItem implements Event {

	public FindItem(){
		System.out.println("Item Found");
		Random r = new Random();
		int itemChance = r.nextInt(10);
		if (itemChance == 0){
			System.out.println("You found Eli");
		} else if (itemChance < 3){
			System.out.println("You found Akaash");
		} else if (itemChance < 10){
			System.out.println("You found Ben");
		}
	}
}
