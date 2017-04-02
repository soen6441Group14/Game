package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import build.BullyBuilder;
import build.Director;
import build.FighterBulider;
import build.NimbleBuilder;
import build.Scores;
import build.TankBuilder;
import enumclass.Orientation;
import load.LoadCharacter;
import load.LoadItem;
import objects.Characters;
import objects.Items;
import save.SaveCharacter;
/**
 * CharacterFrame is used to create a new character or edit a character
 * @author grey
 *@version 2.0
 */

public class CharacterFrame {

	public Map map;
//	public ArrayList<Items> newItemArrayList = new ArrayList<Items>();//显示创建人物时的物品下拉框
	Characters characters = null;
	ArrayList<Items> newItemArrayList = new ArrayList<Items>();
	JComboBox<String> fighter = new JComboBox<String>();
	
	private JTextField name = new JTextField();
	private JTextField level = new JTextField();
	private JTextField hitpoints = new JTextField();
	private JTextField movement = new JTextField();
	private JTextField strength = new JTextField();
	private JTextField constitution = new JTextField();
	private JTextField dexterity = new JTextField();
	private JTextField wisdom = new JTextField();
	private JTextField intelligence = new JTextField();
	private JTextField charisma = new JTextField();
	private JTextField orient = new JTextField();
	
	private JTextField modStr = new JTextField();
	private JTextField modDex = new JTextField();
	private JTextField modCon = new JTextField();
	private JTextField modWis = new JTextField();
	private JTextField modInt = new JTextField();
	private JTextField modCha = new JTextField();
	private JTextField armorClass = new JTextField();
	private JTextField attackBonus = new JTextField();
	private JTextField damageBonus = new JTextField();
	
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

