import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Hero;
import models.Unit;
import models.Zombie;
import models.ZombieKing;
import zombie_interface.Damageable;

public class Game {
	private Scanner scan = new Scanner(System.in);
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
	
	private int posChk() {
		for(int i = 0; i < enemyList.size(); i++) {
			if(player.getPos() == enemyList.get(i).getPos()) {
				System.out.println("[WARNING] �������!");
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
			System.out.printf("1.����   2.����(%d������)\n", player.getDrinkCnt());
			System.out.println("[����] > ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				if(enemy instanceof Damageable)
				player.attack((Damageable)enemy);
			}
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
			System.out.println("[���] You Lose");
			return false;
		}
		
		else {
			System.out.println("[�¸�] You Win");
			return true;
		}
	}
	
	private void map(int act) {
		System.out.println("[������ : " + player.getPos() + "]");
		System.out.println("[1] : �ö󰣴�");
		
		if(act == 1) {
			System.out.println("[2] : ü���� ȸ���Ѵ�.");
			System.out.println("[3] : ���⸦ ��ȭ�Ѵ�.");
		}
	}
	
	public void run() {
		unitInit();
		int act = 1;
		
		while(true) {
			if(player.getPos() >= 12) {
				System.out.println("������ �����ߴ�!!");
				break;
			}
			
			map(act);
			int sel = scan.nextInt();
			
			if(sel == 1) {
				player.setPos(player.getPos() + 1);
				int chk = posChk();
				
				if(chk != -1) {
					if(!fight(enemyList.get(chk))) break;
				}
				
				else System.out.println("�ƹ��ϵ� �Ͼ�� �ʾҴ�..");
				
				act = 1;
			}
			
			else if(sel == 2 && act == 1) {
				int rHp = Unit.getRanClass().nextInt(40) + 20;
				
				player.setHp(player.getHp() + rHp);
				System.out.println("ü�� " + rHp + "��ŭ ȸ��!");
				act = 2;
			}
			
			else if(sel == 3 && act == 1) {
				int rAtt = Unit.getRanClass().nextInt(2) + 1;
				
				if(rAtt == 1) {
					rAtt = Unit.getRanClass().nextInt(3) + 1;
					player.setAtt(player.getAtt() + rAtt);
					System.out.println("���ݷ� " + rAtt + "��ŭ ����!");
				}
				
				else if(rAtt == 2) {
					rAtt = Unit.getRanClass().nextInt(3) + 1;
					player.setDef(player.getDef() + rAtt);
					System.out.println("������ " + rAtt + "��ŭ ����!");
				}
				act = 2;
			}
		}
	}
}
