package Strategy;

/**
 * The class is the implementation of strategy interface
 * The class describes the strategy of execution for player controlled by users in the game
 * @author Tann chen
 */
public class Humanplayer implements Strategy{
    @Override
    public void execute() {

        System.out.println("[human player turn] you can operate the hero");
    }
    
  
}
