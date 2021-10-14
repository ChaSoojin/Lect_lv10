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
		this.player = new Hero("용사", 100, 5, 1, 1);
		enemy.add(new Zombie("그냥좀비", 25, 5, 1, 3));
		enemy.add(new Zombie("힘쌘좀비", 45, 10, 2, 6));
		enemy.add(new Zombie("정예좀비", 65, 15, 3, 9));
		//enemy.add(new ZombieKing("좀비왕",100,20,4,12,50));
	}
	
	public void run() {
		
	}
}
