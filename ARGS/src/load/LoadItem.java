package load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import objects.Items;
/**
 * LoadItem class contains methods which return Items or ArrayList of items
 * @author grey
 * @version 2.0
 */
public class LoadItem {
	Items items;
	/**
	 * when we create a character, we need to choose items from file to equip the character
	 * @param string name
	 * @param itemArrayList ArrayList of items
	 * @return Items object
	 */
	//在创建人物时，多选框选择一个item，则返回对应姓名的名字和值的字符串。读取人物时也需要
	public Items loadItem(String string, ArrayList<Items> itemArrayList){
		
		for(Items items2: itemArrayList){
			if(items2.getName().equals(string)){
				items = items2;
				break;
			}
		}
		return items;

	}
	/**
	 * display all the items which are created
	 * @return ArrayList of items
	 * @throws IOException no file exception
	 * @throws ClassNotFoundException not class found
	 */
	//显示主界面的下拉框中的物品列表
	@SuppressWarnings("unchecked")
	public ArrayList<Items> readItem() throws IOException, ClassNotFoundException{
		
		ArrayList<Items> arrayList = new ArrayList<Items>();
		File input = new File("file/Items.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
        arrayList = (ArrayList<Items>) objectInputStream.readObject();
        objectInputStream.close();
        
        return arrayList;

		
	}

}
