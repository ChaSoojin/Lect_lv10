import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private static Game instance = new Game();
	private ArrayList<Unit> enemy;
	private Hero player;
	Scanner scan = new Scanner(System.in);
	
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
		enemy.add(new ZombieKing("좀비왕",100,20,4,12,50));
	}
	
	private int posChk() {
		for(int i = 0; i < enemy.size(); i++) {
			if(player.getPos() == enemy.get(i).getPos()) {
				System.out.println("[WARNING] 좀비등장!");
				return i;
			}
		}
		return -1;
	}
	
	private int dieChk(Unit unit) {
		if(player.getHp() <= 0) return 1;
		else if(unit.getHp() <= 0) return 2;
		else return 0;
	}
	
	private boolean fight(Unit enemy) {
		while(true) {
			System.out.println(player);
			System.out.println("===== VS =====");
			System.out.println(enemy);
			System.out.println("--------------");
			System.out.printf("1.공격   2.물약(%d개남음)\n", player.drinkCnt);
			System.out.println("[선택] > ");
			int sel = scan.nextInt();
			
			if(sel == 1) player.attack(enemy);
			else if(sel == 2) player.drink();
			
			if(dieChk(enemy) != 0) {
				break;
			}
			System.out.println();
			
			enemy.attack(player);
			
			if(dieChk(enemy)!= 0) {
				break;
			}
			System.out.println();
		}
		
		
		if(dieChk(enemy) == 1) {
			System.out.println("[사망] You Lose");
			return false;
		}
		
		else {
			System.out.println("[승리] You Win");
			return true;
		}
	}
	
	private void map(int act) {
		System.out.println("[현재층 : " + player.getPos() + "]");
		System.out.println("[1] : 올라간다");
		
		if(act == 1) {
			System.out.println("[2] : 체력을 회복한다.");
			System.out.println("[3] : 무기를 강화한다.");
		}
	}
	
	public void run() {
		init();
		int act = 1;
		
		while(true) {
			if(player.getPos() >= 12) {
				System.out.println("생존에 성공했다!!");
				break;
			}
			
			map(act);
			int sel = scan.nextInt();
			
			if(sel == 1) {
				player.setPos(player.getPos() + 1);
				int chk = posChk();
				
				if(chk != -1) {
					if(!fight(enemy.get(chk))) break;
				}
				
				else System.out.println("아무일도 일어나지 않았다..");
				
				act = 1;
			}
			
			else if(sel == 2 && act == 1) {
				int rHp = Unit.ran.nextInt(40) + 20;
				
				player.setHp(player.getHp() + rHp);
				System.out.println("체력 " + rHp + "만큼 회복!");
				act = 2;
			}
			
			else if(sel == 3 && act == 1) {
				int rAtt = Unit.ran.nextInt(2) + 1;
				
				if(rAtt == 1) {
					rAtt = Unit.ran.nextInt(3) + 1;
					player.setAtt(player.getAtt() + rAtt);
					System.out.println("공격력 " + rAtt + "만큼 증가!");
				}
				
				else if(rAtt == 2) {
					rAtt = Unit.ran.nextInt(3) + 1;
					player.setDef(player.getDef() + rAtt);
					System.out.println("방어력이 " + rAtt + "만큼 증가!");
				}
				act = 2;
			}
		}
	}
}
