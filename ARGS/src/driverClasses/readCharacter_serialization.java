package driverClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import mainClasses.character;

/**
 * @author SNaKeRUBIN
 */
public class readCharacter_serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<character> list;

        File input = new File("D:\\_character.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(input));
        //character temp = (character) ois.readObject();
        list = (ArrayList<character>) ois.readObject();

        System.out.println("");
    }
}
