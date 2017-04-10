package decorator;

import enumclass.Enchantment;
/**
 * This is part of the decorator pattern which implements the Burning 
 * enchantment of the weapons
 * 
 * @author grey
 * @see WeaponDecorator
 */
public class Burning extends WeaponDecorator {

	public Burning(Weapon decoratedWeapon) {
		super(decoratedWeapon);
	}


	@SuppressWarnings("static-access")
	@Override
	public void add(Enchantment enchantment) {
		if(super.arrayList == null)
		super.add(enchantment);
		
		this.arrayList.add(enchantment);
	}


	
}
