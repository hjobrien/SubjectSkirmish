package event;

import java.util.Random;

import creature.Creature;
import creature.Douglet;
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
		int i = rand.nextInt(2);
		if(i == 1){
			return new Douglet();
		}
		else
			return new SiHorse();
	}
	

}
