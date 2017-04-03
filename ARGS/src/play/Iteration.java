package play;

import java.util.ArrayList;

import objects.Characters;

public class Iteration {
	public boolean play= true;
	public	ArrayList<Characters> characterTurn;
	
	public Iteration(ArrayList<Characters> characterTurn){
		this.characterTurn = characterTurn;
	}
	
	public void play(){
		
		while(true){
			if(play == true)
			for(Characters characters: characterTurn){
				characters.turn();
			}
			else
				break;
		}
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}
	

}
