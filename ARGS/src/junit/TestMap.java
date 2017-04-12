package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Entry;
import objects.Exit;
import objects.Ground;
import objects.Items;
/* test map functionality*/
public class TestMap {
	
	static Map testmap = new Map("Map1",10,10);
	static Characters characters;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		JComboBox<String> characterMapBox = new JComboBox<String>();
//		characterMapBox.addItem("PLAYER");
		
		testmap.setMap(new Cells[10][10], 10, 10);
		ArrayList<Items> inventory = new ArrayList<>();
		ArrayList<Items> backpack = new ArrayList<>();
		characters = new Characters("PLAYER", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.FRIENDLY, 2, 2, 2, inventory, backpack);

		for (int rows = 0; rows < 10; rows++)
			for (int cols = 0; cols < 10; cols++) {
				testmap.map[rows][cols] = new Cells(TileType.GROUND, 10, 10, new Ground(TileType.GROUND));
			}
//		testmap.map[5][5] = new Cells(TileType.ENTRY, 10, 10, new Entry(TileType.ENTRY));
		testmap.map[2][2] = new Cells(TileType.HERO, 10, 10, characters);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testGetCharacterMap(){
//		Characters newCharacter;
//		testmap.characterMapBox.addItem("PLAYER");
//		newCharacter = testmap.getCharacterMap();
		
		if(characters.getName().equals("PLAYER"))
			assertTrue(true);
		else
			assertTrue(false);
	}
	
	@Test
	public void testVerifyMap(){
		int[] flag = testmap.verifyMap(0, 0, 0);
//		System.out.println(flag[0]+" "+flag[1]+" "+flag[2]);
		if(flag[0]==0){
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	
	@Test
	public void test_Exit() {
		
		testmap.map[1][1] = new Cells(TileType.EXIT,1,1,new Exit(TileType.EXIT));
		assertTrue(testmap.exitGlobal(testmap.map));
		

		
	}@Test
	public void test_Entry() {
		
		testmap.map[1][1] = new Cells(TileType.ENTRY,1,1,new Entry(TileType.ENTRY));
		assertTrue(testmap.entryGlobal(testmap.map));
		

		
	}@Test
	public void test_Player() {
		
		testmap.map[1][1] = new Cells(TileType.HERO,1,1,characters);
		assertTrue(testmap.playerGlobal(testmap.map));
		
	}
}
