package load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import objects.Cells;
import objects.Matrix;
/**
 * LoadMap contains method that return Matrix or ArrayList<Matrix> or Cells[][]
 * @author grey
 *@version 2.0
 */
public class LoadMap {
	Cells[][] cells;
	Matrix newMatrix;
	
	/**
	 * when we create a campaign, we need to get the specific map with the name of Stirng
	 * @param allMaps ArrayList<Matrix>
	 * @param string name
	 * @return  Matrix object
	 * @throws IOException
	 */
	public Matrix loadMap2(ArrayList<Matrix> allMaps, String string) throws IOException{
		for(Matrix matrix: allMaps)
		{
			if(matrix.getName().equals(string)){
				newMatrix = matrix;
				break;
			}
				
		}
		
		return newMatrix;
		
	}
	/**
	 * display all the maps which are created
	 * @return ArrayList<Matrix>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Matrix> readMap() throws IOException, ClassNotFoundException{
		
		ArrayList<Matrix> arrayList = new ArrayList<Matrix>();
		File input = new File("file/Maps.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
        arrayList = (ArrayList<Matrix>) objectInputStream.readObject();
        objectInputStream.close();
        
        return arrayList;
	}
/**
 * when we load a map, we can get the Cells[][] by searching the name in the arraylist of maps
 * @param allMaps ArrayList<Matrix>
 * @param string3 name
 * @return Cells[][]
 * @throws IOException
 */
	// 在读取地图时调用，根据输入的名字，加载不同的地图
	public Cells[][] loadMap(ArrayList<Matrix> allMaps, String string3) throws IOException{
		
		for(Matrix matrix: allMaps){
			if(matrix.getName().equals(string3)){
				cells = matrix.getMap();
				break;
			}
		}
		return cells;
		
		
	}

}
