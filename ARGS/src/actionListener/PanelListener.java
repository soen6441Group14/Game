package actionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Strategy.Strategy;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Ground;
import objects.Items;
import objects.Entry;
import play.RunningController;

import javax.swing.JOptionPane;

/**
 * This is the class that listen to the panel in the map frame
 *  @author grey
 *	@version 2.0	
 *
 */

public class PanelListener implements KeyListener,Strategy{

	Map mapFrame;
	public Cells[][] map;
	int numRows,numCols;
	int rowOfEntry;
	int columnOfEntry;
	int playingIndex;
	int numberMap;
	int xHero;
	int yHero;
	Characters hero = null;
	

	public Characters getHero() {
		return hero;
	}

	public int getxHero() {
		return xHero;
	}

	public int getyHero() {
		return yHero;
	}


	/**
	 * The constructor method to initialize the panelListener class
	 * @param map
	 * @param numberMap
	 */

	public PanelListener(Map map, int numberMap) {
		this.mapFrame = map;
		this.numberMap = numberMap;
		setListeningMatrix();
	}

	/**
	 * The method is used to set the cell[][] information, based on the listed map
	 */
	public void setListeningMatrix(){
		this.map = this.mapFrame.getMap();
		this.numRows = this.mapFrame.getNumRows();
		this.numCols = this.mapFrame.getNumCols();
		this.playingIndex = this.mapFrame.getPlayingIndex();
		getEntry();
	}
	/**
	 * override method of keyListener
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	/**
	 * override method of keyListener
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	/**
	 * override method of keyListener
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		 int[] position = getHeroLocation();
		 xHero = position[0];
		 yHero = position[1];
		 hero = map[xHero][yHero].getCharacters();


		 if(e.getKeyCode() == KeyEvent.VK_S){
		 	if(isTurn){
				boolean flag =  moveDown(xHero,yHero,hero);
				mapFrame.setMap(map, numRows, numCols);
				mapFrame.drawMap(2);
				mapFrame.drawInformation();
				if(flag)
					steps--;
			}
		 }
		 else if (e.getKeyCode() == KeyEvent.VK_W){
		 	if(isTurn){
				boolean flag =  moveUp(xHero,yHero,hero);
				mapFrame.setMap(map, numRows, numCols);
				mapFrame.drawMap(2);
				mapFrame.drawInformation();
				if(flag)
					steps--;
			}
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_A){
		 	if(isTurn){
				boolean flag =  moveLeft(xHero,yHero,hero);
				mapFrame.setMap(map, numRows, numCols);
				mapFrame.drawMap(2);
				mapFrame.drawInformation();
				if(flag)
					steps--;
			}
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_D){
		 	if(isTurn){
				boolean flag =  moveRight(xHero,yHero,hero);
				mapFrame.setMap(map, numRows, numCols);
				mapFrame.drawMap(2);
				mapFrame.drawInformation();
				if(flag)
					steps--;
			}
		 }

		 if(steps>0)
		 	System.out.println("[ User player ] you have "+steps+" steps left");
		 else{
		 	isTurn=false;
		 	System.out.println("[ User player ] you have finished your turn, other character turn");
		 	boolean isRunning=RunningController.obtainRunningController().getTimerStatus();
		 	if(!isRunning){
				RunningController.obtainRunningController().startRun();
				AttackListener.setValid(false);
			}
		 }


	}

	/**
	 * The method is used to move right
	 * @param xHero the X position of hero
	 * @param yHero the Y position of hero
	 * @param hero	the character object of hero
	 * @return  true of false
	 */
	public boolean moveRight(int xHero, int yHero, Characters hero) {

		boolean flag = false;

		if(yHero+1<numCols){
			 if(map[xHero][yHero+1].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.ENTRY){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.CHEST){
				 Items item = map[xHero][yHero+1].getItems();
				 //lootItem(item,hero);
				 hero.lootItem(item);
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.MONSTER){
				 Characters target=map[xHero][yHero+1].getCharacters();
				 if(target.getOrient() == Orientation.FRIENDLY)
					 //interactWithFriendly(target,hero);
				 	hero.interactWithFriendly(target);
				 else{
				 	if(hero.attackOrLootDead(target)){}
				 	else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
						mapFrame.updateCharacterList();
					}
				 }
			}
			 else if(map[xHero][yHero+1].getTileType() == TileType.EXIT){
				 exitFromMap(hero);
			 }
			recoverTheEntry(xHero,yHero);
			 flag = true;
		}
		return flag;
	}

	/**
	 * The method is used to move left
	 * @param xHero the X position of hero
	 * @param yHero the Y position of hero
	 * @param hero	the character object of hero
	 * @return  true of false
	 */
	public boolean moveLeft(int xHero, int yHero, Characters hero) {
		boolean flag = false;
		if(yHero-1>=0) {
			if (map[xHero][yHero - 1].getTileType() == TileType.GROUND) {
				map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				map[xHero][yHero - 1] = new Cells(TileType.HERO, numRows, numCols, hero);
			} else if (map[xHero][yHero - 1].getTileType() == TileType.ENTRY) {
				map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				map[xHero][yHero - 1] = new Cells(TileType.HERO, numRows, numCols, hero);
			} else if (map[xHero][yHero - 1].getTileType() == TileType.WALL) {
			} else if (map[xHero][yHero - 1].getTileType() == TileType.CHEST) {
				Items item = map[xHero][yHero - 1].getItems();
				//lootItem(item,hero);
				hero.lootItem(item);
				map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				map[xHero][yHero - 1] = new Cells(TileType.HERO, numRows, numCols, hero);
			} else if (map[xHero][yHero - 1].getTileType() == TileType.MONSTER) {
				Characters target = map[xHero][yHero - 1].getCharacters();
				if (target.getOrient() == Orientation.FRIENDLY)
					//interactWithFriendly(target,hero);
					hero.interactWithFriendly(target);
				else{
					if(hero.attackOrLootDead(target)){}
					else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero][yHero - 1] = new Cells(TileType.HERO, numRows, numCols, hero);
						mapFrame.updateCharacterList();
					}
				}
			} else if (map[xHero][yHero - 1].getTileType() == TileType.EXIT) {
				exitFromMap(hero);
			}
			recoverTheEntry(xHero,yHero);
			flag = true;
		}
		return flag;
	}

	/**
	 * The method is used to move up
	 * @param xHero the X position of hero
	 * @param yHero the Y position of hero
	 * @param hero	the character object of hero
	 * @return  true of false
	 */
	public boolean moveUp(int xHero, int yHero, Characters hero) {
		boolean flag = false;

		 if(xHero-1>=0){
			 if(map[xHero-1][yHero].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.ENTRY){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.CHEST){
				 Items item = map[xHero-1][yHero].getItems();
				 //lootItem(item,hero);
				 hero.lootItem(item);
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.MONSTER){
				 Characters target=map[xHero-1][yHero].getCharacters();
				 if(target.getOrient() == Orientation.FRIENDLY)
					// interactWithFriendly(target,hero);
				 	hero.interactWithFriendly(target);
				 else{
				 	if(hero.attackOrLootDead(target)){}
				 	else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
						mapFrame.updateCharacterList();
					}
				 }
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.EXIT){
				 exitFromMap(hero);
			 }
			 recoverTheEntry(xHero,yHero);
			 flag = true;
		 }
		return flag;
	}

	/**
	 * The method is used to move down
	 * @param xHero the X position of hero
	 * @param yHero the Y position of hero
	 * @param hero	the character object of hero
	 * @return  true of false
	 */
	public boolean moveDown(int xHero, int yHero, Characters hero) {
		boolean flag = false;

		if(xHero+1<numRows){
			 if(map[xHero+1][yHero].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.ENTRY){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.CHEST){
				 Items item = map[xHero+1][yHero].getItems();
				 //lootItem(item,hero);
				 hero.lootItem(item);
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.MONSTER){
				 Characters target=map[xHero+1][yHero].getCharacters();
				 if(target.getOrient() == Orientation.FRIENDLY)
					 //interactWithFriendly(target,hero);
				 	hero.interactWithFriendly(target);
				 else{
				 	if(hero.attackOrLootDead(target)){}
				 	else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
						mapFrame.updateCharacterList();
					}
				 }
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.EXIT){
				 exitFromMap(hero);//TODO:这是个需要考虑的问题
			 }
			 recoverTheEntry(xHero,yHero);
			 flag = true;
		 }
		return flag;
	}


	/**
	 * get the location of hero in the map
	 * @return the X and Y in the int[]
	 */
	
	public int[] getHeroLocation(){

		int[] position = new int[2];

		 for(int i=0;i<numRows;i++)
			 for(int j =0; j<numCols;j++){
				 if(map[i][j].getTileType() == TileType.HERO){
					position[0] = i;
					position[1] = j;
					 break;
				 }
			 }
		return position;
	}

	/**
	 * The method is used to get the entry of the playing map
	 */
	public void getEntry() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (map[i][j].getTileType() == TileType.ENTRY) {
					rowOfEntry = i;
					columnOfEntry = j;
					break;
				}
			}
		}
	}

	/**
	 * The method is to recover the original entry after character standing on the entry
	 * @param currentRow    row of hero
	 * @param currentColumn column of hero
	 */
	public void recoverTheEntry(int currentRow, int currentColumn){
		if(currentRow==rowOfEntry && currentColumn==columnOfEntry)
			map[rowOfEntry][columnOfEntry]=new Cells(TileType.ENTRY, numRows, numCols, new Entry(TileType.ENTRY));
	}


	/**
	 * The method is to exit from the exit of the map
	 * If the objective"kill all hostile monsters" is not completed, no map change, prompt information
	 * @param hero the character object of hero
	 */
	
	public void exitFromMap(Characters hero){
		if(!checkCompleteObjective())
			JOptionPane.showMessageDialog(null, "you need to kill all hostile monsters", "Prompt", JOptionPane.INFORMATION_MESSAGE);
		else {
			int level = hero.getLevel();
			hero.setLevel(level+1);
			System.out.println("map index"+playingIndex);
			if (playingIndex >= numberMap) {
				JOptionPane.showMessageDialog(null, "you successfully pass the campaign", "Prompt", JOptionPane.INFORMATION_MESSAGE);

//				mapFrame.panelContainer.requestFocus(false);
//				mapFrame.removePanelContainer();
				mapFrame.panel.setVisible(false);

//				System.out.println("playingIndex: "+playingIndex);
//				System.out.println("numberMap: "+numberMap);

			} else {
				mapFrame.changeMap();
				setListeningMatrix();
				mapFrame.showOnMap(); //TODO:hero locateion定位错误的原因是changemap先
			}
		}

	}

	/**
	 * The method is to check whether the objective is completed or not
	 * if the objective is completed, set the objective as true
	 */
	public boolean checkCompleteObjective(){
		int count=0;
		//count the hostile monsters on the map
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (map[i][j].getTileType() == TileType.MONSTER){
					Characters monster=map[i][j].getCharacters();
					if(monster.getOrient()==Orientation.HOSTILE)
						count++;
				}
			}
		}
		//check
		if(count==0)
			return true;
		else
			return false;
	}

	/*the strategy of human player*/

	private int steps;
	private boolean isTurn=false;

	@Override
	public void execute() {
		//stop the auto-timer to let user input
		RunningController.obtainRunningController().stopRun();
		System.out.println("[ User play ] It is your turn - you can operate");
		AttackListener.setValid(true);
		isTurn=true;
		steps=3;
	}
}
