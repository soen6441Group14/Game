package junit;


import enumclass.Orientation;
import enumclass.TileType;
import junit.framework.Assert;
import objects.Cells;
import objects.Characters;
import objects.Ground;
import objects.Items;
import org.junit.Before;
import org.junit.Test;
import play.Adaptor;

import java.util.ArrayList;

public class TestAdaptor {

    Cells[][] testCells;
    Characters testPlayer;
    Characters testMonster;

    Items testItem1,testItem2,testItem3;

    @Before
    public void before(){

        //create the player
        testPlayer=new Characters("test",1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1, Orientation.PLAYER,
                1,1,1,null,null);

        //create the monster on the map
        testMonster=new Characters("test",1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1, Orientation.HOSTILE,
                1,1,1,null,null);
        //create items
        testItem1=new Items("Weapon",1);
        testItem2=new Items("Ring",2);
        testItem3=new Items("Helmet",3);

        //create the Cells[][]
        testCells=new Cells[5][5];
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                testCells[r][c]=new Cells(TileType.GROUND,5,5,new Ground(TileType.GROUND));
            }
        }

    }

    @Test
    public void testAdaptNPC(){
        testPlayer.setLevel(5);
        testCells[2][2]=new Cells(TileType.MONSTER,5,5,testMonster);
        Adaptor testAdaptor =new Adaptor(testCells,testPlayer);
        Assert.assertEquals(1,testMonster.getLevel());
        testAdaptor.adaptNPC();
        Assert.assertEquals(5,testMonster.getLevel());
    }

    @Test
    public void testAdaptAllItemOnMap(){
        ArrayList<Items> backpack=new ArrayList<Items>();
        backpack.add(testItem2);
        ArrayList<Items> worn=new ArrayList<Items>();
        worn.add(0,testItem3);
        testMonster.setBackpack(backpack);
        testMonster.setInventory(worn);
        //add monster into map
        testCells[2][2]=new Cells(TileType.MONSTER,5,5,testMonster);
        //add the chest into map
        testCells[1][1]=new Cells(TileType.CHEST,5,5,testItem1);
        testPlayer.setLevel(18);
        Adaptor testAdaptor =new Adaptor(testCells,testPlayer);
        Assert.assertEquals(1,testItem1.getValue());
        Assert.assertEquals(2,testItem2.getValue());
        Assert.assertEquals(3,testItem3.getValue());
        testAdaptor.adaptAllItemOnMap();
        Assert.assertEquals(5,testItem1.getValue());
        Assert.assertEquals(5,testItem2.getValue());
        Assert.assertEquals(5,testItem3.getValue());

    }
}
