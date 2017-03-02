package driverClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import mainClasses.campaign;

/**
 * @author SNaKeRUBIN
 */

public class writeCampaigns {

    public static void main(String[] args) throws IOException {

        ArrayList<campaign> list = new ArrayList<>();

        campaign a = new campaign();
        campaign b = new campaign();
        campaign c = new campaign();

        list.add(a);
        list.add(b);
        list.add(c);

        File output = new File("D:\\_campaign.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(output));
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }
}
