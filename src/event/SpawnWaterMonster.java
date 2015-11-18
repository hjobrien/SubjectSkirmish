package event;

import creature.Creature;
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
		return new SiHorse();
	}
	

}
