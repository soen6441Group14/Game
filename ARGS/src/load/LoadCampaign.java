package load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import objects.Campaigns;
/**
 * LoadCampaign class contains methods which get ArrayList of Campaigns or Campaigns
 * @author grey
 *@version 2.0
 */
public class LoadCampaign {
	
	Campaigns campaigns;
	/**
	 * read all the campaigns which are created 
	 * @return ArrayList of Campaigns
	 * @throws IOException IOException
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Campaigns> readCampaign() throws IOException, ClassNotFoundException{
		
		ArrayList<Campaigns> arrayList = new ArrayList<Campaigns>();
		File input = new File("file/Campaigns.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
        arrayList = (ArrayList<Campaigns>) objectInputStream.readObject();
        objectInputStream.close();
        
        return arrayList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Campaigns> readEditCampaign() throws IOException, ClassNotFoundException{
		
		ArrayList<Campaigns> arrayList = new ArrayList<Campaigns>();
		File input = new File("file/EditCampaigns.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
        arrayList = (ArrayList<Campaigns>) objectInputStream.readObject();
        objectInputStream.close();
        
        return arrayList;
	}

	public Campaigns readGameRecord() throws IOException, ClassNotFoundException{

		Campaigns recordCampaign=null;

		File input = new File("file/playingGame.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
		recordCampaign = (Campaigns) objectInputStream.readObject();
		objectInputStream.close();

		return recordCampaign;
	}
	
	/**
	 * read the specific campaigns object which has the name with String name
	 * @param allMaps ArrayList of Campaigns
	 * @param name name
	 * @return Campaigns object
	 */
	public Campaigns loadCampaign(ArrayList<Campaigns> allMaps, String name){
		
		for(Campaigns c: allMaps)
		{
			if(c.getName().equals(name)){
				campaigns = c;
				break;
			}
		}
		return campaigns;
	}

}
