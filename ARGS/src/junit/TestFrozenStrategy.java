package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import strategy.Frozen;
import enumclass.Orientation;
import objects.Characters;
import objects.Items;

public class TestFrozenStrategy {
	static ArrayList<Items> inventory = new ArrayList<>();
	static ArrayList<Items> backpack = new ArrayList<>();
	static Characters characters;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		characters = new Characters("PLAYER", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.PLAYER, 3, 3, 3, inventory, backpack);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testFrozenStrategy() {
		Frozen frozen = new Frozen(characters);
		if(characters == frozen.frozenedCharacter)
			assertTrue(true);
		else
			assertTrue(false);
	}

}
