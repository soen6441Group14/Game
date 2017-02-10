package mainClasses;

import enumClasses.orientation;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * @author SNaKeRUBIN
 */
public class character implements Serializable {

    orientation orient;
    public String name;

    public int level;
    public int hitpoints;
    public int movement;

    public int strength;
    public int dexterity;
    public int constitution;
    public int wisdom;
    public int intelligence;
    public int charisma;

    // mod value = ability value / 3
    public int modStr;
    public int modDex;
    public int modCon;
    public int modInt;
    public int modWis;
    public int modCha;

    public item[] inventory;
    public item weapon;
    public item shield;
    public item helmet;
    public item armor;
    public item ring;
    public item belt;
    public item boots;

    public character() {
    }

    public character(String new_name) {

        name = new_name;

        level = 1;
        hitpoints = -1;
        movement = fourD6() / 6;

        strength = fourD6();
        dexterity = fourD6();
        constitution = fourD6();
        wisdom = fourD6();
        intelligence = fourD6();
        charisma = fourD6();

        modStr = strength / 3;
        modDex = dexterity / 3;
        modCon = constitution / 3;
        modInt = intelligence / 3;
        modWis = wisdom / 3;
        modCha = charisma / 3;

        inventory = new item[10];

        for (int i = 0; i < 10; i++) {
            inventory[i] = new item();
        }
        
        weapon = new item();
        shield = new item();
        helmet = new item();
        armor = new item();
        ring = new item();
        belt = new item();
        boots = new item();

    }

    public int fourD6() {
        Random rand = new Random();

        int[] abc = new int[4];
        for (int i = 0; i < 4; i++) {
            abc[i] = rand.nextInt(6) + 1;
        }

        Arrays.sort(abc);

        return abc[1] + abc[2] + abc[3];

    }
}
