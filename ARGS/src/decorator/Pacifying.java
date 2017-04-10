package decorator;

import enumclass.Enchantment;
/**
 * This is part of the decorator pattern which implements the Pacifying 
 * enchantment of the weapons
 * 
 * @author grey
 * @see WeaponDecorator
 */
public class Pacifying extends WeaponDecorator{
	/**
	 * When creating a decorated Weapon, pass a Weapon to be decorated
	 * as a parameter. Note that this can be an already-decorated Weapon.
	 * @param decoratedWeapon weapon object
	 */
	public Pacifying(Weapon decoratedWeapon) {
		super(decoratedWeapon);
	}
	
	/**
	 *   Overriding methods defined in the abstract superclass. 
	 *   Enables to provide different behavior for decorated Weapon methods
	 *   @param enchantment enchantment
	 */
	@SuppressWarnings("static-access")
	public void  add(Enchantment enchantment){
		if(super.arrayList == null)
			super.add(enchantment);
			
			this.arrayList.add(enchantment);
	}

}
