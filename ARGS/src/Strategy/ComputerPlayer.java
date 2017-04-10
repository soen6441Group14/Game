package Strategy;

import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.*;

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
        locateTheComPlayer();
        getEntry();
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
                }
            }
        }
    }


    
    public boolean moveOneStep(int down, int right){
        boolean flag=false;

        if(playerRow+down<0||playerRow+down>numRows-1||playerColumn+right<0||playerColumn+right>numCols-1)
            return flag;

        if(map[playerRow+down][playerColumn+right].getTileType() == TileType.GROUND){
            map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[playerRow+down][playerColumn +right] = new Cells(TileType.HERO, numRows, numCols,theComPlayer);
            flag=true;
            playerRow=playerRow+down;
            playerColumn=playerColumn+right;
        }
        else if(map[playerRow+down][playerColumn+right].getTileType() == TileType.CHEST){
            Items item = map[playerRow+down][playerColumn+right].getItems();
            //lootItem(item,hero);
            theComPlayer.lootItem(item);
            map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[playerRow+down][playerColumn+right] = new Cells(TileType.HERO, numRows, numCols,theComPlayer);
        }
        else if(map[playerRow+down][playerColumn +right].getTileType() == TileType.MONSTER){
            Characters target=map[playerRow+down][playerColumn+right].getCharacters();
            if(target.getOrient()==Orientation.HOSTILE){
                //attackOrLootDead the target
                boolean ifLive=theComPlayer.attackOrLootDead(target);
                attackTime--;
                if(!ifLive){
                    map[playerRow][playerColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                    map[playerRow+down][playerColumn+right]=new Cells(TileType.MONSTER, numRows, numCols,theComPlayer);
                    mapFrame.updateCharacterList();
                }
                flag=true;
                playerRow=playerRow+down;
                playerColumn=playerColumn+right;
            }
        }
        else if (map[playerRow+down][playerColumn+right].getTileType() == TileType.EXIT) {
            if(!searchForHostiles()){
                map[playerRow][playerColumn]=new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                System.out.println("[" +theComPlayer.getName()+" ] objective is achieved -> he is going to next map");
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



    public void walkTowardDes(int desRow,int desColumn) {

        //check there is enough step and where char is in different row or column
        while (steps>0 && attackTime>0 && (desRow!=playerRow || desColumn!=playerColumn)) {


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

    @Override
    public void execute() {

        attackTime=1;
        steps=3;

        int desRow;
        int desCol;
        if(searchForHostiles()){
            desRow=hostileRow;
            desCol=hostileCol;
            System.out.println("destination**row"+desRow);
            System.out.println("destination**col"+desCol);
        }
        else{
            searchForExit();
            desRow=exitRow;
            desCol=exitCol;
        }

        walkTowardDes(desRow,desCol);
    }
}
