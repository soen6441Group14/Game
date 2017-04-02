package frame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enumclass.Enchantment;
import load.LoadItem;
import objects.Items;
import save.SaveItem;
/**
 * ItemFrame is used to create a new Item or edit a Item
 * @author grey
 * @version 2.0
 */
public class ItemFrame {

	public Map map;
	public Items oldItems;
	public Items items = null;
	public int count = 0;
	public JLabel armorarmorClassLabel = new JLabel("armorClass");
	public JLabel helmetarmorClassLabel = new JLabel("armorClass");
	public JLabel shieldarmorClassLabel = new JLabel("armorClass");
	public JLabel ringarmorClassLabel = new JLabel("armorClass");
	public JLabel bootarmorClassLabel = new JLabel("armorClass");
	public JLabel intelligenceLabel = new JLabel("intelligence");
	public JLabel ringwisdomLabel = new JLabel("wisdom");
	public JLabel helmetwisdomLabel = new JLabel("wisdom");
	public JLabel ringstrengthLabel = new JLabel("strength");
	public JLabel beltstrengthLabel = new JLabel("strength");
	public JLabel ringconstitutionLabel = new JLabel("constitution");
	public JLabel beltconstitutionLabel = new JLabel("constitution");
	public JLabel charismaLabel = new JLabel("charisma");
	public JLabel dexterityLabel = new JLabel("dexterity");
	public JLabel attackBonusLabel = new JLabel("attackBonus");
	public JLabel damageBonusLabel = new JLabel("damageBonus");
	public JLabel rangeLable = new JLabel("range");
	public JTextField itemName = new JTextField();
	public JTextField range = new JTextField();
	public JTextField armorarmorClassValue = new JTextField();
	public JTextField helmetarmorClassValue = new JTextField();
	public JTextField ringarmorClassValue = new JTextField();
	public JTextField shieldarmorClassValue = new JTextField();
	public JTextField bootarmorClassValue = new JTextField();
	public JTextField intelligenceValue = new JTextField();
	public JTextField ringwisdomValue = new JTextField();
	public JTextField helmetwisdomValue = new JTextField();
	public JTextField ringstrengthValue = new JTextField();
	public JTextField beltstrengthValue = new JTextField();
	public JTextField ringconstitutionValue = new JTextField();
	public JTextField beltconstitutionValue = new JTextField();
	public JTextField charismaValue = new JTextField();
	public JTextField dexterityValue = new JTextField();
	public JTextField attackBonusValue = new JTextField();
	public JTextField damageBonusValue = new JTextField();

