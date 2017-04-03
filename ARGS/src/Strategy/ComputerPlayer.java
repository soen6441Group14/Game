package Strategy;

import frame.Map;
import objects.Cells;
import objects.Characters;

/**
 * The class is the implementation of strategy interface
 * The class describes the strategy of execution for player controlled by computer in the game
 * @author Tann chen
 */
public class ComputerPlayer implements Strategy{

    public Map mapFrame;
    public Cells[][] map;
    public int numRows,numCols;
    // to locate the friendly character
    public int playerRow, playerColumn;
    public Characters theComPlayer;

    public ComputerPlayer(Map map,int characterRow,int characterColumn){

        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.playerRow =characterRow;
        this.playerColumn =characterColumn;
        this.theComPlayer=this.map[this.playerRow][this.playerColumn].getCharacters();
    }


    @Override
    public void execute() {

    }
}
