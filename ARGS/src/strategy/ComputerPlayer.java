package strategy;

import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.*;

import javax.swing.*;

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
    public int rowOfEntry;
    public int columnOfEntry;
    //the data member to control campaign
    public int playingIndex;
    public int numberMap;



    /**
     * The constructor
     * @param map the mapFrame
     * @param numberMap map index
     */

    public ComputerPlayer(Map map,int numberMap){
        this.mapFrame=map;
        this.numberMap=numberMap;
        setListeningMatrix();
    }

    /**
     * The method is used to set the cell[][] information, based on the listed map
     */
    public void setListeningMatrix(){
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();
        this.playingIndex = this.mapFrame.getPlayingIndex();
        getEntry(); //TODO:必须在showonMap 前否则无效
    }

    /**
     * the method is used to locate the computer player--hero in the map
     */
    public void locateTheComPlayer(){
        for(int row=0; row<numRows;row++){
            for(int col=0; col<numCols;col++){
                if(map[row][col].getTileType()==TileType.HERO){
                    theComPlayer=map[row][col].getCharacters();
                    playerRow=row;
                    playerColumn=col;
                    break;
                }
            }
        }
    }

    /**
     * The method is used to get the entry of the playing map
     */
    public void getEntry() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (map[i][j].getTileType() == TileType.ENTRY) {
                    rowOfEntry = i;
                    columnOfEntry = j;
                    break;
                }
            }
        }
    }



    /**
     * The method is to recover the original entry after character standing on the entry
     * @param currentRow    row of hero
     * @param currentColumn column of hero
     */
    public void recoverTheEntry(int currentRow, int currentColumn){
        if(currentRow==rowOfEntry && currentColumn==columnOfEntry)
            map[rowOfEntry][columnOfEntry]=new Cells(TileType.ENTRY, numRows, numCols, new Entry(TileType.ENTRY));
    }


    /**
     * The method is used to search the hostile in the map
     * because the target of computer hero is to kill them
     * @return whether there is hostile monster
     */
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


    /**
     * The method is used to search the exit door fn the map
     */
    public void searchForExit(){
        for(int row=0;row<numRows;row++){
            for(int col=0;col<numCols;col++){
                if(map[row][col].getTileType()==TileType.EXIT){
                    exitRow=row;
                    exitCol=col;
                    break;
                }
            }
        }
    }

    /**
     * The method is to exit from the exit of the map
     * If the objective"kill all hostile monsters" is not completed, no map change, prompt information
     * @param hero the character object of hero
     */
    public void exitFromMap(Characters hero){
            int level = hero.getLevel();
            hero.setLevel(level+1);
            System.out.println("map index"+playingIndex);
            if (playingIndex >= numberMap) {
                JOptionPane.showMessageDialog(null, "you successfully pass the campaign", "Prompt", JOptionPane.INFORMATION_MESSAGE);
                mapFrame.panel.setVisible(false);
            }else {
                mapFrame.changeMap();
                setListeningMatrix();
                mapFrame.showOnMap();
            }
    }


    /**
     * The method is used by computer player to move one step in the map
     * @param down move down, -1 if move up
     * @param right move right, -1 if move left
     * @return whether it is successful or not
     */
    public boolean moveOneStep(int down, int right){

        locateTheComPlayer();

        boolean flag=false;

        if(playerRow+down<0||playerRow+down>numRows-1||playerColumn+right<0||playerColumn+right>numCols-1)
            return flag;

        if(map[playerRow+down][playerColumn+right].getTileType() == TileType.GROUND){
            map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[playerRow+down][playerColumn+right] = new Cells(TileType.HERO, numRows, numCols,theComPlayer);
            flag=true;
        }
        else if(map[playerRow+down][playerColumn+right].getTileType() == TileType.CHEST){
            Items item = map[playerRow+down][playerColumn+right].getItems();
            //lootItem(item,hero);
            theComPlayer.lootItem(item);
            map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[playerRow+down][playerColumn+right] = new Cells(TileType.HERO, numRows, numCols,theComPlayer);
        }
        else if(map[playerRow+down][playerColumn+right].getTileType() == TileType.MONSTER){
            Characters target=map[playerRow+down][playerColumn+right].getCharacters();
            if(target.getOrient()==Orientation.HOSTILE){
                //attackOrLootDead the target
                boolean ifLive=theComPlayer.attackOrLootDead(target);
                if(!ifLive){
                    map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                    map[playerRow+down][playerColumn+right]=new Cells(TileType.HERO, numRows, numCols,theComPlayer);
                    mapFrame.updateCharacterList();
                    flag=true;
                }
                else{
                    attackTime--;
                }
            }
        }
        else if (map[playerRow+down][playerColumn+right].getTileType() == TileType.EXIT) {
            if(!searchForHostiles()){
                map[playerRow][playerColumn]=new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                JOptionPane.showMessageDialog(null, "objective is achieved,you are going to next map", "Prompt", JOptionPane.INFORMATION_MESSAGE);
                exitFromMap(theComPlayer);
                steps=0;
            }
        }


        if(flag){
            recoverTheEntry(playerRow,playerColumn);
            System.out.println("[ "+theComPlayer.getName()+" ] move to "+playerRow+","+playerColumn);
            mapFrame.setMap(map,numRows,numCols);
            mapFrame.drawMap(2);
        }
        return flag;
    }


    /**
     * The method is used to move towards destination
     * for the computer player, the destination is hotsile and exit door
     * @param desRow  row
     * @param desColumn  column
     */
    public void walkTowardDes(int desRow,int desColumn){

        //check there is enough step and where char is in different row or column
        while (steps>0 && attackTime>0 && (desRow!=playerRow || desColumn!=playerColumn)){


            while (desRow>playerRow && steps>0 ){

                boolean flag=moveOneStep(1,0);
                if(flag){
                    steps--;
                }
                else
                    break;
            }

            while (desColumn>playerColumn && steps>0){
                boolean flag=moveOneStep(0,1);
                if(flag){
                    steps--;
                }
                else
                    break;
            }

            while (desRow<playerRow && steps>0){
                boolean flag=moveOneStep(-1,0);
                if(flag){
                    steps--;
                }
                else
                    break;
            }

            while (desColumn<playerColumn && steps>0){
                boolean flag=moveOneStep(0,-1);
                if(flag){
                    steps--;
                }
                else
                    break;
            }

        }

    }


    private int steps = 3;
    private int attackTime=1;

    /**
     * The method implements the execute() in strategy interface
     */
    @Override
    public void execute(){

        attackTime=1;
        steps=3;

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
        System.out.println("destination:"+desRow+","+desCol);

        walkTowardDes(desRow,desCol);
    }
}
