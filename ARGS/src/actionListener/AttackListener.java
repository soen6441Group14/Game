package actionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import frame.Map;
import objects.Characters;

public class AttackListener extends MouseAdapter {
	Map map;
	int x,y;
	Characters targetCharacter = null;
	
	public AttackListener(Map map) {
		this.map = map;
	}

	public void mouseClicked(MouseEvent e){
		JButton jButton = (JButton) e.getSource();
		
		if(e.isMetaDown()){//检测鼠标右键单击
			x= (int) jButton.getClientProperty("Rows");
			y = (int) jButton.getClientProperty("Cols");
			targetCharacter = map.getMap()[x][y].getCharacters();
			System.out.println(targetCharacter.getName());
			map.targetMonster = targetCharacter;
		}
}
	

}

