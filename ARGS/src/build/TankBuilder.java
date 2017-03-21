package build;

import java.util.ArrayList;
import java.util.Collections;
/**
 * the concrete builder of fighter builder
 * @author grey
 * @version 2.0
 */
public class TankBuilder extends FighterBulider{
	
	@Override
	void sort(ArrayList<Integer> arrayList) {
		
		Collections.sort(arrayList);
		scores.setConstitution(arrayList.get(5));
		scores.setDexterity(arrayList.get(4));
		scores.setStrength(arrayList.get(3));
		scores.setIntelligence(arrayList.get(2));
		scores.setCharisma(arrayList.get(1));
		scores.setWisdom(arrayList.get(0));
		
	}

}
