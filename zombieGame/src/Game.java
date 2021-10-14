import java.util.ArrayList;

public class Game {
	private static Game instance = new Game();
	private ArrayList<Unit> enemy;
	private Hero player;
	
	private Game() {
		this.enemy = new ArrayList<Unit>();
	}
	
	public static Game getinstance() {
		return instance;
	}
	
	private void init() {
		this.player = new Hero("���", 100, 5, 1, 1);
		enemy.add(new Zombie("�׳�����", 25, 5, 1, 3));
		enemy.add(new Zombie("��������", 45, 10, 2, 6));
		enemy.add(new Zombie("��������", 65, 15, 3, 9));
		//enemy.add(new ZombieKing("�����",100,20,4,12,50));
	}
	
	public void run() {
		
	}
}
