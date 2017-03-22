package play;

import enumclass.TileType;
import objects.Cells;
import objects.Characters;
import objects.Items;
import objects.Matrix;
import java.util.ArrayList;

/**
 * The class is used to adapt the NPC and items on the map, based on the level of the player
 * @author Tann chen
 */
public class Adaptor {

    private Characters basedPlayer;
    private Cells[][] adaptTarget;

    /**
     * constructor
     */
    public Adaptor(Cells[][] targetedMatrix,Characters player){
        this.adaptTarget=targetedMatrix;
        this.basedPlayer=player;
    }

    /**
     * The method is used to adapt the NPC and items on the map, only method used outside the class
     */
    public void adapting(){
        adaptNPC();
        adaptAllItemOnMap();
    }
    /**
     * The method is used to adapt the NPCs'level equal to the player
     */
    public void adaptNPC(){
        Cells[][] targetMap = adaptTarget;
        int rowNum=targetMap[0][0].getX();
        int columnNum=targetMap[0][0].getY();
        for(int r=0; r<rowNum;r++){
            for(int c=0;c<columnNum;c++){
                if(targetMap[c][r].getTileType()== TileType.MONSTER){
                    targetMap[c][r].getCharacters().setLevel(basedPlayer.getLevel());
                }
            }
        }
    }

    /**
     * The method is used to search all items on the map
     */
    public void adaptAllItemOnMap(){
        Cells[][] targetMap = adaptTarget;
        int rowNum=targetMap[0][0].getX();
        int columnNum=targetMap[0][0].getY();
        for(int r=0; r<rowNum;r++) {
            for (int c = 0; c < columnNum; c++) {
                //adapt items in the chest
                if(targetMap[r][c].getTileType()==TileType.CHEST)
                   adaptItems(targetMap[r][c].getItems());
                //items on the characters
                if(targetMap[r][c].getTileType()==TileType.MONSTER){
                    ArrayList<Items> tempbp=targetMap[r][c].getCharacters().getBackpack();
                    for(Items item:tempbp)
                        adaptItems(item);
                    ArrayList<Items> tempinventory=targetMap[r][c].getCharacters().getInventory();
                    for(Items item:tempinventory)
                        adaptItems(item);
                }

            }
        }
    }

    /**
     * The method is used to adapt the items on the map, based on the level of player
     */
    private void adaptItems(Items targetItems){

        if(basedPlayer.getLevel()<=0)
            System.out.println("System error:player's level <=0 ");
        else if(basedPlayer.getLevel()<=4){
            targetItems.setValue(1);
        }
        else if(basedPlayer.getLevel()<=8){
            targetItems.setValue(2);
        }
        else if(basedPlayer.getLevel()<=12){
            targetItems.setValue(3);
        }
        else if(basedPlayer.getLevel()<=16){
            targetItems.setValue(4);
        }
        else{
            targetItems.setValue(5);
        }
    }

}
