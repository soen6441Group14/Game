package Strategy;

import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Ground;
import objects.Items;

/**
 * The class is the implementation of strategy interface
 * The class describes the strategy of execution for the character who are frightened in the game
 * @author Tann chen
 */
public class Frightened implements Strategy {

    public Map mapFrame;
    public Cells[][] map;
    public int numRows,numCols;

    public Characters theFrightened;
    // to locate the frightened character
    public int characterRow, characterColumn;
    //objective - locate the character who hit the frightened character

    public Characters afraidObject;
    public int afraidRow,afraidColumn;


    public Frightened(Map map, int characterRow, int characterColumn,Characters afraid){
        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.characterRow =characterRow;
        this.characterColumn =characterColumn;
        this.theFrightened=this.map[this.characterRow][this.characterColumn].getCharacters();
        this.afraidObject=afraid;
    }

    public void locateTheAfraid(){
        for(int row=0; row<numRows;row++){
            for(int col=0; col<numCols;col++){
                if(map[row][col].getTileType()== TileType.MONSTER||map[row][col].getTileType()==TileType.HERO){
                    Characters suspect=map[row][col].getCharacters();
                    if(suspect==afraidObject){
                        afraidRow=row;
                        afraidColumn=col;
                        break;
                    }
                }
            }
        }
    }

    public boolean moveOneStep(int down, int right){
        boolean flag=false;

        if(map[characterRow +down][characterColumn +right].getTileType() == TileType.GROUND){
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.HERO, numRows, numCols,theFrightened);
            flag=true;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.ENTRY){
            flag=false;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.WALL){
            flag=false;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.CHEST){
            Items item = map[characterRow +down][characterColumn +right].getItems();
            theFrightened.lootItem(item);
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.HERO, numRows, numCols, theFrightened);
            flag=true;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.MONSTER ||
                map[characterRow +down][characterColumn +right].getTileType() == TileType.HERO){
            //TODO:打架
            flag=true;
        }
        mapFrame.setMap(map,numRows,numCols);
        mapFrame.drawMap(2);
        return flag;
    }


    @Override
    public void execute() {
        locateTheAfraid();
        int desRow= characterRow-(afraidRow-characterRow);
        int desColumn= characterColumn-(afraidColumn-characterColumn);
        //exception
        if(characterRow <=5)
            desRow=numRows-characterRow;
        if(characterColumn <=5)
            desColumn=numCols-characterColumn;

        int steps = 3;
        boolean flag;

        while (steps > 0) {
            if ((Math.abs(desRow - characterRow) >= Math.abs(desColumn - characterColumn))){
                if (desRow >= characterRow)
                    flag=moveOneStep(1,0);
                else
                    flag=moveOneStep(-1, 0);
            } else {
                if (desColumn >= characterColumn)
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
