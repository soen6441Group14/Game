package play;

import enumclass.Orientation;
import objects.Characters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The class is to build the controller that runs the turns of characters in the game automatically
 * The class uses the singleton pattern to make sure only sole instance during the game
 * @author Tann
 */
public class RunningController implements ActionListener{

    /* Singleton */

    private static RunningController runningController;
    // to run turns automatically;
    private Timer centerTimer;

    private ArrayList<Characters> turnsCharacters;

    /**
     * The constructor
     * singleton pattern - private
     */
    private RunningController(){
        centerTimer=new Timer(2000,this);
        turnsCharacters=new ArrayList<Characters>();
    }

    /**
     * The method is to obtain the instance of solo instance of the class outside the class
     */
    public static RunningController obtainRunningController(){
        if(runningController==null)
            runningController=new RunningController();
        return runningController;
    }

    /* Timer control */

    public void startRun(){
        this.centerTimer.start();
    }

    public void stopRun(){
        this.centerTimer.stop();
    }

    /* the turns control*/

    private int currentRunIndex;
    //to record the user player
    private Characters userPlayer;


    public boolean getTimerStatus(){
        return centerTimer.isRunning();
    }

    public void enrollTurnsCharaters(ArrayList<Characters> turnMoveCharacters){
        this.turnsCharacters=null;
        this.userPlayer=null;
        this.turnsCharacters=turnMoveCharacters;
        findUsersPlayer(turnMoveCharacters);
        currentRunIndex=0;
    }


    /**
     * The method is to find the user player for every map
     * in order to check user play's live every turn
     */
    public void findUsersPlayer(ArrayList<Characters> charactersList){
        for(Characters cha:charactersList){
            if(cha.getOrient()== Orientation.PLAYER){
                userPlayer=cha;
                break;
            }
        }
    }

    /**
     * The method will handle the event from timer
     * The method will automatically be invoked every delay time
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Characters whoTurns = turnsCharacters.get(currentRunIndex);
        whoTurns.turn();
        if(userPlayer.getHitpoints()<=0){
            centerTimer.stop();
            JOptionPane.showMessageDialog(null, "You are dead ! Game Over !", "Prompt", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        currentRunIndex++;
        if(currentRunIndex>turnsCharacters.size()-1)
            currentRunIndex=0;
    }
}
