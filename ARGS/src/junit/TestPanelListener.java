package junit;


import actionListener.PanelListener;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import junit.framework.Assert;
import objects.Cells;
import objects.Characters;
import objects.Ground;

import static org.junit.Assert.*;

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
    public void testExitCondition() {
    	
    	assertTrue(panelListener.checkCompleteObjective());
    	
    }

}
