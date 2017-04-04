package play;

import java.util.ArrayList;
import objects.Characters;

public class Iteration {

	public boolean playSwitch;
	public	ArrayList<Characters> characterTurn;

	/**
	 * Constructor
	 */
	public Iteration(ArrayList<Characters> characterTurn){
		this.characterTurn = characterTurn;
	}

	/**
	 * The method defines a round to play,in which every character in the map takes its turn
	 * The reson why set playSwitch inside is to control the situation when hero exit from the map when a roundis in middle
	 */
	public void round(){
		for(int index=0;index<characterTurn.size();index++){
			if(!playSwitch)
				break;
			characterTurn.get(index).turn();
		}
	}
	/**
	 * The method is to start the round to play
	 */
	public void switchOn(){
		playSwitch=true;
		while(playSwitch){
			round();
		}
	}

	public void switchOff(){
		this.playSwitch=false;
	}

	/**
	 * The mehtod is to update the characterTurn when the map is changed
	 */
	public void updateCharacterTurnList(ArrayList<Characters> charactersArrayList){
		this.characterTurn=charactersArrayList;
	}

}
