package decorator;

import enumclass.Enchantment;
/**
 * Abstract decorator class - note that it extends the Weapon abstract class
 * @author grey
 *
 */
public abstract class WeaponDecorator extends Weapon {
	
	protected final Weapon decoratedWeapon;
	/**
	 *  Wraps a Weapon object inside an object of one of 
     * WeaponDecorator's subclasses
	 * @param decoratedWeapon weapon object
	 */
	public WeaponDecorator (Weapon decoratedWeapon){
		this.decoratedWeapon = decoratedWeapon;
	}
	/**
	 * add enchantment to Weapon object
	 */
	public void add(Enchantment enchantment) {
		decoratedWeapon.add(enchantment);
	}
	
}
