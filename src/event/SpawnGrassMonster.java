package event;

import java.util.Random;

import creature.Armadylan;
import creature.Bunny;
import creature.Creature;
import creature.Gregg;
import creature.Tanimonster;

public class SpawnGrassMonster extends SpawnMonster {

	public SpawnGrassMonster() {

	}
	

	@Override
	public String toString() {
		return "SpawnGrassMonster";
	}


	@Override
	public Creature getCreature() {
		Random rand = new Random();
		int i = rand.nextInt(4);
		if(i==0){
			return new Tanimonster();
		} else if (i == 1){
			return new Armadylan();
		} else if (i == 2){
			return new Gregg();
		} else {
			return new Bunny();
		}
	}

}
