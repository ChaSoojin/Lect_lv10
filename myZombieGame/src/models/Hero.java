package models;

import zombie_interface.Attackable;
import zombie_interface.Damageable;
import zombie_interface.Repairable;

public class Hero extends Unit implements Attackable, Repairable, Damageable{
	private int drinkCnt = 3;
	
	public Hero(String name, int hp, int att, int def, int pos) {
		super(name, hp, att, def, pos);
	}
	
	public int getDrinkCnt() {
		return this.drinkCnt;
	}
	
	@Override
	public void attack(Damageable damageable) {
		// A instanceof B : A��ü�� B Ŭ������ ��ü�� ������ true�� ��ȯ�ϰ�, Ʋ���� false�� ��ȯ�Ѵ�
		
		Unit target = (Unit) damageable;
		
		if(target instanceof ZombieKing) {
			if(((ZombieKing) target).getShield() > 0) {
				int damage = (this.getAtt() - target.getDef())*(getRanClass().nextInt(150) + 50) / 100;
				if(damage <= 0) damage = 1;
				
				System.out.println(getName() + "�� ����!");
				System.out.println(damage + "�� �����!");
				((ZombieKing) target).setShield(((ZombieKing) target).getShield() - damage);
				
				if(((ZombieKing) target).getShield() <= 0) {
					System.out.println(target.getName() + "�� ���尡 �ڻ쳵��!");
					((ZombieKing) target).setShield(0);
				}
				
				System.out.println(target.getName() + "�� ���� ü�� : " + target.getHp() + " (���� : " + ((ZombieKing) target).getShield()+")");
			}
			else {
				super.attack(damageable);
			}
		}
		else {
			super.attack(damageable);
		}
	}
	
	public void drink() {
		if(drinkCnt > 0) {
			System.out.println("[����]ȸ���� ���ô���...");
			System.out.println("ü���� 100 ȸ�� �Ǿ����ϴ�.");
			
			this.setHp(this.getHp() + 100);
			System.out.println(this.getName() + "�� ���� ü�� : " + this.getHp());
			drinkCnt--;
		}
		
		else System.out.println("[����]������ �����ϴ�.");
	}
}
