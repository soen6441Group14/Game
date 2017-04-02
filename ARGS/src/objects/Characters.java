package objects;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import Strategy.Strategy;
import dialog.ChangeItemDialog;
import enumclass.Enchantment;
import enumclass.Orientation;
import Strategy.*;
/**
 * Character class
 * @author grey
 *	@version 2.0
 */
@SuppressWarnings("serial")
public class Characters implements Serializable {
	Orientation orient;//hostile or friendly
	public String name;

	public int level;
	public int hitpoints; // hitpoints = 4D6*level + modCon
	public int movement;

	public int strength;
	public int dexterity;//敏捷
	public int constitution;//体质
	public int wisdom;
	public int intelligence;
	public int charisma;//魅力

	
	
	public int armorClass; // armorClass = modDex + armor + shield + 10
	public int attackBonus; //attackBonus = level + modStr
	public int damageBonus;//damageBonus = modStr + weapon + 10

	// mod value = ability value / 3
	public int modStr;
	public int modDex;
	public int modCon;
	public int modInt;
	public int modWis;
	public int modCha;

	
	public ArrayList<Items> inventory; // worn items arraylist
	public ArrayList<Items> backpack; //maximum 10 items
	public Items weapon;	// increase damage bonus
	public Items shield; //increase armor class
	public Items helmet; //increase intelligence
	public Items armor;  // increase armor class
	public Items ring; // increase wisdom
	public Items belt; // increase strength
	public Items boots; // increase dexterity

	public HashMap<Enchantment,Integer> enchanted;
	public int enchantedBonus;
	public Strategy strategy;

/**
 *  constructor method
 * @param name	name
 * @param level level
 * @param hitpoints hitpoints
 * @param movement movement
 * @param strength strength
 * @param modStr modifier of strength
 * @param dexterity dexterity
 * @param modDex modDex
 * @param constitution constitution
 * @param modCon modCon
 * @param wisdom wisdom
 * @param modWis modWis
 * @param intelligence intelligence
 * @param modInt modInt
 * @param charisma charisma
 * @param modCha modCha
 * @param orient orient
 * @param armorClass armorClass
 * @param attackBonus attackBonus
 * @param damageBonus damageBonus
 * @param inventory inventory
 * @param backpack backpack
 */
	public Characters(String name,int level,int hitpoints,int movement,int strength,int modStr,int dexterity,int modDex,
			int constitution,int modCon,int wisdom,int modWis,int intelligence,int modInt,int charisma,int modCha,
			Orientation orient,int armorClass,int attackBonus,int damageBonus,ArrayList<Items> inventory,ArrayList<Items> backpack) {

		this.name = name;
		this.level = level;
		this.hitpoints = hitpoints;
		this.movement = movement;
		this.strength = strength;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.charisma = charisma;
		this.dexterity = dexterity;
		this.wisdom = wisdom;
		this.orient = orient;
		this.armorClass = armorClass;
		this.attackBonus = attackBonus;
		this.damageBonus = damageBonus;
		this.inventory = inventory;
		this.backpack = backpack;
		
		this.modCha = modCha;
		this.modCon = modCon;
		this.modDex = modDex;
		this.modInt = modInt;
		this.modStr = modStr;
		this.modWis = modWis;
		enchanted=new HashMap<Enchantment,Integer>();
	}

	public void turn(){
		// the first thing when its turn is to execute the enchanted effect
		executeEnchantedEffects();
		//and then execute its behaviors, different between its strategies
		this.strategy.execute();
	}

	public void executeEnchantedEffects(){
		if(enchanted.size()==0)
			return;
		else{
			Set<Enchantment> enchantedSet=enchanted.keySet();
			for(Enchantment oneEnchanted:enchantedSet){
				int turnsLeft=enchanted.get(oneEnchanted);
				executeOneEnchantedEffects(oneEnchanted,turnsLeft);
			}
		}
	}

	public void executeOneEnchantedEffects(Enchantment enchantedType,int turnsLeft){
		if(enchantedType==Enchantment.Freezing){
			this.setStrategy(new Frozen()); //frozen的执行属于turn()中的
			turnsLeft--;
			if(turnsLeft==0)
				this.enchanted.remove(enchantedType);
			else
				this.enchanted.put(enchantedType,turnsLeft); //update the turns left
		}
		else if(enchantedType==Enchantment.Burning){
			this.hitpoints-=enchantedBonus*5;
			turnsLeft--;
			if(turnsLeft==0)
				this.enchanted.remove(Enchantment.Burning);
			else
				this.enchanted.put(Enchantment.Burning,turnsLeft);
		}
		else if(enchantedType==Enchantment.Slaying){
			this.hitpoints=0;
			this.enchanted.remove(enchantedType);
		}
		else if(enchantedType==Enchantment.Frightening){
			this.setStrategy(new Frightened());
			turnsLeft--;
			if(turnsLeft==0)
				this.enchanted.remove(Enchantment.Frightening);
			else
				this.enchanted.put(Enchantment.Frightening,turnsLeft);
		}
		else if(enchantedType==Enchantment.Pacifying){
			this.setStrategy(new Friendly());
			this.enchanted.remove(enchantedType);
		}
	}

