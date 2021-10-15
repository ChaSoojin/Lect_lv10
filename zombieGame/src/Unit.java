import java.util.Random;

public abstract class Unit {
	static Random ran = new Random();
	private String name;
	private int hp;
	private int att;
	private int def;
	private int pos;
	
	public Unit(String name, int hp, int att, int def, int pos) {
		this.name = name;
		this.hp = hp;
		this.att = att;
		this.def = def;
		this.pos = pos;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getAtt() {
		return this.att;
	}
	
	public int getDef() {
		return this.def;
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public void setAtt(int att) {
		this.att = att;
	}
	
	public void setDef(int def) {
		this.def = def;
	}
	
	public void attack(Unit target) {
		int dam = (this.att - target.def)*(ran.nextInt(150)+50)/100;
		if(dam <= 0) dam = 1;
		System.out.println(name + "�� ����!");
		System.out.println(dam + "�� �����!");
		target.setHp(target.getHp() - dam);
		System.out.println(target.name + "�� ���� ü�� : " + target.hp);
	}
		
	@Override
	public String toString() {
		String unitInfo = "";
		unitInfo += "[�̸�] : " + this.name + "    [ü��] : " + this.hp;
		unitInfo += "[���ݷ�] : " + this.att + "  [����] : " + this.def + "  [��ġ] : " + this.pos;
		
		return unitInfo;
	}
}
