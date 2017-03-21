package objects;

import java.io.Serializable;

import enumclass.TileType;
/**
 * Exit class represents an Exit object
 * @author grey
 * @version 2.0
 */
@SuppressWarnings("serial")
public class Exit implements Serializable{
	boolean pass;
	TileType tileType;
	/**
	 * constructor method
	 * @param tileType tileType
	 */
	public Exit(TileType tileType){
		this.tileType = tileType;
	}
	/**
	 * 
	 * @return tileType
	 */
	public String getName(){
		return tileType.toString();
		
	}
}
