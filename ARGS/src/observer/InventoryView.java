package observer;

import java.util.Observable;
import java.util.Observer;

import frame.Map;

public class InventoryView implements Observer{
	Map map;
	
	public InventoryView(Map map) {
		this.map = map;
	}
	@Override
	public void update(Observable o, Object arg) {
		
		map.drawInformation();
	}
}
