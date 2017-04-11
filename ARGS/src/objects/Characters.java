package objects;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import strategy.Strategy;
import dialog.ChangeItemDialog;
import enumclass.Enchantment;
import enumclass.Orientation;
import strategy.*;
import frame.LoggingWindow;
import frame.Map;

/**
 * Character class
 * @author grey
 *	@version 2.0
 */
@SuppressWarnings("serial")
public class Characters implements Serializable{
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
	public Map dependentMap;
	public Characters afraidCharacter;

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
		if(this.hitpoints<=0){
			System.out.println("[ "+this.name+" ] has dead, so it can not takes turn -> next turn");
			return;
		}

		System.out.println("[ "+this.name+" ] takes turn");
		// the first thing when its turn is to execute the enchanted effect
		executeEnchantedEffects();
		//and then execute its behaviors, different between its strategies
		this.strategy.execute();
	}

	public void executeEnchantedEffects(){
		ArrayList<Enchantment> unvoidEffets=new ArrayList<Enchantment>();

		if(enchanted.size()!=0){
			Set<Enchantment>enchantedSet = enchanted.keySet();
			for(Enchantment oneEnchanted:enchantedSet){
				int turnsLeft=enchanted.get(oneEnchanted);
				Enchantment unvoid=executeOneEnchantedEffects(oneEnchanted,turnsLeft);
				if(unvoid!=null){
					unvoidEffets.add(unvoid);
				}
			}
		}

		//delete the key of which value=0
		if(unvoidEffets.size()!=0){
			for(Enchantment oneUnvoid: unvoidEffets){
				this.enchanted.remove(oneUnvoid);
			}
		}
	}

	public Enchantment executeOneEnchantedEffects(Enchantment enchantedType,int turnsLeft){
		Enchantment unvoidEffect=null;

		if(enchantedType==Enchantment.Freezing){
			if(turnsLeft==0){
				unvoidEffect=Enchantment.Freezing;
				recoverTheCharacterStrategy();
			}
			else{
				this.setStrategy(new Frozen(this)); //frozen的执行属于turn()中的
				turnsLeft--;
				System.out.println("[ "+this.getName()+" ] suffer Freezing enchantment, turnLeft - "+ turnsLeft);
				this.enchanted.replace(Enchantment.Freezing,turnsLeft+1,turnsLeft);
			}
		}
		else if(enchantedType==Enchantment.Burning){
			this.hitpoints-=enchantedBonus*5;
			turnsLeft--;
			System.out.println("[ "+this.getName()+" ] suffer Burning enchantment, turnLeft - "+ turnsLeft);
			if(turnsLeft==0)
				unvoidEffect=Enchantment.Burning;
			else
				this.enchanted.replace(Enchantment.Burning,turnsLeft+1,turnsLeft);
		}
		else if(enchantedType==Enchantment.Slaying){
			this.hitpoints=0;
			System.out.println("[ "+this.getName()+" ] suffer Slaying enchantment, dead " );
			unvoidEffect=Enchantment.Slaying;
		}
		else if(enchantedType==Enchantment.Frightening){
			if(turnsLeft==0){
				unvoidEffect=Enchantment.Frightening;
				recoverTheCharacterStrategy();
			}
			else{
				this.setStrategy(new Frightened(this.dependentMap,this,afraidCharacter));
				turnsLeft--;
				System.out.println("[ "+this.getName()+" ] suffer Frightening enchantment, turnLeft - "+ turnsLeft);
				this.enchanted.replace(Enchantment.Frightening,turnsLeft+1,turnsLeft);
			}
		}
		else if(enchantedType==Enchantment.Pacifying){
			if(this.orient!=Orientation.PLAYER){
				this.setStrategy(new Friendly(this.dependentMap,this));
				System.out.println("[ "+this.getName()+" ] suffer Pacifying enchantment, turnLeft - become Friendly");
			}
			unvoidEffect=Enchantment.Pacifying;
		}

		return unvoidEffect;
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

	public void recoverTheCharacterStrategy(){
		if(this.orient==Orientation.FRIENDLY)
			this.setStrategy(new Friendly(this.dependentMap,this));
		else if(this.orient==Orientation.HOSTILE)
			this.setStrategy(new Aggressive(this.dependentMap,this));
		else if(this.orient==Orientation.PLAYER){
			int isAuto=dependentMap.getIsAuto();
			if(isAuto==0)
				this.setStrategy(this.dependentMap.keyListener);
			else if(isAuto==1)
				this.setStrategy(new ComputerPlayer(this.dependentMap,this.dependentMap.numberMap));
		}
	}

	public void frightenByCharacter(Characters afraidCha){
		this.afraidCharacter=afraidCha;
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
	 * The method is to interact with target monster
	 * the first interaction is killing the monster
	 * the second interaction is to loot its items
	 * @param target  the target monster in the map
	 * @return true if the target is live, false if the target has dead
	 */
	public boolean attackOrLootDead(Characters target){
		
		int attackBonus;
		int d20=getD20();
		int d8=getD8();
		boolean meleeWeapon=false;
		boolean live;
		//if the target is live, damage it
		if(target.getHitpoints()>0){
			live=true;
			if(!this.getInventory().get(0).getName().equals("EMPTY")){//weapon is not null
				if(this.getInventory().get(0).getRange()==1){//melee weapon
					 attackBonus = this.getAttackBonus()+this.getModStr();
					 meleeWeapon=true;
//					 System.out.println(this.getInventory().get(0).getEnchantments().get(0));
				}
				else{//ranged weapon
					 attackBonus = this.getAttackBonus()+this.getModDex();
					 meleeWeapon=false;
				}
				//weapon with enchantment will damage enchantment bonus to target
				if(this.getInventory().get(0).getEnchantments().size()>0 && attackBonus+d20>getArmorClass()){
					int enchantBonus=this.getInventory().get(0).getValue();//from 1 to 5
					target.addEnchantedEffectToCharacter(this.getInventory().get(0).getEnchantments(),enchantBonus);
				}
			}
			else{// character don't have weapon
				attackBonus = this.getAttackBonus();
			}
			
			//deal with damage
			if(attackBonus + d20>=target.getArmorClass()){
				target.setHitpoints(target.getHitpoints()-d8-Math.abs(this.getModStr()));//hitpoints reduce 1d8
				System.out.println("[ "+this.getName()+" ] attack "+target.getName()+" : hurt target");
			}
			else
				System.out.println("[ "+this.getName()+" ] attack "+target.getName()+" : miss");
			//show attack info
			LoggingWindow.getLoggingWindow().updateInfo(this,target,meleeWeapon,d20,d8);
		
		}
		//if the target is dead body, loot items
		else{
			live=false;
			//loot backpack
			for(int i=0; i<10; i++){
				if(!target.getBackpack().get(i).getName().equals("EMPTY")){
					lootItem(target.getBackpack().get(i));
					target.getBackpack().set(i,new Items("EMPTY",0," "));
				}
			}

			//loot worn items
			for(int i=0; i<target.getInventory().size(); i++){
				if(!target.getInventory().get(i).getName().equals("EMPTY")){
					lootItem(target.getInventory().get(i));
					target.getInventory().set(i,new Items("EMPTY",0," "));
				}
			}
		}

		return live;
	}

	/**
	 * The method is used when the user player attack target using attack mouse right button
	 * actually, clickAttack() is a supplement of the attackOrLootDead()
	 * NPC character just allowed to use attackOrDead() to attack target
	 * userPlayer can use both method to attack target
	 * @param target target of character
	 */
	public void clickAttack(Characters target){

		int attackBonus;
		int d20=getD20();
		int d8=getD8();
		boolean meleeWeapon=false;

		if(target.getHitpoints()>0){
			if(!this.getInventory().get(0).getName().equals("EMPTY")){//weapon is not null
				if(this.getInventory().get(0).getRange()==1){//melee weapon
					attackBonus = this.getAttackBonus()+this.getModStr();
					meleeWeapon=true;
				}
				else{//ranged weapon
					attackBonus = this.getAttackBonus()+this.getModDex();
					meleeWeapon=false;
				}
				//weapon with enchantment will damage enchantment bonus to target
				if(this.getInventory().get(0).getEnchantments().size()>0 && attackBonus+d20>getArmorClass()){
					int enchantBonus=this.getInventory().get(0).getValue();//from 1 to 5
					target.addEnchantedEffectToCharacter(this.getInventory().get(0).getEnchantments(),enchantBonus);
				}
			}
			else{// character don't have weapon
				attackBonus = this.getAttackBonus();
			}
			//deal with damage
			if(attackBonus + d20>=target.getArmorClass()){
				target.setHitpoints(target.getHitpoints()-d8-Math.abs(this.getModStr()));//hitpoints reduce 1d8
				System.out.println("[ "+this.getName()+" ] attack "+target.getName()+" : hurt target");
			}
			else
				System.out.println("[ "+this.getName()+" ] attack "+target.getName()+" : miss");

			LoggingWindow.getLoggingWindow().updateInfo(this,target,meleeWeapon,d20,d8);
		}
	}
	//refactoring ... do not use yet!
	public void attackCalculation(Characters target, int d20roll, int d8roll){
		//
				int attackBonus;
				int d20=d20roll;
				int d8=d8roll;
				boolean meleeWeapon=false;

				if(target.getHitpoints()>0){
					if(!this.getInventory().get(0).getName().equals("EMPTY")){//weapon is not null
						if(this.getInventory().get(0).getRange()==1){//melee weapon
							attackBonus = this.getAttackBonus()+this.getModStr();
							meleeWeapon=true;
						}
						else{//ranged weapon
							attackBonus = this.getAttackBonus()+this.getModDex();
							meleeWeapon=false;
						}
						//weapon with enchantment will damage enchantment bonus to target
						if(this.getInventory().get(0).getEnchantments().size()>0 && attackBonus+d20>getArmorClass()){
							int enchantBonus=this.getInventory().get(0).getValue();//from 1 to 5
							target.addEnchantedEffectToCharacter(this.getInventory().get(0).getEnchantments(),enchantBonus);
						}
					}
					else{// character don't have weapon
						attackBonus = this.getAttackBonus();
					}
					//deal with damage
					if(attackBonus + d20>=target.getArmorClass()){
						target.setHitpoints(target.getHitpoints()-d8-Math.abs(this.getModStr()));//hitpoints reduce 1d8
						System.out.println("[ "+this.getName()+" ] attack "+target.getName()+" : hurt target");
					}
					else
						System.out.println("[ "+this.getName()+" ] attack "+target.getName()+" : miss");

					LoggingWindow.getLoggingWindow().updateInfo(this,target,meleeWeapon,d20,d8);
				}
			}

	
	public int getD8(){
		return new Random().nextInt(8)+1;
	}

	public int getD20(){
		return new Random().nextInt(20)+1;
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

	public Strategy getStrategy() {
		return this.strategy;
	}

	/**
	 * @param thestrategy the strategy to set to character
	 */
	public void setStrategy(Strategy thestrategy) {
		this.strategy = thestrategy;
	}

	public void setDependentMap(Map dependentMap) {
		this.dependentMap = dependentMap;
		enchanted=new HashMap<Enchantment,Integer>();
	}
}
