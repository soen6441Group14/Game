package Strategy;

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

    public Map mapFrame;
    public Cells[][] map;
    public int numRows,numCols;

    public Characters theAggressive;
    // to locate the aggressive character
    public int characterRow, characterColumn;
    //objective - locate the hero
    public int heroRow,heroColumn;


    public Aggressive(Map map,int characterRow,int characterColumn){
        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.characterRow =characterRow;
        this.characterColumn =characterColumn;
        this.theAggressive=this.map[this.characterRow][this.characterColumn].getCharacters();
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

    public void walkTowardDes(int desRow,int desColumn) {
        int steps = 3;
        boolean flag;

        while (steps > 0 && characterColumn!=desColumn && characterRow!=desRow) {
            if ((Math.abs(desRow - characterRow) >= Math.abs(desColumn - characterColumn))) {
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

    public boolean moveOneStep(int down, int right){
        boolean flag=false;

        if(map[characterRow +down][characterColumn +right].getTileType() == TileType.GROUND){
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.HERO, numRows, numCols,theAggressive);
            flag=true;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.CHEST){
            Items item = map[characterRow +down][characterColumn +right].getItems();
            theAggressive.lootItem(item);
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.HERO, numRows, numCols, theAggressive);
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
        searchForHero();
        walkTowardDes(heroRow,heroColumn);
    }
}
