package observer;

import frame.Map;

public class CharacterObserver {
	CharacterModel characterModel;
	CharacterView characterView;
	
	public CharacterObserver(Map map) {
		characterModel = new CharacterModel();
		characterView = new CharacterView(map);
		characterModel.addObserver(characterView);
	}
	
	public void start(){
		characterModel.update();
	}

}
