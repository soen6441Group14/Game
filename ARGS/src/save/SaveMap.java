package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import objects.Matrix;
/**
 * SaveMap class is used to save the maps to the file
 * @author grey
 *	@version 2.0
 */
public class SaveMap {
	/**
	 * save method
	 * @param allMaps  ArrayList<Matrix>
	 * @throws IOException
	 */
	// 在保存地图时调用，将创建好的Cells二位数组保存在mapName文件中
	public void saveMap(ArrayList<Matrix> allMaps) throws IOException{
		
		File output = new File("ARGS/file/Maps.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
        objectOutputStream.writeObject(allMaps);
        objectOutputStream.flush();
        objectOutputStream.close();
		
	}

}
