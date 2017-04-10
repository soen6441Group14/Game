package junit;


import actionListener.PanelListener;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import junit.framework.Assert;
import objects.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestPanelListener {

    PanelListener panelListener;
    Map testMap;
    Cells[][] testCells;
    Characters player;
    int xOriginal,yOriginal;

    @Before
    public void before(){

        player=new Characters("test",1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,Orientation.PLAYER,
                1,1,1,null,null);

        //build test cells[][] in which all grid are ground
        testCells=new Cells[5][5];
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                testCells[r][c]=new Cells(TileType.GROUND,5,5,new Ground(TileType.GROUND));
            }
        }
        //set the movingTarget in the center
        testCells[3][3]=new Cells(TileType.HERO,5,5,player);

        testMap=new Map("testMap",5,5);
        testMap.setMap(testCells,5,5);

        panelListener=new PanelListener(testMap,1);
        int[] position=panelListener.getHeroLocation();
        xOriginal = position[0];
        yOriginal = position[1];
    }

    /* character movement*/

    @Test
    public void testMoveUp(){
        panelListener.moveUp(xOriginal,yOriginal,player);
        int[] newPosition=panelListener.getHeroLocation();
        assertEquals(xOriginal-1,newPosition[0]);
        assertEquals(yOriginal,newPosition[1]);
    }
    @Test
    public void testMoveDown(){
        panelListener.moveDown(xOriginal,yOriginal,player);
        int[] newPosition =panelListener.getHeroLocation();
        assertEquals(xOriginal+1,newPosition[0]);
        assertEquals(yOriginal,newPosition[1]);
    }
    @Test
    public void testMoveRight(){
        panelListener.moveRight(xOriginal,yOriginal,player);
        int[] newPosition =panelListener.getHeroLocation();
        assertEquals(xOriginal,newPosition[0]);
        assertEquals(yOriginal+1,newPosition[1]);

    }
    @Test
    public void testMoveLeft(){
        panelListener.moveLeft(xOriginal,yOriginal,player);
        int[] newPosition =panelListener.getHeroLocation();
        assertEquals(xOriginal,newPosition[0]);
        assertEquals(yOriginal-1,newPosition[1]);
    }

   
    @Test
    public void testExitCondition_noMonsters() {
    	
    	assertTrue(panelListener.checkCompleteObjective());
    	
    }

    @Test
    public void testExitCondition_addedMonsters() {
    	
    	Characters mons = new Characters("test",1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,Orientation.HOSTILE,1,1,1,null,null);
    	testCells[1][4]=new Cells(TileType.MONSTER,5,5,mons);
    	
    	assertFalse(panelListener.checkCompleteObjective());
    	
    }
    
    
    @Test
    public void testLootItem(){
        Items item1=new Items("Weapon1",1,"");
        Items item2=new Items("Ring1",1,"");
        ArrayList<Items>testbackpack=new ArrayList<Items>();
        for(int i=0;i<10;i++){
            Items empty=new Items("EMPTY",0,"");
            testbackpack.add(empty);
        }
        player.setBackpack(testbackpack);
        //loot item
             // panelListener.lootItem(item1,player);
             //panelListener.lootItem(item2,player);
        
        //begin testing..
        player.lootItem(item1);
        player.lootItem(item2);
        //...end testing
        
        int i=0;
        for(Items items: player.backpack){
            if(!items.getName().equals("EMPTY"))
                i++;
        }
        assertEquals(2,i);

    }

    @Test
    public void testInteractWithChest(){
        Items item1=new Items("Armor1",1,"");
        ArrayList<Items>testbackpack=new ArrayList<Items>();
        for(int i=0;i<10;i++){
            Items empty=new Items("EMPTY",0,"");
            testbackpack.add(empty);
        }
        player.setBackpack(testbackpack);
        testCells[2][3]=new Cells(TileType.CHEST,5,5,item1);
        panelListener.moveUp(xOriginal,yOriginal,player);
        int i=0;
        for(Items items: player.backpack){
            if(items.getName().equals("Armor1"))
                i++;
        }
        assertEquals(1,i);
    }

    @Test
    public void testInteractWithChest2(){

        Items item2=new Items("Helmet1",1,"");
        ArrayList<Items>testbackpack=new ArrayList<Items>();
        for(int i=0;i<10;i++){
            Items empty=new Items("EMPTY",0,"");
            testbackpack.add(empty);
        }
        player.setBackpack(testbackpack);
        testCells[3][4]=new Cells(TileType.CHEST,5,5,item2);
        panelListener.moveRight(xOriginal,yOriginal,player);
        int i=0;
        for(Items items: player.backpack){
            if(items.getName().equals("Helmet1"))
                i++;
        }
        assertEquals(1,i);
    }

    @Test
    public void testExitFromMap(){
        Cells[][] testCells2=new Cells[5][5];
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                testCells2[r][c]=new Cells(TileType.GROUND,5,5,new Ground(TileType.GROUND));
            }
        }

        Matrix matrix1=new Matrix(testCells,"matrix1");
        Matrix matrix2=new Matrix(testCells2,"matrix2");
        Matrix matrix3=new Matrix(testCells,"matrix3");
        
        ArrayList<Matrix>testMatricArray=new ArrayList<Matrix>();
        testMatricArray.add(matrix1);
        testMatricArray.add(matrix2);
        testMatricArray.add(matrix3);
        
        Campaigns testCampaign=new Campaigns(testMatricArray,"campaign1");
        
        testMap.playingCampaign=testCampaign;
        
        testMap.playingHero=player;
        
        player.setLevel(1);
        
        testMap.initCampaign();
        
        panelListener=new PanelListener(testMap,3);
        panelListener.exitFromMap(player);

        Assert.assertEquals(2,player.getLevel());
        Assert.assertEquals(testCells2,panelListener.map);
        

    }
}
