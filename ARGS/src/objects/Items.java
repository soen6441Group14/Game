package objects;

import java.io.Serializable;

import enumclass.ItemType;
/**
 *  Items class represents an Item object
 * @author grey
 *@version 2.0
 */
@SuppressWarnings("serial")
public class Items implements Serializable {
		ItemType type;

	    public String name;
	    public int value;
	    
/**
 * constructor method
 * @param name name
 * @param value value
 */
	    public Items(String name,int value) {
	        this.name = name;
	        this.value = value;
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
