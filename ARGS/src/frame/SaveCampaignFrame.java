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
import javax.swing.JFrame;
import objects.Campaigns;
import save.SaveCampaign;

public class SaveCampaignFrame {
	
	ArrayList<Campaigns> arrayListCampaigns = new ArrayList<Campaigns>();
	
	public SaveCampaignFrame(Map map, JFrame jFrame2, Campaigns playingCampaign, ArrayList<Campaigns> campaigns, int playingIndex) {
		
		
		
		JFrame jFrame = new JFrame("Save the campaign");
		JButton jButton = new JButton("Save");
		
		jFrame.setLayout(new FlowLayout());
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
				
			
//				playingCampaign.playingIndex = playingIndex;
//				System.out.println("playingIndex: "+newCampaign.playingIndex );
//				campaigns.set(0,newCampaign);
				arrayListCampaigns.add(playingCampaign);
//				int index = campaigns.indexOf(newCampaign);
//				System.out.println("index: "+index);
				campaigns.get(0).playingIndex = playingIndex;
				try {
					new SaveCampaign().saveEditCampaign(campaigns);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
			}
		});
		
	}

	
	
}
