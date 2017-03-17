package build;

import java.util.ArrayList;
import java.util.Collections;

public class TankBuilder extends FighterBulider{
	
	@Override
	void sort(ArrayList<String> arrayList) {
		
		Collections.sort(arrayList);
		scores.setConstitution(Integer.parseInt(arrayList.get(5)));
		scores.setDexterity(Integer.parseInt(arrayList.get(4)));
		scores.setStrength(Integer.parseInt(arrayList.get(3)));
		scores.setIntelligence(Integer.parseInt(arrayList.get(2)));
		scores.setCharisma(Integer.parseInt(arrayList.get(1)));
		scores.setWisdom(Integer.parseInt(arrayList.get(0)));
		
	}

}
