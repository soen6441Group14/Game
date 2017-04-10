package decorator;

import java.util.ArrayList;

import enumclass.Enchantment;
/**
 *  The abstract Weapon class defines the functionality of any Weapon 
 * implemented by subclasses of Weapon
 * @author grey
 *
 */
public abstract class Weapon {
	
	public static ArrayList<Enchantment> arrayList = new ArrayList<>();
	/**
	 * abstract method to add enchantment
	 * @param enchantment  enchantment 
	 */
	public abstract void add(Enchantment enchantment);
}
