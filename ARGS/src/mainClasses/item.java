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

    private String itemCode;
    private int itemvalue;

    public item() {
        itemCode = "-1";
    }
    
    public item(String new_temCode) {
        itemCode = new_temCode;
    }
    
    // i add a new constructor here, since it needs the value of item
    public item(String new_temCode,int value) {
        itemCode = new_temCode;
        itemvalue = value;
    }

    public String printItem() {
        if (getItemCode().equals("-1")) {
            return "null";
        }

        return getItemCode();
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the itemvalue
     */
    public int getItemvalue() {
        return itemvalue;
    }

    /**
     * @param itemvalue the itemvalue to set
     */
    public void setItemvalue(int itemvalue) {
        this.itemvalue = itemvalue;
    }

}
