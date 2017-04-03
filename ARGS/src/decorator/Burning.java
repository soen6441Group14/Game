package decorator;

import enumclass.Enchantment;

public class Burning extends WeaponDecorator {

	public Burning(Weapon decoratedWeapon) {
		super(decoratedWeapon);
		// TODO Auto-generated constructor stub
	}


	@SuppressWarnings("static-access")
	@Override
	public void add(Enchantment enchantment) {
		if(super.arrayList == null)
		super.add(enchantment);
		
		this.arrayList.add(enchantment);
	}


	
}
