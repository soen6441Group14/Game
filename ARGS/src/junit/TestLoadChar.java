package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import load.LoadCharacter;
import objects.Characters;

public class TestLoadChar {

	Characters singleChar;
	static ArrayList<Characters> allChars;
	LoadCharacter LoadCharacterObj;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		allChars = new LoadCharacter().readCharacter();
				
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void test_notExists() {
		
	assertNull(singleChar);	
		
	}
	
	@Test
	public void test_exists() {
	
	singleChar = allChars.get(0);	
	assertNotNull(singleChar);	
		
	}

}
