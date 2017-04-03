package Strategy;

import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Ground;
import objects.Items;

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
    public int hostileRow,hostileCol;
    public int exitRow,exitCol;

    public ComputerPlayer(Map map,Characters theComPlayer){

        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.theComPlayer=theComPlayer;
    }

    public void locateTheComPlayer(){
        for(int row=0; row<numRows;row++){
            for(int col=0; col<numCols;col++){
                if(map[row][col].getTileType()==TileType.MONSTER){
                    Characters cha=map[row][col].getCharacters();
                    if(cha.getOrient()==Orientation.COMPUTER_PLAYER){}
                    playerRow=row;
                    playerColumn=col;
                }
            }
        }
    }


    public boolean searchForHostiles(){
        boolean isHostile=false;

        for(int row=0;row<numRows;row++){
            for(int col=0;col<numCols;col++){
               if(map[row][col].getTileType()== TileType.MONSTER){
                   Characters character=map[row][col].getCharacters();
                   if(character.getOrient()== Orientation.HOSTILE){
                       hostileRow=row;
                       hostileCol=col;
                       isHostile=true;
                       break;
                   }
               }
            }
        }

        return isHostile;
    }

    public void searchForExit(){
        for(int row=0;row<numRows;row++){
            for(int col=0;col<numCols;col++){
                if(map[row][col].getTileType()==TileType.EXIT){
                    exitRow=row;
                    exitCol=col;
                }
            }
        }
    }


    public boolean moveOneStep(int down, int right){
        boolean flag=false;
        if(map[playerRow+down][playerColumn +right].getTileType() == TileType.GROUND){
            map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[playerRow+down][playerColumn +right] = new Cells(TileType.MONSTER, numRows, numCols,theComPlayer);
            flag=true;
            playerRow=playerRow+down;
            playerColumn=playerColumn+right;
        }
        else if(map[playerRow+down][playerColumn +right].getTileType() == TileType.MONSTER ||
                map[playerRow+down][playerColumn +right].getTileType() == TileType.HERO){
            Characters target=map[playerRow+down][playerColumn+right].getCharacters();
            //attack the target
            boolean ifLive=theComPlayer.attack(target);
            if(!ifLive){
                map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                map[playerRow+down][playerColumn+right]=new Cells(TileType.MONSTER, numRows, numCols,theComPlayer);
            }
            flag=true;
            playerRow=playerRow+down;
            playerColumn=playerColumn+right;
        }
        else if (map[playerRow+down][playerColumn+right].getTileType() == TileType.EXIT) {
            if(!searchForHostiles()){
                map[playerRow][playerColumn]=new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                System.out.println("The computer play's objective is achieved!!! he is gone");
            }
        }
        mapFrame.setMap(map,numRows,numCols);
        mapFrame.drawMap(2);
        return flag;
    }
    @Override
    public void execute() {
        if(this.theComPlayer.hitpoints<=0)
            return;

        int desRow;
        int desCol;
        if(searchForHostiles()){
            desRow=hostileRow;
            desCol=hostileCol;
        }
        else{
            searchForExit();
            desRow=exitRow;
            desCol=exitCol;
        }

        int steps=3;
        boolean flag;
        while (steps>0){
            if ((Math.abs(desRow-playerRow)>=Math.abs(desCol - playerColumn))){
                if (desRow >= playerRow)
                    flag=moveOneStep(1,0);
                else
                    flag=moveOneStep(-1, 0);
            } else {
                if (desCol >= playerColumn)
                    flag=moveOneStep(0,1);
                else
                    flag=moveOneStep(0,-1);
            }
            //one step finished
            if(flag)
                steps--;
        }
    }
}
