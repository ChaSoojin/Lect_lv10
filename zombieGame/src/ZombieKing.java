
public class ZombieKing extends Unit{
	int shield;
	
	public ZombieKing(String name, int hp, int att, int def, int pos, int shield) {
		super(name, hp, att, def, pos);
		this.shield = shield;
	}
	
	public int getShield() {
		return this.shield;
	}
	
	public void setShield(int shield) {
		this.shield = shield;
	}

	@Override
	public void attack(Unit target) {
		if(ran.nextInt(100) > 74) {
			int damage = (this.getAtt() - target.getDef()) * (ran.nextInt(150) + 50) / 100;
			
			if(damage <= 0) damage = 1;
			
			damage *= 2;
			System.out.println(getName() + "�� �ʻ��!!!");
			System.out.println(damage + "�� �����!");
			
			target.setHp(target.getHp() - damage);
			System.out.println(target.getName() + "�� ���� ü�� : " + target.getHp());
		}
	}
}
