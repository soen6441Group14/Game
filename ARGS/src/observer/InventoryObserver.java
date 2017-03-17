package observer;

import frame.Map;

public class InventoryObserver {
	
	InventoryModel inventoryModel;
	InventoryView inventoryView;
	
	public InventoryObserver(Map map) {
		
		inventoryModel = new InventoryModel();
		inventoryView = new InventoryView(map);
		inventoryModel.addObserver(inventoryView);
	}
	
	public void start(){
		inventoryModel.update();
	}

}
