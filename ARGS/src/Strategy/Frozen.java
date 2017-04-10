package Strategy;

import objects.Characters;

/**
 * *The class is the implementation of strategy interface
 * The class describes the strategy of execution for the character who are frozen in the game
 * @author Tann chen

 */
public class Frozen implements Strategy {

    public Characters frozenedCharacter;

    /**
     * Constructor
     * @param theCharacter the frozen character
     */
    public Frozen(Characters theCharacter){
        this.frozenedCharacter=theCharacter;
    }
    
    /** 
     * This method implements the execute() in Strategy interface
     */
    @Override
    public void execute() {
        System.out.println("[ " + frozenedCharacter.getName() + " ] frozen status -  can not move !");
    }
}
