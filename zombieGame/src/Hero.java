
public class Hero extends Unit {
	int drinkCnt = 3;
	
	public Hero(String name, int hp, int att, int def, int pos) {
		super(name, hp, att, def, pos);
	}
	
	public void drink() {
		if(drinkCnt > 0) {
			System.out.println("회복약을 마십니다.");
			System.out.println("체력이 100회복 되었습니다.");
			
			this.setHp(this.getHp() + 100);
			System.out.println(this.getName() + "의 남은 체력 : " + this.getHp());
			drinkCnt--;
		}
		
		else System.out.println("물약이 없습니다.");
	}
	
	public void attack(Unit target) {
		// A instanceof B : A객체가 B 클래스의 객체가 맞으면 true를 반환하고, 틀리면 false를 반환한다
		
		if(target instanceof ZombieKing) {
			if(((ZombieKing) target).shield > 0) {
				int damage = (this.getAtt() - target.getDef())*(ran.nextInt(150) + 50) / 100;
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
		}
	}
}
