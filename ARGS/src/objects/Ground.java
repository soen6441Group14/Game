package objects;

import java.io.Serializable;

import enumclass.TileType;
/**
 * Ground class represents an Ground object
 * @author grey
 *	@version 2.0
 */
@SuppressWarnings("serial")
public class Ground implements Serializable{
	boolean through;
	TileType tileType;
	/**
	 * constructor method
	 * @param tileType tileType
	 */
	public Ground(TileType tileType){
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
