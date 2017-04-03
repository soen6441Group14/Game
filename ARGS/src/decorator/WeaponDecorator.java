package decorator;

import enumclass.Enchantment;

public abstract class WeaponDecorator extends Weapon {
	
	protected final Weapon decoratedWeapon;
	
	public WeaponDecorator (Weapon decoratedWeapon){
		this.decoratedWeapon = decoratedWeapon;
	}
	
	public void add(Enchantment enchantment) {
		decoratedWeapon.add(enchantment);
	}
	
}
