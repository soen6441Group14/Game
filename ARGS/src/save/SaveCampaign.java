package save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import objects.Campaigns;
/**
 * saveCampaign class is used to save the campaigns to the file
 * @author grey
 * @version 2.0
 */
public class SaveCampaign {
/**
 * 	save method
 * @param campaignArraylist  ArrayList<Campaigns>
 * @throws IOException
 */
public void saveCampaign(ArrayList<Campaigns> campaignArraylist) throws IOException{
		
		File output = new File("file/Campaigns.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
        objectOutputStream.writeObject(campaignArraylist);
        objectOutputStream.flush();
        objectOutputStream.close();
}

public void saveEditCampaign(ArrayList<Campaigns> campaignArraylist) throws IOException{
	
	File output = new File("file/EditCampaigns.txt");
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
    objectOutputStream.writeObject(campaignArraylist);
    objectOutputStream.flush();
    objectOutputStream.close();
}

}
