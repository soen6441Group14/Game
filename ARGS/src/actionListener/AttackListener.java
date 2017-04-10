package actionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import enumclass.TileType;
import frame.Map;
import objects.Characters;

/**
 * This class implements the range factor of determining whether 
 * the character can attack or not
 * @author Tann chen, grey
 * @version 3.0
 */

public class AttackListener extends MouseAdapter {
	// only when the userPlay strategy executes, it is valid
	public static boolean valid;
	public static int attackTime;
	private Characters userPlayer;

	public Map mapFrame;
	public int row,column;
	public Characters targetCharacter = null;

	/**
	 * constructor
	 * @param map  map class 
	 * @param hero hero object
	 */
	public AttackListener(Map map,Characters hero){
		this.mapFrame = map;
		this.userPlayer=hero;
	}

	/**
	 * make sure that the target clicked on is valid
	 * @param mouseListenerValid  valid or not
	 */
	public static void setValid(boolean mouseListenerValid){
		valid=mouseListenerValid;
		attackTime=1;
	}

	/**
	 * Th method is used to mouse click
	 * @param e mouseEvent
	 */
	public void mouseClicked(MouseEvent e){
		if(valid){ //check validation
			JButton jButton = (JButton) e.getSource();
			//check if right button
			if(e.isMetaDown()){
				//check the range
				if(!(jButton.getBorder() instanceof EtchedBorder)){
					System.out.println("[ Warning ] You target is outside the range that you can attack");
				}
				else{
					row= (int) jButton.getClientProperty("Rows");
					column = (int) jButton.getClientProperty("Cols");
					if(mapFrame.getMap()[row][column].getTileType()!=TileType.MONSTER){
						System.out.println("[ Warning ] the target is not character");
					}
					else{
						targetCharacter = mapFrame.getMap()[row][column].getCharacters();
						this.userPlayer.clickAttack(targetCharacter);
						attackTime--;
					}
				}
			}
		}
		if(attackTime==0)
			valid=false;
	}

}

