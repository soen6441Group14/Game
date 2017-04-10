package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import load.LoadCampaign;
import objects.Campaigns;
import objects.Cells;
import objects.Matrix;

public class TestLoadCampaign {
	static ArrayList<Campaigns> allMaps = new ArrayList<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Cells[][] map = new Cells[10][10];
		Matrix matrix = new Matrix(map, "MAP1");
		
		ArrayList<Matrix> maps = new ArrayList<>();
		maps.add(matrix);
		
		Campaigns campaigns = new Campaigns(maps, "CAMPAIGN1");
		allMaps.add(campaigns);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLoadCampaign() {
		String name = "CAMPAIGN1";
		Campaigns newCam = new LoadCampaign().loadCampaign(allMaps, name);
		if(newCam.getCampaign().get(0).getName().equals("MAP1"))
			assertTrue(true);
		else 
			assertTrue(false);
	}

}
