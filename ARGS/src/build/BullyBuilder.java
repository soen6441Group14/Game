package build;

import java.util.ArrayList;
import java.util.Collections;

public class BullyBuilder extends FighterBulider{

	@Override
	void sort(ArrayList<Integer> arrayList) {
		Collections.sort(arrayList);
		scores.setStrength(arrayList.get(5));
		scores.setConstitution(arrayList.get(4));
		scores.setDexterity(arrayList.get(3));
		scores.setIntelligence(arrayList.get(2));
		scores.setCharisma(arrayList.get(1));
		scores.setWisdom(arrayList.get(0));
		
		
	}
	

}
