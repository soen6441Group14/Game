package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import javax.swing.JFrame;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import enumclass.Orientation;
import frame.CharacterFrame;
import frame.Map;
import objects.Characters;
import objects.Items;

/* test characterframe functionality*/
public class TestCharacterFrame {
	Map map;
	JFrame jFrame = new JFrame();
	static ArrayList<Characters> characterArrayList = new ArrayList<>();
	ArrayList<Items> itemArrayList = new ArrayList<>();
	
	static ArrayList<Items> inventory = new ArrayList<>();
	static ArrayList<Items> backpack = new ArrayList<>();
	static Characters characters;
	
	CharacterFrame characterFrame = new CharacterFrame(map, jFrame, characterArrayList, itemArrayList);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		characters = new Characters("PLAYER", 1, 20, 3, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, 10, 2, Orientation.FRIENDLY, 2, 2, 2, inventory, backpack);
		characterArrayList.add(characters);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void  testLoad(){
		Characters newCharacters = null;
		newCharacters = characterFrame.load(characterArrayList, "PLAYER");
		if(newCharacters.getLevel() == 1)
			assertTrue(true);
		else
			assertTrue(false);
	}
	
	@Test
	public void testGetValues() {
		if(characterFrame.getValues()<=18)
			assertTrue(true);
		else
			assertTrue(false);
	}

}
