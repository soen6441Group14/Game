package objects;

import java.io.Serializable;

import enumclass.TileType;
/**
 * Entry class is the object of Entry
 * @author grey
 *	@version 2.0       
 */
@SuppressWarnings("serial")
public class Entry implements Serializable{
	boolean pass;
	TileType tileType;
	/**
	 * constructor method
	 * @param tileType tileType
	 */
	public Entry(TileType tileType){
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
