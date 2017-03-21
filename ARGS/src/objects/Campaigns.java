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
	
	/**
	 * The constructor method
	 * @param campaign 	single campaign
	 * @param name 		the name of campaign 
	 */
	public Campaigns(ArrayList<Matrix> campaign,String name){
		this.campaign = campaign;
		this.name = name;
	}
	
	/**
	 * getCampaign()
	 * @return single campaign
	 */
	public ArrayList<Matrix> getCampaign() {
		return campaign;
	}
	/**
	 * setCampaign(ArrayList<Matrix> campaign)
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
