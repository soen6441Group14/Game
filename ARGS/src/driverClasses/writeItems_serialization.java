package driverClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import mainClasses.item;

/**
 * @author SNaKeRUBIN
 */
public class writeItems_serialization {

    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<item>> hashmap = new HashMap<>();
        ArrayList<item> weaponlist = new ArrayList<>();
        ArrayList<item> shieldlist = new ArrayList<>();

        item weapon1 = new item();
        item weapon2 = new item();
        weaponlist.add(weapon1);
        weaponlist.add(weapon2);
        hashmap.put("weapon", weaponlist);

        item shield1 = new item();
        item shield2 = new item();
        shieldlist.add(shield1);
        shieldlist.add(shield2);
        hashmap.put("shield", shieldlist);

        File output = new File("D:\\_item.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        oos.writeObject(hashmap);
        oos.flush();
        oos.close();

        System.out.println("");
    }

}
