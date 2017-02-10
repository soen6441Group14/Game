package driverClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import mainClasses.character;

/**
 * @author SNaKeRUBIN
 */
public class writeCharacter_serialization {

    public static void main(String[] args) throws IOException {

        List<character> list = new ArrayList<>();

        character a = new character("fake1");
        character b = new character("fake2");
        character c = new character("fake3");

        list.add(a);
        list.add(b);
        list.add(c);

        File output = new File("D:\\_character.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }
}
