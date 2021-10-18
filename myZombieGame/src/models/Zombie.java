package models;

import zombie_interface.Attackable;
import zombie_interface.Damageable;

public class Zombie extends Unit implements Attackable, Damageable{
	
	public Zombie(String name, int hp, int att, int def, int pos) {
		super(name, hp, att, def, pos);
	}
	
	@Override
	public void attack(Damageable damageable) {
		super.attack(damageable);
	}

	@Override
	public void damage(int att) {
		
	}
}