	/**
	 * constructor method
	 * @param map        the Map object
	 * @param jFrame2    the main frame
	 * @param characterArrayList
	 * @param itemArrayList
	 */
	public CharacterFrame(Map map, JFrame jFrame2, ArrayList<Characters> characterArrayList, ArrayList<Items> itemArrayList){
		JFrame jFrame = new JFrame("Character");
		JButton save = new JButton("Save");
		JButton roll = new JButton("Roll");
		JButton load = new JButton("Load a character");
		JButton Modify = new JButton("Modify");
		JButton loadItem = new JButton("Load an item");
		JComboBox<String> jComboBox = new JComboBox<String>();
		
		JLabel nameLabel = new JLabel("Name");
		JLabel levelLabel = new JLabel("Level");
		JLabel hitpointsLabel = new JLabel("Hitpoint");
		JLabel movementLabel = new JLabel("Movement");
		JLabel strengthLabel = new JLabel("Strength");
		JLabel constitutionLable = new JLabel("Constitution");
		JLabel intelligenceLable = new JLabel("Intelligence");
		JLabel charismaLable = new JLabel("Charisma");
		JLabel dexterityLabel = new JLabel("Dexterity");
		JLabel wisdomLabel = new JLabel("Wisdom");
		JLabel orientLabel = new JLabel("Orient");
		
		JLabel modStrLabel = new JLabel("modStr");
		JLabel modDexLabel = new JLabel("modDex");
		JLabel modConLabel = new JLabel("modCon");
		JLabel modWisLabel = new JLabel("modWis");
		JLabel modIntLabel = new JLabel("modInt");
		JLabel modChaLabel = new JLabel("modCha");
		
		JLabel armorClassLabel = new JLabel("armorClass");
		JLabel attackBonusLabel = new JLabel("attackBonus");
		JLabel damageBonusLabel = new JLabel("damageBonus");
		
		JLabel weaponLabel = new JLabel("Weapon");
		JLabel shieldLabel = new JLabel("Shield");
		JLabel helmetLabel = new JLabel("Helmet");
		JLabel armorLabel = new JLabel("Armor");
		JLabel ringLabel = new JLabel("Ring");
		JLabel beltLabel = new JLabel("Belt");
		JLabel bootLabel = new JLabel("Boot");
		
		weaponName.setSize(new Dimension(50, 30));
		weaponName.setColumns(10);
		weapon.setSize(new Dimension(50, 30));
		weapon.setColumns(10);
		
		shieldName.setSize(new Dimension(50, 30));
		shieldName.setColumns(10);
		shield.setSize(new Dimension(50, 30));
		shield.setColumns(10);
		
		helmetName.setSize(new Dimension(50, 30));
		helmetName.setColumns(10);
		helmet.setSize(new Dimension(50, 30));
		helmet.setColumns(10);
		
		armorName.setSize(new Dimension(50, 30));
		armorName.setColumns(11);
		armor.setSize(new Dimension(50, 30));
		armor.setColumns(11);
		
		ringName.setSize(new Dimension(50, 30));
		ringName.setColumns(11);
		ring.setSize(new Dimension(50, 30));
		ring.setColumns(11);
		
		beltName.setSize(new Dimension(50, 30));
		beltName.setColumns(11);
		belt.setSize(new Dimension(50, 30));
		belt.setColumns(11);
		
		bootName.setSize(new Dimension(50, 30));
		bootName.setColumns(11);
		boot.setSize(new Dimension(50, 30));
		boot.setColumns(11);
		
		
		loadItem.setSize(new Dimension(50, 30));
		
		name.setSize(new Dimension(50, 30));
		name.setColumns(8);
		
		level.setSize(new Dimension(50, 30));
		level.setColumns(8);

		hitpoints.setSize(new Dimension(50, 30));
		hitpoints.setColumns(8);
		
		movement.setSize(new Dimension(50, 30));
		movement.setColumns(8);
		
		strength.setSize(new Dimension(50, 30));
		strength.setColumns(8);
		
		constitution.setSize(new Dimension(50, 30));
		constitution.setColumns(8);
		
		dexterity.setSize(new Dimension(50, 30));
		dexterity.setColumns(8);
		
		wisdom.setSize(new Dimension(50, 30));
		wisdom.setColumns(8);
		
		intelligence.setSize(new Dimension(50, 30));
		intelligence.setColumns(8);
		
		charisma.setSize(new Dimension(50, 30));
		charisma.setColumns(8);
		
		orient.setSize(new Dimension(50, 30));
		orient.setColumns(8);
		
		modCha.setSize(new Dimension(50, 30));
		modCha.setColumns(8);
		
		modStr.setSize(new Dimension(50, 30));
		modStr.setColumns(8);

		modDex.setSize(new Dimension(50, 30));
		modDex.setColumns(8);
		
		modCon.setSize(new Dimension(50, 30));
		modCon.setColumns(6);
		
		modInt.setSize(new Dimension(50, 30));
		modInt.setColumns(8);
		
		modWis.setSize(new Dimension(50, 30));
		modWis.setColumns(8);
		
		armorClass.setSize(new Dimension(50, 30));
		armorClass.setColumns(8);
		
		attackBonus.setSize(new Dimension(50, 30));
		attackBonus.setColumns(6);
		
		damageBonus.setSize(new Dimension(50, 30));
		damageBonus.setColumns(6);
		
		
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(nameLabel);
		jFrame.add(name);
		jFrame.add(levelLabel);
		jFrame.add(level);
		jFrame.add(hitpointsLabel);
		jFrame.add(hitpoints);
		jFrame.add(movementLabel);
		jFrame.add(movement);
		jFrame.add(strengthLabel);
		jFrame.add(strength);
		jFrame.add(modStrLabel);
		jFrame.add(modStr);
		jFrame.add(dexterityLabel);
		jFrame.add(dexterity);
		jFrame.add(modDexLabel);
		jFrame.add(modDex);
		jFrame.add(constitutionLable);
		jFrame.add(constitution);
		jFrame.add(modConLabel);
		jFrame.add(modCon);
		jFrame.add(wisdomLabel);
		jFrame.add(wisdom);
		jFrame.add(modWisLabel);
		jFrame.add(modWis);
		jFrame.add(intelligenceLable);
		jFrame.add(intelligence);
		jFrame.add(modIntLabel);
		jFrame.add(modInt);
		jFrame.add(charismaLable);
		jFrame.add(charisma);
		jFrame.add(modChaLabel);
		jFrame.add(modCha);
		jFrame.add(orientLabel);
		jFrame.add(orient);
		
		jFrame.add(armorClassLabel);
		jFrame.add(armorClass);
		jFrame.add(attackBonusLabel);
		jFrame.add(attackBonus);
		jFrame.add(damageBonusLabel);
		jFrame.add(damageBonus);
		
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
		
		jFrame.add(loadItem);
		jFrame.add(jComboBox);
		jFrame.add(roll);
		jFrame.add(fighter);
		jFrame.add(Modify);
		jFrame.add(save);
		jFrame.add(load);
		

		// display all the items which are created
		for(Items items : itemArrayList)
			jComboBox.addItem(items.getName());
		
		fighter.addItem("Bully");
		fighter.addItem("Nimble");
		fighter.addItem("Tank");
			
		
		jFrame.setLocationRelativeTo(null);//put the screen in the center
		jFrame.setSize(new Dimension(350, 800));
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				jFrame.dispose();
				jFrame2.setEnabled(true);
			}
		});
		
		//According to different kind of fighter, change the ability scores
		fighter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				arrayList.add(Integer.parseInt(strength.getText()));
				arrayList.add(Integer.parseInt(constitution.getText()));
				arrayList.add(Integer.parseInt(dexterity.getText()));
				arrayList.add(Integer.parseInt(intelligence.getText()));
				arrayList.add(Integer.parseInt(charisma.getText()));
				arrayList.add(Integer.parseInt(wisdom.getText()));
				
				Scores scores = setFighter(arrayList);
				
				strength.setText(String.valueOf(scores.getStrength()));
				constitution.setText(String.valueOf(scores.getConstitution()));
				dexterity.setText(String.valueOf(scores.getDexterity()));
				intelligence.setText(String.valueOf(scores.getIntelligence()));
				charisma.setText(String.valueOf(scores.getCharisma()));
				wisdom.setText(String.valueOf(scores.getWisdom()));
				
				int mod = getMod(Integer.parseInt(strength.getText()));
				
				modStr.setText(String.valueOf(mod));
				mod = getMod(Integer.parseInt(dexterity.getText())); 
				modDex.setText(String.valueOf(mod));
				mod = getMod(Integer.parseInt(wisdom.getText())); 
				modWis.setText(String.valueOf(mod));
				mod = getMod(Integer.parseInt(constitution.getText()));
				modCon.setText(String.valueOf(mod));
				mod = getMod(Integer.parseInt(intelligence.getText()));
				modInt.setText(String.valueOf(mod));
				mod = getMod(Integer.parseInt(charisma.getText()));
				modCha.setText(String.valueOf(mod));
			}

			

			
		});
		
		// load an existed character 
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				characters = load(characterArrayList,name.getText());
				
				
				if(characters == null)
					JOptionPane.showMessageDialog(null, "There is no such a character", "Alert", JOptionPane.ERROR_MESSAGE);
				else{
					name.setText(characters.getName());
					level.setText(String.valueOf(characters.getLevel()));
					hitpoints.setText(String.valueOf(characters.getHitpoints()));
					movement.setText(String.valueOf(characters.getMovement()));
					strength.setText(String.valueOf(characters.getStrength()));
					modStr.setText(String.valueOf(characters.getModStr()));
					dexterity.setText(String.valueOf(characters.getDexterity()));
					modDex.setText(String.valueOf(characters.getModDex()));
					constitution.setText(String.valueOf(characters.getConstitution()));
					modCon.setText(String.valueOf(characters.getModCon()));
					wisdom.setText(String.valueOf(characters.getWisdom()));
					modWis.setText(String.valueOf(characters.getModWis()));
					intelligence.setText(String.valueOf(characters.getIntelligence()));
					modInt.setText(String.valueOf(characters.getModInt()));
					charisma.setText(String.valueOf(characters.getCharisma()));
					modCha.setText(String.valueOf(characters.getModCha()));
					
					orient.setText(String.valueOf(characters.getOrient()));
					armorClass.setText(String.valueOf(characters.getArmorClass()));
					attackBonus.setText(String.valueOf(characters.getAttackBonus()));
					damageBonus.setText(String.valueOf(characters.getDamageBonus()));
					
					weaponName.setText(characters.getInventory().get(0).getName());
					weapon.setText(String.valueOf(characters.getInventory().get(0).getValue()));
					shieldName.setText(characters.getInventory().get(1).getName());
					shield.setText(String.valueOf(characters.getInventory().get(1).getValue()));
					helmetName.setText(characters.getInventory().get(2).getName());
					helmet.setText(String.valueOf(characters.getInventory().get(2).getValue()));
					armorName.setText(characters.getInventory().get(3).getName());
					armor.setText(String.valueOf(characters.getInventory().get(3).getValue()));
					ringName.setText(characters.getInventory().get(4).getName());
					ring.setText(String.valueOf(characters.getInventory().get(4).getValue()));
					beltName.setText(characters.getInventory().get(5).getName());
					belt.setText(String.valueOf(characters.getInventory().get(5).getValue()));
					bootName.setText(characters.getInventory().get(6).getName());
					boot.setText(String.valueOf(characters.getInventory().get(6).getValue()));
					
				}
				
			}

			
		});
		
		// change the attribute of character because of some items and modifiers
		Modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
			intelligence.setText(String.valueOf(Integer.parseInt(intelligence.getText())+Integer.parseInt(helmet.getText())));
			strength.setText(String.valueOf(Integer.parseInt(strength.getText())+Integer.parseInt(belt.getText())));
			wisdom.setText(String.valueOf(Integer.parseInt(wisdom.getText())+Integer.parseInt(ring.getText())));
			dexterity.setText(String.valueOf(Integer.parseInt(dexterity.getText())+Integer.parseInt(boot.getText())));
			hitpoints.setText(String.valueOf(Integer.parseInt(level.getText())*getValues()+Integer.parseInt(modCon.getText())));
			armorClass.setText(String.valueOf(Integer.parseInt(modDex.getText())+Integer.parseInt(armor.getText())+Integer.parseInt(shield.getText())+10));
			attackBonus.setText(String.valueOf(Integer.parseInt(level.getText())+Integer.parseInt(modStr.getText())));
			damageBonus.setText(String.valueOf(Integer.parseInt(modStr.getText())+Integer.parseInt(weapon.getText())+10));
			}
		});
		
		//choose the items from the file to equip character
		loadItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String string = jComboBox.getSelectedItem().toString();
				Items items = new LoadItem().loadItem(string,itemArrayList);
				String name = items.getName();
				String value = String.valueOf(items.getValue());
				
				if(name.startsWith("W")||name.startsWith("w"))//大小写问题 正则表达式
				{
					weaponName.setText(name);
					weapon.setText(value);
				}
				else if(name.startsWith("S")||name.startsWith("s"))
				{
					shieldName.setText(name);
					shield.setText(value);
				}
				else if(name.startsWith("H")||name.startsWith("h"))
				{
					helmetName.setText(name);
					helmet.setText(value);
				}
				else if(name.startsWith("A")||name.startsWith("a"))
				{
					armorName.setText(name);
					armor.setText(value);
				}
				else if(name.startsWith("R")||name.startsWith("r"))
				{
					ringName.setText(name);
					ring.setText(value);
				}
				else if(name.startsWith("BELT")||name.startsWith("belt"))
				{
					beltName.setText(name);
					belt.setText(value);
				}
				else if(name.startsWith("BOOT")||name.startsWith("boot"))
				{
					bootName.setText(name);
					boot.setText(value);
				}
				newItemArrayList.add(items);
			}
		});
		
		// save the character
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
			ArrayList<Items> backpack = new ArrayList<Items>();
			
			
