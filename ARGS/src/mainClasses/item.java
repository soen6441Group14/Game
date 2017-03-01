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

    public String itemCode;
    public int itemvalue;

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
        if (itemCode.equals("-1")) {
            return "null";
        }

        return itemCode;
    }

}
