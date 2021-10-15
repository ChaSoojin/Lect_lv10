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
	
	public abstract void attack(Unit target);
	
	
	@Override
	public String toString() {
		String unitInfo = "";
		unitInfo += "[이름] : " + this.name + "    [체력] : " + this.hp;
		unitInfo += "[공격력] : " + this.att + "  [방어력] : " + this.def + "  [위치] : " + this.pos;
		
		return unitInfo;
	}
}
