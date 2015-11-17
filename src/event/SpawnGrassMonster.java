package event;

import creature.Enemy;
import creature.Tanimonster;

public class SpawnGrassMonster extends SpawnMonster {

	public SpawnGrassMonster() {

	}
	

	@Override
	public String toString() {
		return "SpawnGrassMonster";
	}


	@Override
	public Enemy getEnemy() {
		return new Tanimonster();
	}

}
