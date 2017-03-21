package observer;

import java.util.Observable;
/**
 * inventory model
 * @author grey
 *@version 2.0
 */
public class InventoryModel extends Observable{
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
