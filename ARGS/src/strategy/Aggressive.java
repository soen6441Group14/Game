package strategy;


import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.*;


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

    /**
     * The constructor of aggressive strategy
     * @param map the mapFrame
     * @param theCharacter the aggressive character
     */
    public Aggressive(Map map,Characters theCharacter){
        this.mapFrame=map;
        this.map = this.mapFrame.getMap();
        this.numRows = this.mapFrame.getNumRows();
        this.numCols = this.mapFrame.getNumCols();
        this.theAggressive=theCharacter;
        locateTheAggressive();
    }

    /**
     * the method is used to locate the aggressive in the map
     */
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
        System.out.println("The aggressive locate: "+characterRow+","+characterColumn);
    }

    /**
     * the method is used to search the hero, because the target of aggressive is hero
     */
    public void searchForHero(){
        for(int row=0;row <numRows;row++){
            for(int col=0;col<numCols;col++){
                if(map[row][col].getTileType()==TileType.HERO){
                    heroRow=row;
                    heroColumn=col;
                    break;
                }
            }
        }
    }


    private int attackTime=1;

    /**
     * The method is used by the aggressive to walk towards the hero
     * the destination of aggressive is hero
     * @param desColumn column of hero
     * @param desRow  row of hero
     */
    public void walkTowardDes(int desRow,int desColumn) {

        int steps = 3;

        //check there is enough step and where char is in different row or column
        while (steps>0 && attackTime>0 && (desRow!=characterRow || desColumn!=characterColumn)) {


            while (desRow>characterRow && steps>0 ){

                boolean flag=moveOneStep(1,0);
                if(flag){
                    steps--;
                }
                else
                    break;
            }


            while (desColumn>characterColumn && steps>0){
                boolean flag=moveOneStep(0,1);
                if(flag){
                    steps--;
                }
                else
                    break;
            }


            while (desRow<characterRow && steps>0){
                boolean flag=moveOneStep(-1,0);
                if(flag){
                    steps--;
                }
                else
                    break;
            }

            while (desColumn<characterColumn && steps>0){
                boolean flag=moveOneStep(0,-1);
                if(flag){
                    steps--;
                }
                else
                   break;
            }

        }

    }

    /**
     * the method is used to move by one step
     * down+right=1, to make sure only one step is operated
     * @param down  walk one step towards down, -1 means walk up
     * @param right walk one step towards right, -1 means walk left
     * @return true , if walking is successful
     */
    public boolean moveOneStep(int down, int right){
        boolean flag=false;

        if(characterRow+down<0||characterRow+down>numRows-1||characterColumn+right<0||characterColumn+right>numCols-1)
            return flag;

        if(map[characterRow+down][characterColumn +right].getTileType() == TileType.GROUND){
            map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
            map[characterRow +down][characterColumn +right] = new Cells(TileType.MONSTER, numRows, numCols,theAggressive);
            flag=true;
            characterRow=characterRow+down;
            characterColumn=characterColumn+right;
        }
        else if(map[characterRow+down][characterColumn+right].getTileType() == TileType.CHEST){
            Items item = map[characterRow+down][characterColumn +right].getItems();
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
            //attackOrLootDead the target
            boolean ifLive=theAggressive.attackOrLootDead(target);
            attackTime--;
            if(target.getOrient()== Orientation.FRIENDLY)
                target.setStrategy(new Aggressive(this.mapFrame,target));
            if(!ifLive){
                map[characterRow][characterColumn] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
                map[characterRow+down][characterColumn+right]=new Cells(TileType.MONSTER, numRows, numCols,theAggressive);
                flag=true;
                characterRow=characterRow+down;
                characterColumn=characterColumn+right;
            }
        }

        if(flag){
            System.out.println("[ "+theAggressive.getName()+" ] move to "+characterRow+","+characterColumn);
            mapFrame.setMap(map,numRows,numCols);
            mapFrame.drawMap(2);
            mapFrame.drawInformation();
        }
        return flag;
    }


    /**
     * the method implements the execute() in strategy interface
     * @see Strategy
     */
    @Override
    public void execute() {
        attackTime=1;
        searchForHero();
        walkTowardDes(heroRow,heroColumn);

    }

}
