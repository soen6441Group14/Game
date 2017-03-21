package observer;

import frame.Map;
/**
 * character observer
 * @author grey
 *@version 2.0
 */
public class CharacterObserver {
	CharacterModel characterModel;
	CharacterView characterView;
	/**
	 * add the view to the model
	 * @param map  the main map object
	 */
	public CharacterObserver(Map map) {
		characterModel = new CharacterModel();
		characterView = new CharacterView(map);
		characterModel.addObserver(characterView);
	}
	/**
	 * start to update
	 */
	public void start(){
		characterModel.update();
	}

}
