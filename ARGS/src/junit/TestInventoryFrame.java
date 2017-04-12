package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import enumclass.Orientation;
import frame.InventoryFrame;
import frame.Map;
import objects.Characters;
import objects.Items;
/* test inventory frame functionality*/
public class TestInventoryFrame {
	
	Map map;
	JFrame jFrame = new JFrame();
	static ArrayList<Characters> characterArrayList = new ArrayList<>();
	ArrayList<Items> itemArrayList = new ArrayList<>();
	
	static ArrayList<Items> inventory = new ArrayList<>();
	static ArrayList<Items> backpack = new ArrayList<>();
	static Characters characters;
	
	InventoryFrame inventoryFrame = new InventoryFrame(map, jFrame, characterArrayList, characters);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Items items = new Items("WEAPON1", 1,"");
		backpack.add(items);
		characters = new Characters("PLAYER", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.FRIENDLY, 2, 2, 2, inventory, backpack);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEquip() {
		String name = "WEAPON1";
		int value = inventoryFrame.getBackpackValue(name, characters);
		if(value == 1)
			assertTrue(true);
		else 
			assertTrue(false);
		
	}

}
