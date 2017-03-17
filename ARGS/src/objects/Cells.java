package objects;

import java.io.Serializable;

import enumclass.TileType;

/**
 * Cells is the object which represents a grid of map
 * @author grey
 *	@version 1.0
 */
@SuppressWarnings("serial")
public class Cells implements Serializable{
	
	TileType tileType;
	int x, y;//the total length of rows and columns
	boolean isVisited;
	Characters characters;
	Entry entry;
	Exit exit;
	Ground ground;
	Items items;
	Wall wall;
	
	/**
	 * constructor method
	 * @param tileType 	different type of grid
	 * @param x		the length of row
	 * @param y		the length of column
	 * @param wall	the object wall
	 */

	public Cells(TileType tileType,int x, int y,Wall wall){
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.wall = wall;
	}
	/**
	 * constructor method
	 * @param tileType 	different type of grid
	 * @param x		the length of row
	 * @param y		the length of column
	 * @param entry	the object entry
	 */
	
	public Cells(TileType tileType,int x, int y,Entry entry){
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.entry = entry;
	}
	/**
	 * constructor method
	 * @param tileType 	different type of grid
	 * @param x		the length of row
	 * @param y		the length of column
	 * @param exit	the object exit
	 */
	public Cells(TileType tileType,int x, int y,Exit exit){
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.exit = exit;
	}
	/**
	 * constructor method
	 * @param tileType 	different type of grid
	 * @param x		the length of row
	 * @param y		the length of column
	 * @param ground	the object ground
	 */
	public Cells(TileType tileType,int x, int y,Ground ground){
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.ground = ground;
		}
	/**
	 * constructor method
	 * @param tileType 	different type of grid
	 * @param x		the length of row
	 * @param y		the length of column
	 * @param items	the object items
	 */
	public Cells(TileType tileType,int x, int y,Items items){
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.items = items;
	}
	/**
	 * constructor method
	 * @param tileType 	different type of grid
	 * @param x		the length of row
	 * @param y		the length of column
	 * @param characters	the object characters
	 */
	public Cells(TileType tileType,int x, int y,Characters characters){
		this.tileType = tileType;
		this.x = x;
		this.y = y;
		this.characters = characters;
	}
	
	/**
	 * 
	 * @return characters
	 */
	
	public Characters getCharacters() {
		return characters;
	}
	/**
	 * 
	 * @return entry
	 */
	public Entry getEntry() {
		return entry;
	}
	/**
	 * 
	 * @return exit
	 */
	public Exit getExit() {
		return exit;
	}
	/**
	 * 
	 * @return ground
	 */
	public Ground getGround() {
		return ground;
	}
	/**
	 * 
	 * @return items
	 */
	public Items getItems() {
		return items;
	}
	/**
	 * 
	 * @return wall
	 */
	public Wall getWall() {
		return wall;
	}
	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

/**
 * 
 * @param x   the length of row
 */
	public void setX(int x) {
		this.x = x;
	}
/**
 * 
 * @return y
 */
	public int getY() {
		return y;
	}
/**
 * 
 * @param y the length of column
 */
	public void setY(int y) {
		this.y = y;
	}
/**
 * 
 * @return tileType
 */
	public TileType getTileType(){
		return tileType;
	}
	/**
	 * 
	 * @param tileType tileType
	 */
	public void setTileType(TileType tileType){
		this.tileType = tileType;
	}
	
	
	

}
