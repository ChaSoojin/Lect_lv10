
public class Hero extends Unit {
	int drinkCnt = 3;
	
	public Hero(String name, int hp, int att, int def, int pos) {
		super(name, hp, att, def, pos);
	}
	
	public void drink() {
		if(drinkCnt > 0) {
			System.out.println("ȸ������ ���ʴϴ�.");
			System.out.println("ü���� 100ȸ�� �Ǿ����ϴ�.");
			
			this.setHp(this.getHp() + 100);
			System.out.println(this.getName() + "�� ���� ü�� : " + this.getHp());
			drinkCnt--;
		}
		
		else System.out.println("������ �����ϴ�.");
	}
	
	public void attack(Unit target) {
		// A instanceof B : A��ü�� B Ŭ������ ��ü�� ������ true�� ��ȯ�ϰ�, Ʋ���� false�� ��ȯ�Ѵ�
		
		if(target instanceof ZombieKing) {
			if(((ZombieKing) target).shield > 0) {
				int damage = (this.getAtt() - target.getDef())*(ran.nextInt(150) + 50) / 100;
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
		}
	}
}
