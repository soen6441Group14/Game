package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Exit;
import objects.Ground;

public class Equip {

	Map testmap = new Map("Map1",10,10);
	
	
	@Before
	public void before() {
		testmap.map = new Cells[10][10];

		for (int rows = 0; rows < 10; rows++)
			for (int cols = 0; cols < 10; cols++) {
				testmap.map[rows][cols] = new Cells(TileType.GROUND, 10, 10, new Ground(TileType.GROUND));

			}

	}
	
	@Test
	public void test_Exit() {
		
		testmap.map[1][1] = new Cells(TileType.EXIT,1,1,new Exit(TileType.EXIT));
		assertTrue(testmap.exitGlobal(testmap.map));
		

		
	}@Test
	public void test_Entry() {
		
		testmap.map[1][1] = new Cells(TileType.ENTRY,1,1,new Exit(TileType.ENTRY));
		assertTrue(testmap.entryGlobal(testmap.map));
		

		
	}@Test
	public void test_Player() {
		
		testmap.map[1][1] = new Cells(TileType.HERO,1,1,new Exit(TileType.HERO));
		assertTrue(testmap.playerGlobal(testmap.map));
		

		
	}
	

	
	
	
}
