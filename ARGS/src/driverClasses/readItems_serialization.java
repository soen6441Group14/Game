package driverClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import mainClasses.item;

/**
 * @author SNaKeRUBIN
 */
public class readItems_serialization {

    public HashMap<String, ArrayList<item>> readItems() throws IOException, ClassNotFoundException {
        HashMap<String, ArrayList<item>> hashmap = new HashMap<>();

        File input = new File("D:\\_item.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(input));
        //character temp = (character) ois.readObject();
        hashmap = (HashMap<String, ArrayList<item>>) ois.readObject();

       return hashmap;
    }

}
