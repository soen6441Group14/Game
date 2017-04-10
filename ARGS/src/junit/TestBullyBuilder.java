package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import build.BullyBuilder;
import build.Director;
import build.Scores;

public class TestBullyBuilder {
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
		BullyBuilder bully = new BullyBuilder();
		director = new Director();
		
		director.setBuilder(bully);
		director.constructScores(arrayList);
		scores = director.getScores();
		if(scores.getStrength() == 6)
			assertTrue(true);
		else 
			assertTrue(false);
	}

}
