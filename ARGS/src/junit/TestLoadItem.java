package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import load.LoadItem;
import objects.Items;

public class TestLoadItem {
	
	LoadItem loadItem = new LoadItem();
	static ArrayList<Items> itemArrayList = new ArrayList<>();
	static String name = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		Items items = new Items("WEAPON1", 1,"");
		Items items2 = new Items("SHIELD1", 2,"");
		itemArrayList.add(items);
		itemArrayList.add(items2);
		name = "WEAPON1";
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testLoadItem() {
		
		if(loadItem.loadItem(name, itemArrayList).getValue() == 1)
			assertTrue(true);
		else
			assertTrue(false);
		
	}

}
