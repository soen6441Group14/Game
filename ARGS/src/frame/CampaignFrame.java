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
 *@version 1.0
 */
public class CampaignFrame {
	public JTextField campaignName = new JTextField();
	public ArrayList<Matrix> showMaps = new ArrayList<>();
	public ArrayList<Matrix> campaign = new ArrayList<>();
	public Campaigns editCampaigns = null;
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
		JComboBox<String> jComboBox = new JComboBox<String>();
		JComboBox<String> ownMaps = new JComboBox<String>();

		
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
		
	    try {
	    	showMaps =	new LoadMap().readMap();
		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
	    for(Matrix matrix: showMaps){
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
				ownMaps.removeAllItems();
				
				editCampaigns = new LoadCampaign().loadCampaign(campaignArraylist, campaignName.getText());
				
				if(editCampaigns == null)
					JOptionPane.showMessageDialog(null, "There is no such a campaign", "Alert", JOptionPane.ERROR_MESSAGE);
				else
					for(Matrix matrix: editCampaigns.getCampaign()){
					ownMaps.addItem(matrix.getName());
				}
			}
		});
		
		//将显示出来的maps中，不需要的去除
		//remove the map that is selected
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(Matrix matrix: editCampaigns.getCampaign()){
					if(matrix.getName().equals(ownMaps.getSelectedItem().toString())){
						editCampaigns.getCampaign().remove(matrix);
						break;
					}
				}
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
				
				Matrix matrix = null;
				
				try {
					matrix = new LoadMap().loadMap2(allMaps, jComboBox.getSelectedItem().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//如果输入的campaign名字已存在(edit)，则添加map。否则新建campaign(create)，加入map
				//if the name you input has already exited, then add map. if not, create a new campaign, add the map
				if(editCampaigns!=null)
					editCampaigns.getCampaign().add(matrix);//这里editCampaign 在开始时为空
				else
					campaign.add(matrix);
					
				
			}
		});
		
		//将campaign存入到campaigns中，再将campaigns保存到campaignArraylist
		//add the campaign to campaignArraylist and save campaignArraylist 
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Campaigns oldCampaign = new LoadCampaign().loadCampaign(campaignArraylist, campaignName.getText());
				//如果原来没有这个名字的campaign，则加入新的campaign。如果原来有，删除原来的，添加现在的
				//if oldCampaign exit,delete original campaign and add new campaign. if not, just add new campaign
				if(oldCampaign == null)//create
				{
				editCampaigns = new Campaigns(campaign, campaignName.getText());
				campaignArraylist.add(editCampaigns);
				}
				else{//edit
					campaignArraylist.remove(oldCampaign);
					campaignArraylist.add(editCampaigns);
				}
				
				try {
					new SaveCampaign().saveCampaign(campaignArraylist);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//在主界面显示campaigns  drawCampaigns();
				// display the campaigns in the main frame
				map.drawCampaignBox();
				
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
				
			}
		});
	}

}
