package decorator;

import enumclass.Enchantment;

public class Slaying extends WeaponDecorator{

	public Slaying(Weapon decoratedWeapon) {
		super(decoratedWeapon);
	}
	
	
	@SuppressWarnings("static-access")
	public void add(Enchantment enchantment){
		if(super.arrayList == null)
			super.add(enchantment);
			
			this.arrayList.add(enchantment);
	}

}
