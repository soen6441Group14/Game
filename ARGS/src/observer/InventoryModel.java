package observer;

import java.util.Observable;

public class InventoryModel extends Observable{
	
	public void update(){
		// specify that my state was changed  
		setChanged();
		// notify all attached Observers of a change
		notifyObservers(this);
		}

}
