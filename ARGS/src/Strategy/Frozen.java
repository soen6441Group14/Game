package Strategy;

/**
 * *The class is the implementation of strategy interface
 * The class describes the strategy of execution for the character who are frozen in the game
 * @author Tann chen

 */
public class Frozen implements Strategy {
    @Override
    public void execute() {
        System.out.println("The character is frozen, it can not move !!!");
    }
}
