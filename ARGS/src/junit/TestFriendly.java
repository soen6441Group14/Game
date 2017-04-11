package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import strategy.Friendly;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Ground;
import objects.Items;

public class TestFriendly {
	static ArrayList<Items> inventory = new ArrayList<>();
	static ArrayList<Items> backpack = new ArrayList<>();
	static Characters characters;
	static Map map = new Map("Map1",10,10);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		characters = new Characters("PLAYER", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.PLAYER, 3, 3, 3, inventory, backpack);
		map.setMap(new Cells[10][10], 10, 10);
		for (int rows = 0; rows < 10; rows++)
			for (int cols = 0; cols < 10; cols++) {
				map.map[rows][cols] = new Cells(TileType.GROUND, 10, 10, new Ground(TileType.GROUND));
			}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testFriendly() {
		Friendly friendly = new Friendly(map, characters);
		if(characters == friendly.theFriendly)
			assertTrue(true);
		else
			assertTrue(false);
	}

}