	public void addEnchantedEffectToCharacter(ArrayList<Enchantment> enchantmentList,int enchantmentBonus){
		for(Enchantment enchantment: enchantmentList){
			if(enchantment==Enchantment.Freezing){
			  	if(this.enchanted.containsKey(Enchantment.Freezing))
			  		this.enchanted.put(Enchantment.Freezing,this.enchanted.get(Enchantment.Freezing)+enchantmentBonus);
			  	else
			  		this.enchanted.put(Enchantment.Freezing,enchantmentBonus);
			}
			else if(enchantment==Enchantment.Burning){
				this.enchanted.put(Enchantment.Burning,3);
				this.enchantedBonus=enchantmentBonus;
			}
			else if(enchantment==Enchantment.Slaying){
				this.enchanted.put(Enchantment.Slaying,1);
			}
			else if(enchantment==Enchantment.Frightening){
				this.enchanted.put(Enchantment.Frightening,enchantmentBonus);
			}
			else if(enchantment==Enchantment.Pacifying){
				this.enchanted.put(Enchantment.Pacifying,1);
			}
		}
	}

	/*interactions*/

	/**
	 * The method is used to interact with chest
	 * @param item the item in the map that you loot
	 */

	public void lootItem(Items item){
		int temp=-1;
		for(int i=0;i<10;i++){
			if(this.backpack.get(i).getName().equals("EMPTY")){
				temp=i;
				break;
			}
		}
		if(temp==-1){
	//		System.out.println("the backpack is full");
		}
		else{
			this.backpack.set(temp,item);
		}
	}


	/**
	 * The method is used to interact with friendly monsters
	 * The interaction is changing the items with monsters
	 * @param friendly  the friendly monster object in the map
	 */
	public void interactWithFriendly(Characters friendly){

		ArrayList<String> itemsNameList = new ArrayList<>();
		for(int i=0;i<10;i++){
			if(!this.backpack.get(i).getName().equals("EMPTY"))
				itemsNameList.add(this.backpack.get(i).getName());
		}

		ChangeItemDialog changeItemDialog = new ChangeItemDialog(itemsNameList);

		String backpackH = changeItemDialog.getSelectedName();
		changeItemDialog.dispose();


		//人物装备
		// the item in the backpack of hero that needs to be exchanged
		Items itemHero = null;
		//人物装备的index
		//the index of hero's item in the his backpack
		int indexHero = 0;
		for(int i=0;i<10;i++){
			if(this.backpack.get(i).getName().equals(backpackH))
			{
				itemHero = this.backpack.get(i);
				indexHero = i;
				break;
			}
		}

		ArrayList<Items> backpackM = friendly.getBackpack();
		//非空装备的个数
		//the number of item that is not empty in the backpack of friendly monster
		int number = 10;
		number = getNumber(backpackM,number);


		int random = 0;
		random = new Random().nextInt(number);//怪物中获取的装备的index
		//get()本来就少一个，所以random()不加一
		Items itemMonster = friendly.getBackpack().get(random);

		// exchange the item between hero and friendly monster
		this.backpack.set(indexHero, itemMonster);
		friendly.getBackpack().set(random, itemHero);

	}

	/**
	 * get the the number of item that is not empty in the backpack of friendly monster
	 * @param backpackM the backpack of friendly monster
	 * @param number the the number of item that is not empty
	 * @return the the number of item that is not empty
	 */
	public int getNumber(ArrayList<Items> backpackM, int number) {
		for(int i=0;i<backpackM.size();i++){
			if(backpackM.get(i).getName().equals("EMPTY"))
				number--;
		}
		return number;
	}

	/**
	 * The method is to interact with hostile monster
	 * the first interaction is killing the monster
	 * the second interaction is to loot its items
	 * @param hostile  the hostile monster in the map
	 * @return true if the hostile is live, false if the hostile has dead
	 */
	public boolean interactWithHostile(Characters hostile){
		boolean live;
		if(hostile.getHitpoints()>0){
			live=true;
			hostile.setHitpoints(0);
		}
		else{
			live=false;
			//loot backpack
			for(int i=0; i<10; i++){
				if(!hostile.getBackpack().get(i).getName().equals("EMPTY")){
					lootItem(hostile.getBackpack().get(i));
					hostile.getBackpack().set(i,new Items("EMPTY",0," "));
				}
			}

			//loot worn items
			for(int i=0; i<hostile.getInventory().size(); i++){
				if(!hostile.getInventory().get(i).getName().equals("EMPTY")){
					lootItem(hostile.getInventory().get(i));
					hostile.getInventory().set(i,new Items("EMPTY",0," "));
				}
			}
		}

		return live;
	}




