package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import objects.Characters;
/**
 * SaveCharacter class is used to save the characters to the file
 * @author grey
 * @version 2.0
 */
public class SaveCharacter {
	/**
	 * save method
	 * @param arrayList ArrayList of Characters
	 * @throws IOException  no file exception
	 */
	public void saveCharacter(ArrayList<Characters> arrayList) throws IOException{
		File output = new File("file/Characters.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
        objectOutputStream.writeObject(arrayList);
        objectOutputStream.flush();
        objectOutputStream.close();
	}
	


}
