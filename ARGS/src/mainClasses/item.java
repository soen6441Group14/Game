package mainClasses;

import enumClasses.itemTypes;
import java.io.Serializable;

/**
 * @author SNaKeRUBIN
 */
//
//  use hashmap for items
//  item identifier of form a001
//
//
public class item implements Serializable {

	itemTypes type;
    public String name;
    public int value;
    public item(){
    	
    }
    
/**
* constructor method
* @param name name
* @param value value
*/
    
    public item(String name,int value,itemTypes type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
    
    public itemTypes getType() {
	return type;
}

    public void setType(itemTypes type) {
	this.type = type;
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
/**
* 
* @return value
*/
	public int getValue() {
		return value;
	}
/**
* 
* @param value value
*/
	public void setValue(int value) {
		this.value = value;
	}



}
