package Strategy;

import objects.Characters;

/**
 * *The class is the implementation of strategy interface
 * The class describes the strategy of execution for the character who are frozen in the game
 * @author Tann chen

 */
public class Frozen implements Strategy {

    public Characters frozenedCharacter;

    public Frozen(Characters theCharacter){
        this.frozenedCharacter=theCharacter;
    }
    
    /** 
     * This method executes the frozen status on the player
     */
    @Override
    public void execute() {
        System.out.println("[ " + frozenedCharacter.getName() + " ] frozen status -  can not move !");
    }
}
