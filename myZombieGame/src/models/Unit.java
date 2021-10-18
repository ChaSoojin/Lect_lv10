package models;

import java.util.Random;

import zombie_interface.Attackable;

public abstract class Unit implements Attackable{
	private static Random ran = new Random();
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
	
	public static Random getRanClass() {
		return ran;
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
	
	@Override
	public String toString() {
		String unitInfo = "";
		unitInfo += "[이름] : " + this.name + "    [체력] : " + this.hp;
		unitInfo += "[공격력] : " + this.att + "  [방어력] : " + this.def + "  [위치] : " + this.pos;
		
		return unitInfo;
	}

	@Override
	public void attack(Unit target) {
		int damage = (this.att - target.def)*(ran.nextInt(150)+50)/100;
		if(damage <= 0) damage = 1;
		System.out.println(this.name + "의 공격!");
		System.out.println(damage + "의 대미지!");
		target.setHp(target.getHp() - damage);
		System.out.println(target.name + "의 남은 체력 : " + target.hp);
	}
}
