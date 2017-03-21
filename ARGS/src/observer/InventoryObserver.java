package observer;

import frame.Map;
/**
 * inventory observer
 * @author grey
 * @version 2.0
 */
public class InventoryObserver {
	
	InventoryModel inventoryModel;
	InventoryView inventoryView;
	/**
	 * add the view to the model
	 * @param map the map object in the map frame
	 */
	public InventoryObserver(Map map) {
		
		inventoryModel = new InventoryModel();
		inventoryView = new InventoryView(map);
		inventoryModel.addObserver(inventoryView);
	}
	/**
	 * invoke the model's method to update
	 */
	public void start(){
		inventoryModel.update();
	}

}
