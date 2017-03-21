package objects;



import java.io.Serializable;
import java.util.ArrayList;
import enumclass.Orientation;
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

	
	
	public int armorClass; // armorClass = modDex + armor + shield
	public int attackBonus; //attackBonus = level + modStr
	public int damageBonus;//damageBonus = modStr + weapon

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

	}
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

	

}
