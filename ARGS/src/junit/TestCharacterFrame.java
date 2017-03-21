package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import javax.swing.JFrame;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import frame.CharacterFrame;
import frame.Map;
import objects.Characters;
import objects.Items;

public class TestCharacterFrame {
	Map map;
	JFrame jFrame = new JFrame();
	ArrayList<Characters> characterArrayList = new ArrayList<>();
	ArrayList<Items> itemArrayList = new ArrayList<>();
	
	CharacterFrame characterFrame = new CharacterFrame(map, jFrame, characterArrayList, itemArrayList);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testGetValues() {
		if(characterFrame.getValues()<=18)
			assertTrue(true);
		else
			assertTrue(false);
	}

}