//			newItemArrayList.add(new Items(shieldName.getText(), Integer.parseInt(shield.getText())));
//			newItemArrayList.add(new Items(helmetName.getText(), Integer.parseInt(helmet.getText())));
//			newItemArrayList.add(new Items(armorName.getText(), Integer.parseInt(armor.getText())));
//			newItemArrayList.add(new Items(ringName.getText(), Integer.parseInt(ring.getText())));
//			newItemArrayList.add(new Items(beltName.getText(), Integer.parseInt(belt.getText())));
//			newItemArrayList.add(new Items(bootName.getText(), Integer.parseInt(boot.getText())));
			
			//编辑character时，会使backpack恢复默认值
			if(name.getText().startsWith("P")||name.getText().startsWith("p"))
			{
				backpack.add(new Items("WEAPON2", 2,"damageBonus"));
				backpack.add(new Items("BOOT2", 2,"dexterity"));
				backpack.add(new Items("BELT2", 2,"strength"));
				backpack.add(new Items("RING2", 2,"wisdom"));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
			}
			else{
				backpack.add(new Items("SHIELD2", 2,"armorClass"));
				backpack.add(new Items("HELMET2", 2,"intelligence"));
				backpack.add(new Items("ARMOR2", 2,"armorClass"));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
				backpack.add(new Items("EMPTY", 0," "));
			}
			
			

			Characters oldcharacters = null;
			try {
				oldcharacters = new LoadCharacter().loadcharacter(name.getText(), characterArrayList);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			characters = new Characters(name.getText(),Integer.parseInt(level.getText()), Integer.parseInt(hitpoints.getText()),
					Integer.parseInt(movement.getText()), Integer.parseInt(strength.getText()),Integer.parseInt(modStr.getText()),
					Integer.parseInt(dexterity.getText()),Integer.parseInt(modDex.getText()), Integer.parseInt(constitution.getText()),Integer.parseInt(modCon.getText()),
					Integer.parseInt(wisdom.getText()),Integer.parseInt(modWis.getText()),Integer.parseInt(intelligence.getText()),Integer.parseInt(modInt.getText()),
					Integer.parseInt(charisma.getText()),Integer.parseInt(modCha.getText()),Enum.valueOf(Orientation.class, orient.getText()),
					Integer.parseInt(armorClass.getText()),Integer.parseInt(attackBonus.getText()),Integer.parseInt(damageBonus.getText()),newItemArrayList,backpack);
			
			
			
			if(oldcharacters == null)
				characterArrayList.add(characters);
			else {
				//替代原有的character
				int index = characterArrayList.indexOf(oldcharacters);
				characterArrayList.set(index,characters);
				
			}
			
			
			try {
				new SaveCharacter().saveCharacter(characterArrayList);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			map.drawcharacterBox();
			map.drawInformation();
			
			jFrame2.setEnabled(true);
		    jFrame.dispose();
				
				
				
			}
		});
		
		// set the attribute of Player
		roll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int values = getValues();
				strength.setText(String.valueOf(values));
				values = getValues();
				dexterity.setText(String.valueOf(values));
				values = getValues();
				wisdom.setText(String.valueOf(values));
				values = getValues();
				constitution.setText(String.valueOf(values));
				values = getValues();
				intelligence.setText(String.valueOf(values));
				values = getValues();
				charisma.setText(String.valueOf(values));
			}
			
		});
	}
	
	/**
	 * load character's information to the character frame
	 * @param characterArrayList  arraylist that contains all the characters
	 * @param text the character's name in the text field
	 * @return  the character object 
	 */
	public Characters load(ArrayList<Characters> characterArrayList, String text) {
		try {
			characters = new LoadCharacter().loadcharacter(text, characterArrayList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return characters;
	}
	
	/**
	 * use the build pattern to set different kinds of character
	 * @param arrayList 6 attributes of character
	 * @return scores object that contains 6 attributes of character
	 */
	public Scores setFighter(ArrayList<Integer> arrayList) {
		
		Director director;
		Scores scores;
		
		FighterBulider bully = new BullyBuilder();
		FighterBulider nimble = new NimbleBuilder();
		FighterBulider tank = new TankBuilder();
		
		director = new Director();
		
		if(fighter.getSelectedItem().toString().equals("Bully")){
			director.setBuilder(bully);
			director.constructScores(arrayList);
			scores = director.getScores();
		}
		else if(fighter.getSelectedItem().toString().equals("Nimble")){
			director.setBuilder(nimble);
			director.constructScores(arrayList);
			scores = director.getScores();
		}
		else{
			director.setBuilder(tank);
			director.constructScores(arrayList);
			scores = director.getScores();
		}
		
		
		return scores;
	}
	
	public int getMod(int i) {
		if(i==1)
			return -5;
		else if(i==2 || i==3)
			return -4;
		else if (i==4 || i==5)
			return -3;
		else if (i==6 || i==7)
			return -2;
		else if (i==8 || i==9)
			return -1;
		else if (i==10 || i==11)
			return 0;
		else if (i==12 || i==13)
			return 1;
		else if (i==14 || i==15)
			return 2;
		else if (i==16 || i==17)
			return 3;
		else if (i==18 || i==19)
			return 4;
		else if (i==20 || i==21)
			return 5;
		else 
			return 6;
		
	}
	
	/**
	 * 4D6 method choose 3 of highest number in 4 times of roll
	 * @return the total amount of 3 of highest number
	 */
	public int getValues() {
		Random random = new Random();
		int[] array = new int[4];
		
		for (int i = 0; i < 4; i++) {
			array[i] = random.nextInt(6)+1;

		}
		Arrays.sort(array);
		
		return array[1]+array[2]+array[3];				
	}

}
