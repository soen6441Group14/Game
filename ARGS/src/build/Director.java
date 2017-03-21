package build;

import java.util.ArrayList;

/**
 * Director of the Builder pattern
 * @author grey
 * @version 2.0
 */

public class Director {
	/**
	 * The Director is to use a specific "build plan": the FighterBuilder
	 */
	private FighterBulider fighterBulider;
	
	public void setBuilder(FighterBulider newFighterBuilder){
		this.fighterBulider = newFighterBuilder;
	}
	/**
	 * The Director assumes that all Scores have the same parts
	 * and each part is built by calling the same method
	 * though what these specific methods do may be different
	 */
	public void constructScores(ArrayList<Integer> arrayList){
		fighterBulider.createNewScores();
		fighterBulider.sort(arrayList);
		
	}
	 /**
     * @return gets the Scores after it has been built
     */
	public Scores getScores(){
		return fighterBulider.getScores();
	}
	

}