	// WEAPON, SHIELD, HELMET, ARMOR, RING, BELT, BOOTS,EMPTY
/**
 * constructor method
 * @param map 		Map object
 * @param jFrame2   main frame
 * @param itemArrayList  itemArrayList
 */
	public ItemFrame(Map map, JFrame jFrame2, ArrayList<Items> itemArrayList) {
		JFrame jFrame = new JFrame("Items");
		JComboBox<String> jComboBox = new JComboBox<>();
		JComboBox<String> enchantment = new JComboBox<>();
		
		JPanel main = new JPanel(new FlowLayout());
		JPanel helmet = new JPanel();
		JPanel armor = new JPanel();
		JPanel ring = new JPanel();
		JPanel weapon = new JPanel();
		JPanel shield = new JPanel();
		JPanel belt = new JPanel();
		JPanel boot = new JPanel();
		JButton save = new JButton("Save");
		JButton load = new JButton("Load an item");
		JButton add = new JButton("Add enchantment");
		
		
		range.setColumns(5);
		
//		itemName.setSize(new Dimension(100, 30));
		itemName.setColumns(10);
//		shieldarmorClassValue.setSize(new Dimension(100, 30));
		shieldarmorClassValue.setColumns(5);
//		armorarmorClassValue.setSize(new Dimension(100, 30));
		armorarmorClassValue.setColumns(5);
//		ringarmorClassValue.setSize(new Dimension(100, 30));
		ringarmorClassValue.setColumns(5);
//		bootarmorClassValue.setSize(new Dimension(100, 30));
		bootarmorClassValue.setColumns(5);
//		helmetarmorClassValue.setSize(new Dimension(100, 30));
		helmetarmorClassValue.setColumns(5);
		
//		intelligenceValue.setSize(new Dimension(100, 30));
		intelligenceValue.setColumns(5);
//		ringwisdomValue.setSize(new Dimension(100, 30));
		ringwisdomValue.setColumns(5);
//		helmetwisdomValue.setSize(new Dimension(100, 30));
		helmetwisdomValue.setColumns(5);
//		ringstrengthValue.setSize(new Dimension(100, 30));
		ringstrengthValue.setColumns(5);
//		beltstrengthValue.setSize(new Dimension(100, 30));
		beltstrengthValue.setColumns(5);
//		ringconstitutionValue.setSize(new Dimension(100, 30));
		ringconstitutionValue.setColumns(5);
//		beltconstitutionValue.setSize(new Dimension(100, 30));
		beltconstitutionValue.setColumns(5);
//		charismaValue.setSize(new Dimension(100, 30));
		charismaValue.setColumns(5);
//		dexterityValue.setSize(new Dimension(100, 30));
		dexterityValue.setColumns(5);
//		attackBonusValue.setSize(new Dimension(100, 30));
		attackBonusValue.setColumns(5);
//		damageBonusValue.setSize(new Dimension(100, 30));
		damageBonusValue.setColumns(5);
		

		jComboBox.addItem("Helmet");
		jComboBox.addItem("Armor");
		jComboBox.addItem("Weapon");
		jComboBox.addItem("Shield");
		jComboBox.addItem("Ring");
		jComboBox.addItem("Belt");
		jComboBox.addItem("Boot");
		
		enchantment.addItem("Freezing");
		enchantment.addItem("Burning");
		enchantment.addItem("Slaying");
		enchantment.addItem("Frightening");
		enchantment.addItem("Pacifying");
		
		
		
		armor.add(armorarmorClassLabel);
		armor.add(armorarmorClassValue);
		
		helmet.add(intelligenceLabel);
		helmet.add(intelligenceValue);
		helmet.add(helmetwisdomLabel);
		helmet.add(helmetwisdomValue);
		helmet.add(helmetarmorClassLabel);
		helmet.add(helmetarmorClassValue);
		
		shield.add(shieldarmorClassLabel);
		shield.add(shieldarmorClassValue);
		
		ring.add(ringwisdomLabel);
		ring.add(ringwisdomValue);
		ring.add(ringarmorClassLabel);
		ring.add(ringarmorClassValue);
		ring.add(ringstrengthLabel);
		ring.add(ringstrengthValue);
		ring.add(ringconstitutionLabel);
		ring.add(ringconstitutionValue);
		ring.add(charismaLabel);
		ring.add(charismaValue);
		
		belt.add(beltstrengthLabel);
		belt.add(beltstrengthValue);
		belt.add(beltconstitutionLabel);
		belt.add(beltconstitutionValue);
		
		boot.add(dexterityLabel);
		boot.add(dexterityValue);
		boot.add(bootarmorClassLabel);
		boot.add(bootarmorClassValue);
		
		weapon.add(damageBonusLabel);
		weapon.add(damageBonusValue);
		weapon.add(attackBonusLabel);
		weapon.add(attackBonusValue);
	
		
		CardLayout cardLayout = new CardLayout();
		main.setLayout(cardLayout);
		main.add(helmet,"Helmet");
		main.add(armor,"Armor");
		main.add(weapon,"Weapon");
		main.add(shield,"Shield");
		main.add(ring,"Ring");
		main.add(belt,"Belt");
		main.add(boot,"Boot");
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(jComboBox);
		jFrame.add(itemName);
		jFrame.add(enchantment);
		jFrame.add(rangeLable);
		jFrame.add(range);
		jFrame.add(add);
		jFrame.add(load);
		jFrame.add(save);
		jFrame.add(main);
		
		jFrame.setLocationRelativeTo(null);// put the screen in the center
		jFrame.setSize(new Dimension(900, 500));
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jFrame.dispose();
				jFrame2.setEnabled(true);
			}
		});
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(count==0)
				init(itemArrayList);
				
				if(jComboBox.getSelectedItem().toString().equals("Weapon")){
					items.enchantments.add(Enum.valueOf(Enchantment.class, enchantment.getSelectedItem().toString()));
					items.setRange(Integer.parseInt(range.getText()));
				}
				else
					JOptionPane.showMessageDialog(null, "Please choose a weapon", "Alert", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		jComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(jComboBox.getSelectedItem().toString().equals("Helmet"))
					cardLayout.show(main, "Helmet");
				else if(jComboBox.getSelectedItem().toString().equals("Ring"))
					cardLayout.show(main, "Ring");
				else if(jComboBox.getSelectedItem().toString().equals("Weapon"))
					cardLayout.show(main, "Weapon");
				else if(jComboBox.getSelectedItem().toString().equals("Shield"))
					cardLayout.show(main, "Shield");
				else if(jComboBox.getSelectedItem().toString().equals("Armor"))
					cardLayout.show(main, "Armor");
				else if(jComboBox.getSelectedItem().toString().equals("Belt"))
					cardLayout.show(main, "Belt");
				else 
					cardLayout.show(main, "Boot");
				
				
				
			}
		});;
		
		//load a item value with the input name
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				oldItems = new LoadItem().loadItem(itemName.getText(), itemArrayList);
				if(oldItems == null)
					JOptionPane.showMessageDialog(null, "There is no such item", "Alert", JOptionPane.ERROR_MESSAGE);
				else{
					if(itemName.getText().startsWith("H")||itemName.getText().startsWith("h"))
					{
						if(oldItems.getBonusType().equals("intelligence")){
							intelligenceValue.setText(String.valueOf(oldItems.getValue()));
							helmetwisdomValue.setText("0");
							helmetarmorClassValue.setText("0");
						}
						else if(oldItems.getBonusType().equals("wisdom")){
							intelligenceValue.setText("0");
							helmetwisdomValue.setText(String.valueOf(oldItems.getValue()));
							helmetarmorClassValue.setText("0");
						}
						else {
							intelligenceValue.setText("0");
							helmetwisdomValue.setText("0");
							helmetarmorClassValue.setText(String.valueOf(oldItems.getValue()));
						}
					}
					else if(itemName.getText().startsWith("a")||itemName.getText().startsWith("A"))
					{
						armorarmorClassValue.setText(String.valueOf(oldItems.getValue()));
					}
					else if(itemName.getText().startsWith("s")||itemName.getText().startsWith("S"))
					{
						shieldarmorClassValue.setText(String.valueOf(oldItems.getValue()));
					}
					else if(itemName.getText().startsWith("r")||itemName.getText().startsWith("R"))
					{
						if(oldItems.getBonusType().equals("wisdom")){
							ringwisdomValue.setText(String.valueOf(oldItems.getValue()));
							ringarmorClassValue.setText("0");
							ringconstitutionValue.setText("0");
							ringstrengthValue.setText("0");
							charismaValue.setText("0");
						}
						else if (oldItems.getBonusType().equals("armorClass")){
							ringwisdomValue.setText("0");
							ringarmorClassValue.setText(String.valueOf(oldItems.getValue()));
							ringconstitutionValue.setText("0");
							ringstrengthValue.setText("0");
							charismaValue.setText("0");
						}
						else if (oldItems.getBonusType().equals("strength")){
							ringwisdomValue.setText("0");
							ringarmorClassValue.setText("0");
							ringconstitutionValue.setText("0");
							ringstrengthValue.setText(String.valueOf(oldItems.getValue()));
							charismaValue.setText("0");
						}
						else if (oldItems.getBonusType().equals("constitution")){
							ringwisdomValue.setText("0");
							ringarmorClassValue.setText("0");
							ringconstitutionValue.setText(String.valueOf(oldItems.getValue()));
							ringstrengthValue.setText("0");
							charismaValue.setText("0");
						}
						else {
							ringwisdomValue.setText("0");
							ringarmorClassValue.setText("0");
							ringconstitutionValue.setText("0");
							ringstrengthValue.setText("0");
							charismaValue.setText(String.valueOf(oldItems.getValue()));
						}
					}
					else if(itemName.getText().startsWith("w")||itemName.getText().startsWith("W"))
					{	
						if(oldItems.getBonusType().equals("damageBonus")){
							damageBonusValue.setText(String.valueOf(oldItems.getValue()));
							attackBonusValue.setText("0");
						}
						else{
							damageBonusValue.setText("0");
							attackBonusValue.setText(String.valueOf(oldItems.getValue()));
						}
						range.setText(String.valueOf(oldItems.getRange()));
					}
					else if(itemName.getText().startsWith("belt")||itemName.getText().startsWith("BELT"))
					{
						if(oldItems.getBonusType().equals("strength")){
							beltstrengthValue.setText(String.valueOf(oldItems.getValue()));
							beltconstitutionValue.setText("0");
						}
						else{
							beltstrengthValue.setText("0");
							beltconstitutionValue.setText(String.valueOf(oldItems.getValue()));
						}
					}
					else 
					{	
						if(oldItems.getBonusType().equals("dexterity")){
							dexterityValue.setText(String.valueOf(oldItems.getValue()));
							bootarmorClassValue.setText("0");
						}
						else {
							dexterityValue.setText("0");
							bootarmorClassValue.setText(String.valueOf(oldItems.getValue()));
						}
					}
					
				}
					
			}
		});
		
		// save a item
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(count == 0)
				init(itemArrayList);
				
				// if the item with input name exist , remove it and add new item. if not, add item
				if (oldItems == null) {
					itemArrayList.add(items);
				}
				else {
					itemArrayList.remove(oldItems);
					itemArrayList.add(items);
				}

				try {
					new SaveItem().saveItem(itemArrayList);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
				System.out.println(items.getEnchantments().get(0));
				map.drawItemBox();

				jFrame2.setEnabled(true);
				jFrame.dispose();

			}

			
		});

	}
	
	public void init(ArrayList<Items> itemArrayList) {
		ArrayList<Enchantment> enchantments = null;
		int range = 0;
		
		oldItems = new LoadItem().loadItem(itemName.getText(), itemArrayList);
		if(oldItems != null){
			enchantments = oldItems.getEnchantments();
			range = oldItems.getRange();
		}
		
		if(itemName.getText().startsWith("H")||itemName.getText().startsWith("h"))
		{	
			if(!intelligenceValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(intelligenceValue.getText()),"intelligence");
			else if (!helmetwisdomValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(helmetwisdomValue.getText()),"wisdom");
			else 
				items = new Items(itemName.getText(), Integer.parseInt(helmetarmorClassValue.getText()),"armorClass");
		}
		else if(itemName.getText().startsWith("a")||itemName.getText().startsWith("A")){
			items = new Items(itemName.getText(), Integer.parseInt(armorarmorClassValue.getText()),"armorClass");
		}
		else if(itemName.getText().startsWith("s")||itemName.getText().startsWith("S")){
			items = new Items(itemName.getText(), Integer.parseInt(shieldarmorClassValue.getText()),"armorClass");
		}
		else if(itemName.getText().startsWith("r")||itemName.getText().startsWith("R")){
			if(!ringwisdomValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(ringwisdomValue.getText()),"wisdom");
			else if(!ringarmorClassValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(ringarmorClassValue.getText()),"armorClass");
			else if(!ringconstitutionValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(ringconstitutionValue.getText()),"constitution");
			else if(!ringstrengthValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(ringstrengthValue.getText()),"strength");
			else 
				items = new Items(itemName.getText(), Integer.parseInt(charismaValue.getText()),"charisma");
		}
		else if(itemName.getText().startsWith("w")||itemName.getText().startsWith("W")){
			if(!damageBonusValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(damageBonusValue.getText()),"damageBonus");
			else 
				items = new Items(itemName.getText(), Integer.parseInt(attackBonusValue.getText()),"attackBonus");
			
			if(oldItems != null){
				items.enchantments = enchantments;
				items.range = range;
			}
		}
		else if(itemName.getText().startsWith("belt")||itemName.getText().startsWith("BELT")){
			if(!beltstrengthValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(beltstrengthValue.getText()),"strength");
			else 
				items = new Items(itemName.getText(), Integer.parseInt(beltconstitutionValue.getText()),"constitution");
		}
		else {
			if(!dexterityValue.getText().equals("0"))
				items = new Items(itemName.getText(), Integer.parseInt(dexterityValue.getText()),"dexterity");
			else 
				items = new Items(itemName.getText(), Integer.parseInt(bootarmorClassValue.getText()),"armorClass");
		}
		count++;
	}
}
