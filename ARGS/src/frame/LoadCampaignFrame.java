package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import enumclass.TileType;
import load.LoadCampaign;
import objects.Campaigns;
import objects.Characters;
import objects.Matrix;
/**
 * Load Campaign Frame
 * @author grey
 *
 */
public class LoadCampaignFrame {
/**
 * constructor
 * @param map  map class object
 * @param jFrame2  main map frame
 * @param campaigns  arraylist of campaigns
 */
	public LoadCampaignFrame(Map map, JFrame jFrame2, ArrayList<Campaigns> campaigns) {
		
		JFrame jFrame = new JFrame("Load the campaign");
		JButton jButton = new JButton("Load");
		JLabel jLabelName = new JLabel("Input a campaign name");
		JTextField campaignName = new JTextField();
		
		campaignName.setSize(new Dimension(50, 30));
		campaignName.setColumns(8);
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(jLabelName);
		jFrame.add(campaignName);
		jFrame.add(jButton);
		
		jFrame.setLocationRelativeTo(null);//put the screen in the center
		jFrame.setSize(new Dimension(300, 300));
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				jFrame.dispose();
				jFrame2.setEnabled(true);
			}
		});
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				map.playingCampaign=new LoadCampaign().loadCampaign(campaigns,campaignName.getText().toString());
				map.playingIndex = map.playingCampaign.playingIndex;
				map.playingHero = getHeroLocation(map.playingCampaign.getCampaign().get(map.playingIndex));
//				System.out.println(map.playingCampaign.getName());
//				System.out.println(map.playingIndex);
//				System.out.println(map.playingHero.getHitpoints());
				map.initCampaign();
				
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
			}
		});
		
	}
	/**
	 * get hero's location
	 * @param matrix the map that hero is on
	 * @return hero object
	 */
	public Characters getHeroLocation(Matrix matrix){

		Characters hero = null;

		 for(int i=0;i<matrix.getMap()[0][0].getX();i++)
			 for(int j =0; j<matrix.getMap()[0][0].getY();j++){
				 if(matrix.getMap()[i][j].getTileType() == TileType.HERO){
					hero = matrix.getMap()[i][j].getCharacters();
					 break;
				 }
			 }
		return hero;
	}
}
