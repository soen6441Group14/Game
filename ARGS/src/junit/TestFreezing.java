package junit;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import decorator.Burning;
import decorator.Empty;
import decorator.Freezing;
import decorator.Slaying;
import decorator.Weapon;
import enumclass.Enchantment;
/* test freezing functionality*/
public class TestFreezing {
	static Weapon weapon = new Empty();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		weapon.arrayList.clear();
//		System.out.println(weapon.arrayList.get(0));
	}

	@Test
	public void testFreezing() {
		
		weapon = new Freezing(weapon);
		weapon.add(Enum.valueOf(Enchantment.class, "Freezing"));
//		System.out.println("0 "+weapon.arrayList.get(0));
		if(weapon.arrayList.get(0) == Enchantment.Freezing)
			assertTrue(true);
		else 
			assertTrue(false);
	}
	
	@Test
	public void testBurning() {
		
		weapon = new Burning(weapon);
		weapon.add(Enum.valueOf(Enchantment.class, "Burning"));
//		System.out.println("1 "+weapon.arrayList.get(1));
		if(weapon.arrayList.get(1) == Enchantment.Burning)
			assertTrue(true);
		else 
			assertTrue(false);
	}
	
	@Test
	public void testSlaying() {
		
		weapon = new Slaying(weapon);
		weapon.add(Enum.valueOf(Enchantment.class, "Slaying"));
//		System.out.println("2 "+weapon.arrayList.get(2));
		if(weapon.arrayList.get(2) == Enchantment.Slaying)
			assertTrue(true);
		else 
			assertTrue(false);
	}
	
//	@Test
//	public void testFrightening() {
//		
//		weapon = new Frightening(weapon);
//		weapon.add(Enum.valueOf(Enchantment.class, "Frightening"));
//		System.out.println("3 "+weapon.arrayList.get(3));
//		if(weapon.arrayList.get(3) == Enchantment.Frightening)
//			assertTrue(true);
//		else 
//			assertTrue(false);
//	}
	
//	@Test
//	public void testPacifying() {
//		
//		weapon = new Pacifying(weapon);
//		weapon.add(Enum.valueOf(Enchantment.class, "Pacifying"));
//		System.out.println("4 "+weapon.arrayList.get(4));
//		if(weapon.arrayList.get(4) == Enchantment.Pacifying)
//			assertTrue(true);
//		else 
//			assertTrue(false);
//	}

}
