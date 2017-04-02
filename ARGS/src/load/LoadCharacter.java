package load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import objects.Characters;
import objects.Items;
/**
 * LoadCharacter class contains method which return the variable in the character
 * @author grey
 *@version 2.0
 */
public class LoadCharacter {
	ArrayList<Items> inventory = new ArrayList<Items>();
	ArrayList<Items> backpack = new ArrayList<Items>();
	Characters characters;
	Items weapon;
	Items shield;
	Items helmet;
	Items armor;
	Items ring;
	Items belt;
	Items boot;
	Items backpack1;
	Items backpack2;
	Items backpack3;
	Items backpack4;
	Items backpack5;
	Items backpack6;
	Items backpack7;
	Items backpack8;
	Items backpack9;
	Items backpack10;

	/**
	 * when we create the map, we need to add specific character with the name on the JComBox by using this method
	 * @param string  name
	 * @param characterArrayList  ArrayList<Characters>
	 * @return Characters object
	 * @throws IOException  IOException
	 */
	//在创建地图时，需要加入创建好的人物，这个时候需要将下拉框的名字和文件中的名字对应，返回对应的人物对象。(MapListener)
	public Characters loadcharacter(String string, ArrayList<Characters> characterArrayList) throws IOException{


		for(Characters characters2:characterArrayList){
			if(characters2.getName().equals(string)){
				characters = characters2;
				break;
			}
		}
		return characters;
	}
	
	/**
	 * read all characters which are created
	 * @return ArrayList<Characters> 
	 * @throws IOException  IOException
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Characters> readCharacter() throws IOException, ClassNotFoundException{
		
		ArrayList<Characters> arrayList = new ArrayList<Characters>();
		File input = new File("ARGS/file/Characters.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
        arrayList = (ArrayList<Characters>) objectInputStream.readObject();
        objectInputStream.close();
        
        return arrayList;
	}
	
	
	/**
	 * display all the items in the backpack of Player
	 * @param characterArrayList
	 * @param character 
	 * @return ArrayList<Items>
	 * @throws IOException
	 */
	//显示主界面的backpack items
	public ArrayList<Items> readBackpack(ArrayList<Characters> characterArrayList, Characters character) throws IOException{
		
		for(Characters characters: characterArrayList){
			if(characters.equals(character)){
				backpack = characters.getBackpack();
				break;
			}
		}
		return backpack;
		

		
		
	}

}
