package event;

import creature.Enemy;
import creature.SiHorse;

public class SpawnWaterMonster extends SpawnMonster {

	public SpawnWaterMonster() {

	}

	@Override
	public String toString() {
		return "SpawnWaterMonster";
	}

	@Override
	public Enemy getEnemy() {
		return new SiHorse();
	}
	

}
