package observer;

import java.util.Observable;
import java.util.Observer;

import frame.Map;

public class CharacterView implements Observer{
	Map map;
	
	public CharacterView(Map map) {
		this.map = map;
	}
	@Override
	public void update(Observable o, Object arg) {
		
		map.drawInformation();
	}

}
