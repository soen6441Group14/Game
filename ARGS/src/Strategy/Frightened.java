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


    public Frightened(Map map,Characters theCharacter,Characters afraid){
        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.theFrightened=theCharacter;
        locateTheCharacter();
        this.afraidObject=afraid;
    }

    public void locateTheCharacter(){
        for(int row=0;row<numRows;row++){
            for(int col=0;col<numCols;col++){
                if(map[row][col].getTileType()== TileType.MONSTER||map[row][col].getTileType()==TileType.HERO){
                    if(map[row][col].getCharacters()==theFrightened){
                        this.characterRow=row;
                        this.characterColumn=col;
                        break;
                    }
                }
            }
        }
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
        if(characterRow+down<0||characterRow+down>numRows-1||characterColumn+right<0||characterColumn+right>numCols-1)
            return flag;

        if(map[characterRow +down][characterColumn +right].getTileType() == TileType.GROUND){
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.MONSTER, numRows, numCols,theFrightened);
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
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
            map[characterRow +down][characterColumn +right] = new Cells(TileType.MONSTER, numRows, numCols, theFrightened);
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.MONSTER ||
                map[characterRow +down][characterColumn +right].getTileType() == TileType.HERO){
            Characters target=map[characterRow+down][characterColumn+right].getCharacters();

            //attack the target
            if(!theFrightened.attack(target)){
                map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                map[characterRow+down][characterColumn+right]=new Cells(TileType.MONSTER, numRows, numCols,theFrightened);
            }
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
        }
        mapFrame.setMap(map,numRows,numCols);
        mapFrame.drawMap(2);
        return flag;
    }

    public void walkTowardDes(int desRow,int desColumn) {
        int steps = 3;

        while (steps>0) {

            while (desRow>characterRow && steps>0 ){

                boolean flag=moveOneStep(1,0);
                if(flag)
                    steps--;
                else
                    break;
            }
            while (desColumn>characterColumn && steps>0){

                boolean flag=moveOneStep(0,1);
                if(!flag)
                    break;
                else
                    steps--;
            }
            while (desRow<characterRow && steps>0){

                boolean flag=moveOneStep(-1,0);
                if(!flag)
                    break;
                else
                    steps--;
            }
            while (desColumn<characterColumn && steps>0){

                boolean flag=moveOneStep(0,-1);
                if(flag)
                    steps--;
                else
                    break;
            }
        }
    }


    @Override
    public void execute() {
        //if dead, not execute
        if(this.theFrightened.hitpoints<=0)
            return;

        locateTheAfraid();
        int desRow=characterRow-(afraidRow-characterRow);
        int desColumn=characterColumn-(afraidColumn-characterColumn);
        //exception
        if(characterRow<=3)
            desRow=numRows-characterRow;
        if(characterColumn<=3)
            desColumn=numCols-characterColumn;

        walkTowardDes(desRow,desColumn);
    }
}
