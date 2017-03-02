package driverClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import mainClasses.map;

/**
 * @author SNaKeRUBIN
 */
public class writeMaps {

    public static void main(String[] args) throws IOException {

        ArrayList<map> list = new ArrayList<>();

        map a = new map();
        map b = new map();
        map c = new map();

        list.add(a);
        list.add(b);
        list.add(c);

        File output = new File("D:\\_maps.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }
}
