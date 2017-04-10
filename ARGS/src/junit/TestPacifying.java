package junit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import decorator.Empty;
import decorator.Pacifying;
import decorator.Weapon;
import enumclass.Enchantment;

public class TestPacifying {
	static Weapon weapon = new Empty();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		weapon.arrayList.clear();
	}

	@Test
	public void testPacifying() {
		
		weapon = new Pacifying(weapon);
		weapon.add(Enum.valueOf(Enchantment.class, "Pacifying"));
//		System.out.println("0 "+weapon.arrayList.get(0));
		if(weapon.arrayList.get(0) == Enchantment.Pacifying)
			assertTrue(true);
		else 
			assertTrue(false);
	}

}
