package objects;

import java.io.Serializable;
/**
 * Matrix class represents an map object
 * @author grey
 *@version 1.0
 */
@SuppressWarnings("serial")
public class Matrix implements Serializable{
	
	Cells[][] map ;
	String name;
	/**
	 * constructor method
	 * @param map map
	 * @param name name
	 */
	public Matrix(Cells[][] map,String name){
		this.map = map;
		this.name = name;
	}
/**
 * 
 * @return Cells[][] map
 */
	public Cells[][] getMap() {
		return map;
	}
/**
 *  
 * @param map Cells[][] map
 */
	public void setMap(Cells[][] map) {
		this.map = map;
	}
/**
 * 
 * @return name
 */
	public String getName() {
		return name;
	}
/**
 * 
 * @param name name
 */
	public void setName(String name) {
		this.name = name;
	}

}
