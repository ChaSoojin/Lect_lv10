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
		// A instanceof B : A객체가 B 클래스의 객체가 맞으면 true를 반환하고, 틀리면 false를 반환한다
		
		Unit target = (Unit) damageable;
		
		if(target instanceof ZombieKing) {
			if(((ZombieKing) target).getShield() > 0) {
				int damage = (this.getAtt() - target.getDef())*(getRanClass().nextInt(150) + 50) / 100;
				if(damage <= 0) damage = 1;
				
				System.out.println(getName() + "의 공격!");
				System.out.println(damage + "의 대미지!");
				((ZombieKing) target).setShield(((ZombieKing) target).getShield() - damage);
				
				if(((ZombieKing) target).getShield() <= 0) {
					System.out.println(target.getName() + "의 쉴드가 박살났다!");
					((ZombieKing) target).setShield(0);
				}
				
				System.out.println(target.getName() + "의 남은 체력 : " + target.getHp() + " (쉴드 : " + ((ZombieKing) target).getShield()+")");
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
			System.out.println("[성공]회복약 마시는중...");
			System.out.println("체력이 100 회복 되었습니다.");
			
			this.setHp(this.getHp() + 100);
			System.out.println(this.getName() + "의 남은 체력 : " + this.getHp());
			drinkCnt--;
		}
		
		else System.out.println("[실패]물약이 없습니다.");
	}
}
