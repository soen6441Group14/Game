package driverClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mainClasses.character;

/**
 * @author SNaKeRUBIN
 */
//
//  Writing and reading characters
//
//
public class character_writer {

    public static void main(String[] args) throws IOException {

        File output = new File("D:\\character.txt");
        FileWriter writer = new FileWriter(output);

        character a = new character("fighter");
        character b = new character("zombie");

        List<character> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        int size = list.size();
        writer.write(Integer.toString(size) + System.getProperty("line.separator"));
        writer.write(System.getProperty("line.separator"));

        for (character temp : list) {
            writer.write(temp.name + System.getProperty("line.separator"));
            writer.write(temp.level + "," + temp.hitpoints + "," + temp.movement + System.getProperty("line.separator"));
            writer.write(temp.strength + "," + temp.dexterity + "," + temp.constitution + "," + temp.wisdom + ","
                    + temp.intelligence + "," + temp.charisma + System.getProperty("line.separator"));
            writer.write(temp.weapon.printItem() + "," + temp.shield.printItem() + "," + temp.helmet.printItem() + ","
                    + temp.armor.printItem() + "," + temp.ring.printItem() + "," + temp.belt.printItem() + "," + temp.boots.printItem() + System.getProperty("line.separator"));
            writer.write(temp.inventory[0].printItem() + "," + temp.inventory[1].printItem() + ","
                    + temp.inventory[2].printItem() + "," + temp.inventory[3].printItem() + ","
                    + temp.inventory[4].printItem() + "," + temp.inventory[5].printItem() + ","
                    + temp.inventory[6].printItem() + "," + temp.inventory[7].printItem() + ","
                    + temp.inventory[8].printItem() + "," + temp.inventory[9].printItem() + System.getProperty("line.separator"));
            writer.write(System.getProperty("line.separator"));
        }
        writer.flush();
    }

    /*
    number of chars in file // might not be needed

name
lvl,hitpoints,movement
strength,dexterity,constitution,wisdom,intelligence,charisma
weapon,shield,helmet,armor,ring,belt,boots
inventory[0],inventory[1],inventory[2],inventory[3],inventory[4],inventory[5],inventory[6],inventory[7],inventory[8],inventory[9]

name
lvl,hitpoints,movement
strength,dexterity,constitution,wisdom,intelligence,charisma
weapon,shield,helmet,armor,ring,belt,boots
inventory[0],inventory[1],inventory[2],inventory[3],inventory[4],inventory[5],inventory[6],inventory[7],inventory[8],inventory[9]
     */
}
