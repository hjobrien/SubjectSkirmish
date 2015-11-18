package event;

import java.util.Random;

import creature.Armadylan;
import creature.Creature;
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
		int i = rand.nextInt(2);
		if(i==0)
			return new Tanimonster();
		else
			return new Armadylan();
	}

}
