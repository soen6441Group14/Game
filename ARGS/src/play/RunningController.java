package play;

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
        centerTimer=new Timer(1500,this);
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

    public void enrollTurnsCharaters(ArrayList<Characters> turnMoveCharacters){
        this.turnsCharacters=null;
        this.turnsCharacters=turnMoveCharacters;
        currentRunIndex=0;
    }

    /**
     * The method will handle the event from timer
     * The method will automatically be invoked every delay time
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Characters whoTurns = turnsCharacters.get(currentRunIndex);
        whoTurns.turn();
        currentRunIndex++;
        if(currentRunIndex>turnsCharacters.size()-1)
            currentRunIndex=0;
    }
}
