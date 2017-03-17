package build;

import java.util.ArrayList;

/**
 * Abstract Builder class of the Builder pattern
 * 
 */
public abstract class FighterBulider {
	/**
	 * Product to be constructed by the builder
	 */
	protected Scores scores;
	/**
	 * Get the constructed Scores from the Builder
	 */
	public Scores getScores(){
		return scores;
	}
	/**
	 * Create a new unspecified Scores that 
	 * will be eventually build by calling the 
	 * following abstract methods in a concrete 
	 * class derived from the Scores class
	 */
	public void createNewScores(){
		scores = new Scores();
	}
	
	abstract void sort(ArrayList<String> arrayList);

}
