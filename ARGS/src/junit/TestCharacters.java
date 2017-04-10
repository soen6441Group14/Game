package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import enumclass.Orientation;
import objects.Characters;
import objects.Items;

public class TestCharacters {
	
	static ArrayList<Items> inventory = new ArrayList<>();
	static ArrayList<Items> backpack = new ArrayList<>();
	static Characters characters;
	static Characters monster;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Items weapon = new Items("WEAPON1", 1, "damageBonus");
		weapon.setRange(1);
		inventory.add(weapon);
		characters = new Characters("PLAYER", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.PLAYER, 3, 3, 3, inventory, backpack);
		monster = new Characters("Monster", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.HOSTILE, 15, 2, 2, inventory, backpack);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAttackBonus() {
		characters.clickAttack(monster);
		if(5 == characters.getAttackBonus()+characters.getModStr())
			assertTrue(true);
		else {
			assertTrue(false);
		}
	}
	
	@Test
	public void testAttack() {
		characters.clickAttack(monster);
		if(characters.attackBonus + characters.getD20()>=monster.getArmorClass())
			assertTrue(true);
		else {
			assertTrue(false);
		}
//		System.out.println(characters.attackBonus + characters.getD20());
//		System.out.println(monster.getArmorClass());
	}
	
	@Test
	public void testDamageBonus() {
		characters.clickAttack(monster);
		if(characters.getD8()+Math.abs(characters.getModStr())<=10)
			assertTrue(true);
		else {
			assertTrue(false);
		}
	}

}
