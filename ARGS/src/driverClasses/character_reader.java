package driverClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mainClasses.character;
import mainClasses.item;

/**
 * @author SNaKeRUBIN
 */
public class character_reader {

    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("D:\\character.txt");
        Scanner scan = new Scanner(input);

        List<character> list = new ArrayList<>();

        character temp;

        int count = Integer.parseInt(scan.nextLine());

        for (int x = 0; x < count; x++) {
            temp = new character();

            scan.nextLine();
            String name_ = scan.nextLine();
            temp.name = name_;

            String line = scan.nextLine();
            String[] array = line.split(",");
            temp.level = Integer.parseInt(array[0]);
            temp.hitpoints = Integer.parseInt(array[1]);
            temp.movement = Integer.parseInt(array[2]);

            line = scan.nextLine();
            array = line.split(",");
            temp.strength = Integer.parseInt(array[0]);
            temp.dexterity = Integer.parseInt(array[1]);
            temp.constitution = Integer.parseInt(array[2]);
            temp.wisdom = Integer.parseInt(array[3]);
            temp.intelligence = Integer.parseInt(array[4]);
            temp.charisma = Integer.parseInt(array[5]);

            line = scan.nextLine();
            array = line.split(",");
            temp.weapon = new item(array[0]);
            temp.shield = new item(array[1]);
            temp.helmet = new item(array[2]);
            temp.armor = new item(array[3]);
            temp.ring = new item(array[4]);
            temp.belt = new item(array[5]);
            temp.boots = new item(array[6]);

            line = scan.nextLine();
            array = line.split(",");
            temp.inventory = new item[10];
            for (int i = 0; i < 10; i++) {
                temp.inventory[i] = new item(array[i]);
            }

            temp.modStr = temp.strength / 2;
            temp.modDex = temp.dexterity / 2;
            temp.modCon = temp.constitution / 2;
            temp.modInt = temp.intelligence / 2;
            temp.modWis = temp.wisdom / 2;
            temp.modCha = temp.charisma / 2;

            list.add(temp);
        }

        System.out.println("");
    }
}
