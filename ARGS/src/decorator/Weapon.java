package decorator;

import java.util.ArrayList;

import enumclass.Enchantment;

public abstract class Weapon {
	
	public static ArrayList<Enchantment> arrayList = new ArrayList<>();
	
	public abstract void add(Enchantment enchantment);
}
