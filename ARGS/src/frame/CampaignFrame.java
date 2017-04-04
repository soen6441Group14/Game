package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import load.LoadCampaign;
import load.LoadMap;
import objects.Campaigns;
import objects.Matrix;
import save.SaveCampaign;
/**
 * CampaignFrame is used to create a new campaign or edit a campaign
 * @author grey
 *@version 2.0
 */
public class CampaignFrame {
	public JTextField campaignName = new JTextField();
	public ArrayList<Matrix> showMaps = new ArrayList<>();
	public ArrayList<Matrix> campaign = new ArrayList<>();
	public Campaigns editCampaigns = null;
	
	JComboBox<String> ownMaps = new JComboBox<String>();
	JComboBox<String> jComboBox = new JComboBox<String>();
	/**
	 * constructor method, when the CampaignFrame is called,  it will invoke 
	 * @param map
	 * @param jFrame2
	 * @param allMaps
	 * @param campaignArraylist
	 */
	public CampaignFrame(Map map, JFrame jFrame2,ArrayList<Matrix> allMaps, ArrayList<Campaigns> campaignArraylist){
		JFrame jFrame = new JFrame("Items");
		JButton save = new JButton("Save");
		JButton loadMap = new JButton("Load a map");
		JButton loadCampaign = new JButton("Load a campaign");
		JButton remove = new JButton("Remove");
		JLabel campaignLabel = new JLabel("Campaign name");
		
		

		
		campaignName.setSize(new Dimension(100, 30));
		campaignName.setColumns(10);
		
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(campaignLabel);
		jFrame.add(campaignName);
		jFrame.add(loadMap);
		jFrame.add(jComboBox);
		jFrame.add(save);
		jFrame.add(loadCampaign);
		jFrame.add(ownMaps);
		jFrame.add(remove);
		
//	    try {
//	    	showMaps =	new LoadMap().readMap();
//		} catch (ClassNotFoundException | IOException e2) {
//			e2.printStackTrace();
//		}
	    
	    for(Matrix matrix: allMaps){
	    	jComboBox.addItem(matrix.getName());
	    }
		
		jFrame.setLocationRelativeTo(null);//put the screen in the center
		jFrame.setSize(new Dimension(350, 500));
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				jFrame.dispose();
				jFrame2.setEnabled(true);
			}
		});
		
		//将对应名字的campaign中的maps显示在JComBox中
		// display the name of maps in specific campaigns
		loadCampaign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = loadCampaign(campaignArraylist);
				System.out.println(flag);
			}

		});
		
		//将显示出来的maps中，不需要的去除
		//remove the map that is selected
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = remove();
				System.out.println(flag);
				
				//去除相应地图之后，在界面显示出来
				// display the map in the campaign again
				ownMaps.removeAllItems();
				for(Matrix matrix: editCampaigns.getCampaign()){
					ownMaps.addItem(matrix.getName());
				}
			}
		});
	
		
		//将选中的map存入campaign中
		//load a selected map to the campaign
		loadMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = loadMap(allMaps);
				System.out.println(flag);
				
			}
		});
		
		//将campaign存入到campaigns中，再将campaigns保存到campaignArraylist
		//add the campaign to campaignArraylist and save campaignArraylist 
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean flag = save(campaignArraylist);
				System.err.println(flag);
				
				//在主界面显示campaigns  drawCampaigns();
				// display the campaigns in the main frame
				map.drawCampaignBox();
				
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
			}
		});
	}
	/**
	 * save the campaign Arraylist to files
	 * @param campaignArraylist   campaign Arraylist
	 * @return if the campaign is edited and saved then it is true; otherwise it is false
	 */
	public boolean save(ArrayList<Campaigns> campaignArraylist) {
		boolean flag = false;
		//如果原来没有这个名字的campaign，则加入新的campaign。如果原来有，删除原来的，添加现在的
		//if oldCampaign exit,delete original campaign and add new campaign. if not, just add new campaign
		if(editCampaigns == null)//create
		{
			editCampaigns = new Campaigns(campaign, campaignName.getText());
			campaignArraylist.add(editCampaigns);
		}
		else{//edit
			int index = campaignArraylist.indexOf(editCampaigns);
			campaignArraylist.set(index,editCampaigns);
			flag = true;
		}
		
		try {
			new SaveCampaign().saveCampaign(campaignArraylist);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * load specific map in the arraylist to the campaign frame
	 * @param allMaps  the arraylist that contains all maps
	 * @return if the campaign does not exist, then it is true; otherwise it is false
	 */
	public boolean loadMap(ArrayList<Matrix> allMaps) {
		boolean flag = false;
		Matrix matrix = null;
		
		try {
			matrix = new LoadMap().loadMap2(allMaps, jComboBox.getSelectedItem().toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//如果输入的campaign名字已存在(edit)，则添加map。否则新建campaign(create)，加入map
		//if the name you input has already exited, then add map. if not, create a new campaign, add the map
		if(editCampaigns!=null)
			editCampaigns.getCampaign().add(matrix);//这里editCampaign 在开始时为空
		else{
			campaign.add(matrix);
			flag = true;
		}
		return flag;
	}
	/**
	 * remove the map that is selected
	 * @return if the map exist, then it is true; otherwise it is false
	 */
	public boolean remove() {
		boolean flag = false;
		
		for(Matrix matrix: editCampaigns.getCampaign()){
			if(matrix.getName().equals(ownMaps.getSelectedItem().toString())){
				editCampaigns.getCampaign().remove(matrix);
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	/**
	 * load the campaign that you select
	 * @param campaignArraylist the arraylist that contains all the campaigns
	 * @return  if the campaign that you select exist, then it is true; otherwise it is false
	 */
	public boolean loadCampaign(ArrayList<Campaigns> campaignArraylist) {
		boolean flag = false;
		ownMaps.removeAllItems();
		
		editCampaigns = new LoadCampaign().loadCampaign(campaignArraylist, campaignName.getText());
		
		if(editCampaigns == null)
			JOptionPane.showMessageDialog(null, "There is no such a campaign", "Alert", JOptionPane.ERROR_MESSAGE);
		else{
			for(Matrix matrix: editCampaigns.getCampaign()){
			ownMaps.addItem(matrix.getName());
		}
			flag = true;
		}
		return flag;
	}
	

}
