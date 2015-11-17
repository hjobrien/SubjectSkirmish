package event;

import creature.Enemy;
import creature.Tanimonster;

public class SpawnWaterMonster extends SpawnMonster {

	public SpawnWaterMonster() {

	}

	@Override
	public String toString() {
		return "SpawnWaterMonster";
	}

	@Override
	public Enemy getEnemy() {
		// TODO Auto-generated method stub
		return new Tanimonster();
	}
	

}
