package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import enumclass.Orientation;
import objects.Characters;
import objects.Items;

public class TestAttackRoll {

	Characters player, mons;
	Items item1, item2;
	ArrayList<Items> testInventory;

	@Before
	public void before() {

		player = new Characters("test", 1, 100, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, Orientation.PLAYER, 1, 1, 1,
				null, null);
		mons = new Characters("test", 1, 100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, Orientation.HOSTILE, 1, 1, 1, null,
				null);

		testInventory = new ArrayList<Items>();
		for (int i = 0; i < 10; i++) {
			Items empty = new Items("EMPTY", 0, "");
			testInventory.add(empty);
		}
		item1 = new Items("Weapon1", 1, "");
		item2 = new Items("Ring1", 1, "");
		testInventory.add(0, item1);
		testInventory.add(1, item2);

		player.setInventory(testInventory);

	}

	// test randomizer
	@Test
	public void testD20_randomizer() {

		int testValue = player.getD20();
		assertTrue(testValue > 0 && testValue <= 20);

	}

	// test randomizer
	@Test
	public void testD8_randomizer() {

		int testValue = player.getD8();
		assertTrue(testValue > 0 && testValue <= 8);

	}

	// based on d8 roll
	@Test
	public void testAttackCalculation_d8_damageValue_1() {

		int d20roll = 20;
		int d8roll = 4;
		player.attackCalculation(mons, d20roll, d8roll);
		// hand calculation says 91
		assertEquals(91, mons.getHitpoints());

	}

	// based on d8 roll
	@Test
	public void testAttackCalculation_d8_damageValue_2() {

		int d20roll = 20;
		int d8roll = 8;
		player.attackCalculation(mons, d20roll, d8roll);
		// hand calculation says 87
		assertEquals(87, mons.getHitpoints());

	}

	// based on d20 roll
	@Test
	public void testAttackCalculation_d20_attackOrNot_1() {

		int d20roll = 20;
		int d8roll = 8;
		mons.armorClass = 4;
		int monsHitPoints = mons.getHitpoints();
		player.attackCalculation(mons, d20roll, d8roll);
		assertTrue(mons.getHitpoints() < monsHitPoints);

	}

	// based on d20 roll
	@Test
	public void testAttackCalculation_d20_attackOrNot_2() {

		int d20roll = 1;
		int d8roll = 8;
		int monsHitPoints = mons.getHitpoints();
		mons.armorClass = 4;
		player.attackCalculation(mons, d20roll, d8roll);
		int attackBonus = player.attackBonus;
		System.out.println(attackBonus);
		int armorClass = mons.getArmorClass();
		System.out.println(armorClass);
		assertTrue(mons.getHitpoints() == monsHitPoints);

	}

	// based on armor class
	@Test
	public void testAttackCalculation_armorClass_attackOrNot_1() {

		int d20roll = 20;
		int d8roll = 8;
		int monsHitPoints = mons.getHitpoints();
		mons.armorClass = 22;
		player.attackCalculation(mons, d20roll, d8roll);
		assertTrue(mons.getHitpoints() < monsHitPoints);

	}

	// based on armor class
	@Test
	public void testAttackCalculation_armorClass_attackOrNot_2() {

		int d20roll = 20;
		int d8roll = 8;
		int monsHitPoints = mons.getHitpoints();
		mons.armorClass = 23;
		player.attackCalculation(mons, d20roll, d8roll);
		assertFalse(mons.getHitpoints() < monsHitPoints);

	}
	
	// based on attack bonus
	@Test
	public void testAttackCalculation_attackBonus_attackOrNot_3() {

		int d20roll = 20;
		int d8roll = 8;
		int monsHitPoints = mons.getHitpoints();
		mons.armorClass = 23;
		player.attackBonus = 2;
		player.attackCalculation(mons, d20roll, d8roll);
		assertTrue(mons.getHitpoints() < monsHitPoints);

	}
	

}
