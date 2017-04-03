package objects;

import java.io.Serializable;
import java.util.ArrayList;

import enumclass.Enchantment;
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
	    public ArrayList<Enchantment> enchantments = new ArrayList<>();
	    public int range;
		public String bonusType;
	    
/**
 * constructor method
 * @param name name
 * @param value value
 */
	    public Items(String name,int value,String bonusType) {
	        this.name = name;
	        this.value = value;
	        this.bonusType = bonusType;
	    }
	    

	    public ArrayList<Enchantment> getEnchantments() {
			return enchantments;
		}


		public void setEnchantments(ArrayList<Enchantment> enchantments) {
			this.enchantments = enchantments;
		}


		public int getRange() {
			return range;
		}


		public void setRange(int range) {
			this.range = range;
		}
	    
		public String getBonusType() {
			return bonusType;
		}
		
		public void setBonusType(String bonusType) {
			this.bonusType = bonusType;
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
