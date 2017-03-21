package observer;

import java.util.Observable;
/**
 * character model 
 * @author grey
 *	@version 2.0
 */
public class CharacterModel extends Observable{
	/**
	 * update method
	 */
	public void update(){
	// specify that my state was changed  
	setChanged();
	// notify all attached Observers of a change
	notifyObservers(this);
	}
	
}
