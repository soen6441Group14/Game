package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import objects.Items;
/**
 * SaveItem class is used to save the items to the file
 * @author grey
 *	@version 2.0
 */
public class SaveItem {

	/**
	 * save method
	 * @param arrayList  ArrayList<Items>
	 * @throws IOException
	 */
	public void saveItem(ArrayList<Items> arrayList) throws IOException{
		
		File output = new File("file/Items.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
        objectOutputStream.writeObject(arrayList);
        objectOutputStream.flush();
        objectOutputStream.close();

		
	}
}
