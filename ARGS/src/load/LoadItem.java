package load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import objects.Items;
/**
 * LoadItem class contains methods which return Items or ArrayList<Items>
 * @author grey
 * @version 1.0
 */
public class LoadItem {
	Items items;
	/**
	 * when we create a character, we need to choose items from file to equip the character
	 * @param string name
	 * @param itemArrayList ArrayList<Items>
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
	 * @return ArrayList<Items>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	//显示主界面的下拉框中的物品列表
	@SuppressWarnings("unchecked")
	public ArrayList<Items> readItem() throws IOException, ClassNotFoundException{
		
		ArrayList<Items> arrayList = new ArrayList<Items>();
		File input = new File("ARGS/file/Items.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(input));
        arrayList = (ArrayList<Items>) objectInputStream.readObject();
        objectInputStream.close();
        
        return arrayList;

		
	}

}
