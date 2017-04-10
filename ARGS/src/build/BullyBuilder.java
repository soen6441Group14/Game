package build;

import java.util.ArrayList;
import java.util.Collections;

/**
 * the concrete builder of fighter builder
 * @author grey
 * @version 2.0
 */
public class BullyBuilder extends FighterBulider{
	/**
	 * This method overrides the FighterBuilder sort method
	 * by ordering the stats towards bully character using
	 * D20 rules
	 * @param arrayList is the stat rolls
	 */
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
