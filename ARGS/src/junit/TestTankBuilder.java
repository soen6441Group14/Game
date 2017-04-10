package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import build.Director;
import build.Scores;
import build.TankBuilder;

public class TestTankBuilder {
	static ArrayList<Integer> arrayList = new ArrayList<>();
	Director director;
	Scores scores;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(6);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		TankBuilder tank = new TankBuilder();
		director = new Director();
		
		director.setBuilder(tank);
		director.constructScores(arrayList);
		scores = director.getScores();
		if(scores.getConstitution() == 6)
			assertTrue(true);
		else 
			assertTrue(false);
	}

}
