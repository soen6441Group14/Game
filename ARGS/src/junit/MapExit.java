package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import actionListener.PanelListener;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Entry;
import objects.Exit;
import objects.Ground;
import objects.Items;
import objects.Wall;

public class MapExit {

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
	public void test() {
		
		testmap.map[1][9] = new Cells(TileType.ENTRY, 10, 10, new Entry(TileType.ENTRY));
		testmap.map[2][9] = new Cells(TileType.EXIT, 10, 10, new Exit(TileType.EXIT));
		testmap.map[3][9] = new Cells(TileType.MONSTER, 10, 10, new Characters("MONSTER1",1,10,1,1,2,2,2,1,2,1,2,2,1,1,2,Orientation.HOSTILE,1,1,1, null,null));
		
		PanelListener panelListener = new PanelListener(testmap,1);
		assertTrue(panelListener.checkCompleteObjective());
		
	}

}
