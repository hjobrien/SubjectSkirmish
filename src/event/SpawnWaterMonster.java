package event;

import java.util.Random;

import creature.Creature;
import creature.Douglet;
import creature.Quax;
import creature.SiHorse;

public class SpawnWaterMonster extends SpawnMonster {

	public SpawnWaterMonster() {

	}

	@Override
	public String toString() {
		return "SpawnWaterMonster";
	}

	@Override
	public Creature getCreature() {
		Random rand = new Random();
		int i = rand.nextInt(3);
		if(i == 0){
			return new Douglet();
		} else if (i == 1){
			return new Quax();
		} else {
			return new SiHorse();
		}
	}
	

}