	/* setters and getters*/
/**
 * 
 * @return constitution
 */
	public int getConstitution() {
		return constitution;
	}
/**
 * 
 * @param constitution constitution
 */
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
/**
 * 
 * @return intelligence
 */
	public int getIntelligence() {
		return intelligence;
	}
/**
 * 
 * @param intelligence intelligence
 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
/**
 * 
 * @return charisma
 */
	public int getCharisma() {
		return charisma;
	}
/**
 * 
 * @param charisma charisma
 */
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
/**
 * 
 * @return armorClass
 */
	public int getArmorClass() {
		return armorClass;
	}
/**
 * 
 * @param armorClass armorClass
 */
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
/**
 * 
 * @return attackBonus
 */
	public int getAttackBonus() {
		return attackBonus;
	}
/**
 * 
 * @param attackBonus attackBonus
 */
	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}
/**
 * 
 * @return damageBonus
 */
	public int getDamageBonus() {
		return damageBonus;
	}
/**
 * 
 * @param damageBonus damageBonus
 */
	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}
/**
 * 
 * @return modStr
 */
	public int getModStr() {
		return modStr;
	}
/**
 * 
 * @param modStr modStr
 */
	public void setModStr(int modStr) {
		this.modStr = modStr;
	}
/**
 * 
 * @return modDex
 */
	public int getModDex() {
		return modDex;
	}
/**
 * 
 * @param modDex modDex
 */
	public void setModDex(int modDex) {
		this.modDex = modDex;
	}
/**
 * 
 * @return modCon
 */
	public int getModCon() {
		return modCon;
	}
/**
 * 
 * @param modCon modCon
 */
	public void setModCon(int modCon) {
		this.modCon = modCon;
	}
/**
 * 
 * @return modInt
 */
	public int getModInt() {
		return modInt;
	}
/**
 * 
 * @param modInt modInt
 */
	public void setModInt(int modInt) {
		this.modInt = modInt;
	}
/**
 * 
 * @return modWis
 */
	public int getModWis() {
		return modWis;
	}
/**
 * 
 * @param modWis modWis
 */
	public void setModWis(int modWis) {
		this.modWis = modWis;
	}
/**
 * 
 * @return modCha
 */
	public int getModCha() {
		return modCha;
	}
/**
 * 
 * @param modCha modCha
 */
	public void setModCha(int modCha) {
		this.modCha = modCha;
	}
/**
 * 
 * @return movement
 */
	public int getMovement() {
		return movement;
	}
/**
 * 
 * @param movement movement
 */
	public void setMovement(int movement) {
		this.movement = movement;
	}
/**
 * 
 * @return orient
 */
	public Orientation getOrient() {
		return orient;
	}
/**
 * 
 * @param orient orient
 */
	public void setOrient(Orientation orient) {
		this.orient = orient;
	}
/**
 * 
 * @return name
 */
	public String getName() {
		return name;
	}
/**
 * 
 * @param name name
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * 
 * @return level
 */
	public int getLevel() {
		return level;
	}
/**
 * 
 * @param level level
 */
	public void setLevel(int level) {
		this.level = level;
	}
/**
 * 
 * @return hitpoints
 */
	public int getHitpoints() {
		return hitpoints;
	}
/**
 * 
 * @param hitpoints hitpoints
 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
/**
 * 
 * @return strength
 */
	public int getStrength() {
		return strength;
	}
/**
 * 
 * @param strength strength
 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * 
	 * @return dexterity
	 */

	public int getDexterity() {
		return dexterity;
	}
/**
 * 
 * @param dexterity dexterity
 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
/**
 * 
 * @return wisdom
 */
	public int getWisdom() {
		return wisdom;
	}
	/**
	 * 
	 * @param wisdom wisdom
	 */
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
/**
 * 
 * @return inventory
 */
	public ArrayList<Items> getInventory() {
		return inventory;
	}
/**
 * 
 * @param inventory inventory
 */
	public void setInventory(ArrayList<Items> inventory) {
		this.inventory = inventory;
	}
/**
 * 
 * @return backpack
 */
	public ArrayList<Items> getBackpack() {
		return backpack;
	}
/**
 * 
 * @param backpack backpack
 */
	public void setBackpack(ArrayList<Items> backpack) {
		this.backpack = backpack;
	}

	/**
	 * @param thestrategy the strategy to set to character
	 */
	public void setStrategy(Strategy thestrategy) {
		this.strategy = thestrategy;
	}
}
