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
 * The class describes the strategy of execution for aggressive NPC in the game
 * @author Tann chen
 */
public class Aggressive implements Strategy{

    Map mapFrame;
    public Cells[][] map;
    int numRows,numCols;
    // to locate the aggressive character
    public int currentRow,currentColumn;
    //objective - locate the hero
    public int heroRow,heroColumn;
    public Characters theAggressive;

    public Aggressive(Map map,int characterRow,int characterColumn){
        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.currentRow=characterRow;
        this.currentColumn=characterColumn;
        this.theAggressive=this.map[currentRow][currentColumn].getCharacters();
    }

    public void searchForHero(){
        for(int row=0;row <numRows;row++){
            for(int col=0;col<numCols;col++){
                if(map[row][col].getTileType()==TileType.HERO){
                    heroRow=row;
                    heroColumn=col;
                }
            }
        }
    }

    public void walk(int desRow,int desColumn){
        if((Math.abs(desRow-currentRow)>=Math.abs(desColumn-currentColumn))){
            if(desRow>currentRow) {
                if(moveOneStep(1,0))
            }
            else
                moveOneStep(-1,0);
        }
        else{}
    }

    public boolean moveOneStep(int down, int right){
        boolean flag=false;

        if(map[currentRow+down][currentColumn+right].getTileType() == TileType.GROUND){
            map[currentRow][currentColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[currentRow+down][currentColumn+right] = new Cells(TileType.HERO, numRows, numCols,theAggressive);
            flag=true;
        }
        else if(map[currentRow+down][currentColumn+right].getTileType() == TileType.ENTRY){
            flag=false;
        }
        else if(map[currentRow+down][currentColumn+right].getTileType() == TileType.WALL){
            flag=false;
        }
        else if(map[currentRow+down][currentColumn+right].getTileType() == TileType.CHEST){
            Items item = map[currentRow+down][currentColumn+right].getItems();
            theAggressive.lootItem(item);
            map[currentRow][currentColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[currentRow+down][currentColumn+right] = new Cells(TileType.HERO, numRows, numCols, theAggressive);
            flag=true;
        }
        else if(map[currentRow+down][currentColumn+right].getTileType() == TileType.MONSTER ||
                map[currentRow+down][currentColumn+right].getTileType() == TileType.HERO){
            //TODO:打架
            flag=true;
        }
        return flag;
    }



    @Override
    public void execute() {
        searchForHero();


    }
}
