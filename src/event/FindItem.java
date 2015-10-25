package event;

import java.util.Random;

public class FindItem implements Event {

	public FindItem(){
		
	}

	@Override
	public String toString() {
		String output = "Item Found \n";
		Random r = new Random();
		int itemChance = r.nextInt(10);
		if (itemChance == 0){
			output += "You found Eli";
		} else if (itemChance < 3){
			output += "You found Akaash";
		} else if (itemChance < 10){
			output += "You found Ben";
		}
		return output;
	}
	
	
}
