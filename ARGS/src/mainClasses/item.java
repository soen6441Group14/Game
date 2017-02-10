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

    public item() {
        itemCode = "-1";
    }

    public item(String new_temCode) {
        itemCode = new_temCode;
    }

    public String printItem() {
        if (itemCode.equals("-1")) {
            return "null";
        }

        return itemCode;
    }

}
