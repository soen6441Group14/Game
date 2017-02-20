package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CharacterFrame {

	private JTextField name = new JTextField();
	private JTextField level = new JTextField();
	private JTextField hitpoints = new JTextField();
	private JTextField strength = new JTextField();
	private JTextField dexterity = new JTextField();
	private JTextField wisdom = new JTextField();
	private JTextField orient = new JTextField();

	
	public CharacterFrame(){
		JFrame jFrame = new JFrame("Items");
		JButton jButton = new JButton("Comfirm");
		JLabel nameLabel = new JLabel("Name");
		JLabel levelLabel = new JLabel("Level");
		JLabel hitpointsLabel = new JLabel("Hitpoint");
		JLabel strengthLabel = new JLabel("Strength");
		JLabel dexterityLabel = new JLabel("Dexterity");
		JLabel wisdomLabel = new JLabel("Wisdom");
		JLabel orientLabel = new JLabel("Orient");
		
		name.setSize(new Dimension(100, 30));
		name.setColumns(10);
		
		level.setSize(new Dimension(100, 30));
		level.setColumns(10);

		hitpoints.setSize(new Dimension(100, 30));
		hitpoints.setColumns(10);
		
		strength.setSize(new Dimension(100, 30));
		strength.setColumns(10);
		
		dexterity.setSize(new Dimension(100, 30));
		dexterity.setColumns(10);
		
		wisdom.setSize(new Dimension(100, 30));
		wisdom.setColumns(10);
		
		orient.setSize(new Dimension(100, 30));
		orient.setColumns(10);
		
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(levelLabel);
		jFrame.add(level);
		jFrame.add(hitpointsLabel);
		jFrame.add(hitpoints);
		jFrame.add(strengthLabel);
		jFrame.add(strength);
		jFrame.add(dexterityLabel);
		jFrame.add(dexterity);
		jFrame.add(wisdomLabel);
		jFrame.add(wisdom);
		jFrame.add(orientLabel);
		jFrame.add(orient);
		jFrame.add(jButton);
		jFrame.setLocationRelativeTo(null);//put the screen in the center
		jFrame.setSize(new Dimension(200, 500));
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
		
				jFrame.dispose();
//				jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				
			}
		});

		
	}



}
