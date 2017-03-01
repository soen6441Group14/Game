package driverClasses;

import java.util.PriorityQueue;
import mainClasses.item;

/**
 * @author SNaKeRUBIN
 */
public class itemCode_generator {

    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int i = 0; i < 1000; i++) {
            minHeap.add(i);
        }

//        item weapon1 = new item("a" + String.format("%03d", minHeap.remove()));
//        item weapon2 = new item("a" + String.format("%03d", minHeap.remove()));
//        item weapon3 = new item("a" + String.format("%03d", minHeap.remove()));

//        System.out.println(weapon1.printItem());
//        System.out.println(weapon2.printItem());
//        System.out.println(weapon3.printItem());
    }

}
