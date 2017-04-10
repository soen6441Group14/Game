package objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Campaigns class which contains all the campaigns
 * @author grey
 * @version 2.0
 */
@SuppressWarnings("serial")
public class Campaigns implements Serializable{
	
	public ArrayList<Matrix> campaign;
	public String name;
	public int playingIndex;
	
	/**
	 * The constructor method
	 * @param campaign 	single campaign
	 * @param name 		the name of campaign 
	 * @param playingIndex the number of map that is showed on the map
	 */
	public Campaigns(ArrayList<Matrix> campaign,String name,int playingIndex){
		this.campaign = campaign;
		this.name = name;
		this.playingIndex = playingIndex;
	}
	
	/**
	 * getCampaign()
	 * @return single campaign
	 */
	public ArrayList<Matrix> getCampaign() {
		return campaign;
	}
	/**
	 * setCampaign(ArrayList of Matrix campaign)
	 * @param campaign 	single campaign
	 */
	public void setCampaign(ArrayList<Matrix> campaign) {
		this.campaign = campaign;
	}
	
	/**
	 * getName()
	 * @return 	the name of campaign
	 */
	public String getName() {
		return name;
	}
	/**
	 * setName(String name)
	 * @param name	the name of campaign
	 */
	public void setName(String name) {
		this.name = name;
	}
}
