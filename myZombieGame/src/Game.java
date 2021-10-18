import java.util.ArrayList;
import java.util.Random;

import models.Hero;
import models.Unit;
import models.Zombie;
import models.ZombieKing;

public class Game {
	private static Game instance = new Game();
	private ArrayList<Unit> enemyList = null;
	Hero player;
	
	private Game() {
		this.enemyList = new ArrayList<Unit>();
	}
	
	public static Game getInstance() {
		return instance;
	}
	
	private void unitInit() {
		this.player = new Hero("���", 100, 5, 1, 1);
		enemyList.add(new Zombie("�׳�����", 25, 5, 1, 3));
		enemyList.add(new Zombie("��������", 45, 10, 2, 6));
		enemyList.add(new Zombie("��������", 65, 15, 3, 9));
		enemyList.add(new ZombieKing("�����",100,20,4,12,50));
	}
	
	public void run() {
		
	}
}
