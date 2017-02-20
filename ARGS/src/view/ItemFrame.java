package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * 
 * ItemFrame is used to input item names and values to create items
 * 
 * @author grey
 *
 */
public class ItemFrame {
	
	
	private JTextField weapon = new JTextField();
	private JTextField shield = new JTextField();
	private JTextField helmet = new JTextField();
	private JTextField armor = new JTextField();
	private JTextField ring = new JTextField();
	private JTextField belt = new JTextField();
	private JTextField boot = new JTextField();
	
	private JTextField weaponName = new JTextField();
	private JTextField shieldName = new JTextField();
	private JTextField helmetName = new JTextField();
	private JTextField armorName = new JTextField();
	private JTextField ringName= new JTextField();
	private JTextField beltName = new JTextField();
	private JTextField bootName = new JTextField();
	//WEAPON, SHIELD, HELMET, ARMOR, RING, BELT, BOOTS,EMPTY
	public ItemFrame(){
		JFrame jFrame = new JFrame("Items");
		JButton jButton = new JButton("Comfirm");
		JLabel weaponLabel = new JLabel("Weapon");
		JLabel shieldLabel = new JLabel("Shield");
		JLabel helmetLabel = new JLabel("Helmet");
		JLabel armorLabel = new JLabel("Armor");
		JLabel ringLabel = new JLabel("Ring");
		JLabel beltLabel = new JLabel("Belt");
		JLabel bootLabel = new JLabel("Boot");
		
		weaponName.setSize(new Dimension(100, 30));
		weaponName.setColumns(10);
		weapon.setSize(new Dimension(100, 30));
		weapon.setColumns(10);
		
		shieldName.setSize(new Dimension(100, 30));
		shieldName.setColumns(10);
		shield.setSize(new Dimension(100, 30));
		shield.setColumns(10);
		
		helmetName.setSize(new Dimension(100, 30));
		helmetName.setColumns(10);
		helmet.setSize(new Dimension(100, 30));
		helmet.setColumns(10);
		
		armorName.setSize(new Dimension(100, 30));
		armorName.setColumns(11);
		armor.setSize(new Dimension(100, 30));
		armor.setColumns(11);
		
		ringName.setSize(new Dimension(100, 30));
		ringName.setColumns(11);
		ring.setSize(new Dimension(100, 30));
		ring.setColumns(11);
		
		beltName.setSize(new Dimension(100, 30));
		beltName.setColumns(11);
		belt.setSize(new Dimension(100, 30));
		belt.setColumns(11);
		
		bootName.setSize(new Dimension(100, 30));
		bootName.setColumns(11);
		boot.setSize(new Dimension(100, 30));
		boot.setColumns(11);
		
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(weaponLabel);
		jFrame.add(weaponName);
		jFrame.add(weapon);
		jFrame.add(shieldLabel);
		jFrame.add(shieldName);
		jFrame.add(shield);
		jFrame.add(helmetLabel);
		jFrame.add(helmetName);
		jFrame.add(helmet);
		jFrame.add(armorLabel);
		jFrame.add(armorName);
		jFrame.add(armor);
		jFrame.add(ringLabel);
		jFrame.add(ringName);
		jFrame.add(ring);
		jFrame.add(beltLabel);
		jFrame.add(beltName);
		jFrame.add(belt);
		jFrame.add(bootLabel);
		jFrame.add(bootName);
		jFrame.add(boot);
		jFrame.add(jButton);
		jFrame.setLocationRelativeTo(null);//put the screen in the center
		jFrame.setSize(new Dimension(350, 500));
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
