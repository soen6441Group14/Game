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


    public Aggressive(Map map,Characters theCharacter){
        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();

        this.theAggressive=theCharacter;
        locateTheAggressive();
    }

    public void locateTheAggressive(){
        for(int row=0;row<numRows;row++){
            for(int col=0;col<numCols;col++){
                if(map[row][col].getTileType()==TileType.MONSTER){
                    if(theAggressive==map[row][col].getCharacters()){
                        characterRow=row;
                        characterColumn=col;
                        break;
                    }
                }
            }
        }
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


        while (steps > 0) {


            while (desRow>characterRow && steps>0 ){
                System.out.println("一次循环");
                boolean flag=moveOneStep(1,0);
                System.out.println(""+flag);
                if(flag)
                   steps--;
                else
                    break;
            }
            System.out.println(""+steps);
            while (desColumn>characterRow && steps>0){
                System.out.println("二次循环");
                boolean flag=moveOneStep(0,1);
                if(!flag)
                    break;
                else
                    steps--;
            }
            System.out.println(""+steps);
            while (desRow<characterRow && steps>0){
                System.out.println("三次循环");
                boolean flag=moveOneStep(-1,0);
                if(!flag)
                    break;
                else
                    steps--;
            }
            System.out.println(""+steps);
            while (desColumn<characterColumn && steps>0){
                System.out.println("四次循环");
                boolean flag=moveOneStep(0,-1);
                if(flag)
                    steps--;
                else
                   break;
            }
            System.out.println(""+steps);
        }
    }


    public boolean moveOneStep(int down, int right){
        boolean flag=false;

        if(map[characterRow +down][characterColumn +right].getTileType() == TileType.GROUND){
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.MONSTER, numRows, numCols,theAggressive);
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
        }
        else if(map[characterRow+down][characterColumn+right].getTileType() == TileType.CHEST){
            Items item = map[characterRow +down][characterColumn +right].getItems();
            theAggressive.lootItem(item);
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.MONSTER, numRows, numCols, theAggressive);
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
        }
        else if(map[characterRow +down][characterColumn +right].getTileType() == TileType.MONSTER ||
                map[characterRow +down][characterColumn +right].getTileType() == TileType.HERO){
            Characters target=map[characterRow+down][characterColumn+right].getCharacters();
            //attack the target
            boolean ifLive=theAggressive.attack(target);
            if(!ifLive){
                map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                map[characterRow+down][characterColumn+right]=new Cells(TileType.MONSTER, numRows, numCols,theAggressive);
            }
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
        }
        mapFrame.setMap(map,numRows,numCols);
        mapFrame.drawMap(2);
        return flag;
    }



    @Override
    public void execute() {
        if(theAggressive.hitpoints<=0)
            return;

        searchForHero();
        System.out.println(heroRow+"/"+heroColumn);

        walkTowardDes(heroRow,heroColumn);

    }
}
