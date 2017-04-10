package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import enumclass.Orientation;
import objects.Characters;
import objects.Items;

public class TestAttackRoll {
	 
	Characters player,mons;
	Items item1,item2;
	ArrayList<Items> testInventory;
	
	@Before
    public void before(){
		
		player=new Characters("test",1,100,1,1,50,1,1,1,1,1,1,1,1,1,1,Orientation.PLAYER,1,1,1,null,null);
        mons = new Characters("test",1,100,1,1,1,1,1,1,1,1,1,1,1,1,1,Orientation.HOSTILE,1,1,1,null,null);
        
        testInventory=new ArrayList<Items>();
        for(int i=0;i<10;i++){
            Items empty=new Items("EMPTY",0,"");
            testInventory.add(empty);
        }
        item1=new Items("Weapon1",1,"");
        item2=new Items("Ring1",1,"");
        testInventory.add(0, item1);
        testInventory.add(1, item2);
        
        player.setInventory(testInventory);
           
	}
	
	@Test
	public void testAttackOrLootDead() {
		
		int monsHitPoints = mons.getHitpoints();
		System.out.println(monsHitPoints);
		
		player.attackOrLootDead(mons);
		
		
		monsHitPoints = mons.getHitpoints();
		System.out.println(monsHitPoints);
		
	}

}
