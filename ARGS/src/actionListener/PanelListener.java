package actionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Ground;
import objects.Items;
import objects.Entry;
import javax.swing.JOptionPane;



public class PanelListener implements KeyListener {
	Map mapFrame;
	Cells[][] map;
	int numRows,numCols;
	int rowOfEntry;
	int columnOfEntry;
	int playingIndex;
	int numberMap;
	
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Characters hero = null;
		 
		 int[] position = getHeroLocation();
		 int xHero = position[0];
		 int yHero = position[1];
		 hero = map[xHero][yHero].getCharacters();

		 if(hero==null)
		 	System.out.println("system error: hero object missing");
		
		 
		 if(e.getKeyCode() == KeyEvent.VK_S){
			boolean flag =  moveDown(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
		//	 recoverTheEntry(xHero,yHero);
			 mapFrame.drawMap(2);
		 }
		 else if (e.getKeyCode() == KeyEvent.VK_W){
			 boolean flag =  moveUp(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
		//	 recoverTheEntry(xHero,yHero);
			 mapFrame.drawMap(2);
			 
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_A){
			 boolean flag =  moveLeft(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
		//	 recoverTheEntry(xHero,yHero);
			 mapFrame.drawMap(2);
			 
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_D){
			 boolean flag =  moveRight(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
		//	 recoverTheEntry(xHero,yHero);
			 mapFrame.drawMap(2);
			 
		 }
	}

	/**
	 * The method is used to move right
	 */
	private boolean moveRight(int xHero, int yHero, Characters hero) {
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
				 lootItem(item,hero);
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.MONSTER){
				 Characters target=map[xHero][yHero+1].getCharacters();
				 if(target.getOrient() == Orientation.FRIENDLY)
					 interactWithFriendly(target);
				 else{
				 	if(interactWithHostile(target,hero)){}
				 	else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
					}
				 }
			}
			 else if(map[xHero][yHero+1].getTileType() == TileType.EXIT){
				 exitFromMap(hero);
			 }
			 flag = true;
		}
		return flag;
	}

	/**
	 * The method is used to move left
	 */
	private boolean moveLeft(int xHero, int yHero, Characters hero) {
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
				lootItem(item,hero);
				map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				map[xHero][yHero - 1] = new Cells(TileType.HERO, numRows, numCols, hero);
			} else if (map[xHero][yHero - 1].getTileType() == TileType.MONSTER) {
				Characters target = map[xHero][yHero - 1].getCharacters();
				if (target.getOrient() == Orientation.FRIENDLY)
					interactWithFriendly(target);
				else{
					if(interactWithHostile(target,hero)){}
					else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero][yHero - 1] = new Cells(TileType.HERO, numRows, numCols, hero);
					}
				}
			} else if (map[xHero][yHero - 1].getTileType() == TileType.EXIT) {
				exitFromMap(hero);
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * The method is used to move up
	 */
	private boolean moveUp(int xHero, int yHero, Characters hero) {
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
				 lootItem(item,hero);
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.MONSTER){
				 Characters target=map[xHero-1][yHero].getCharacters();
				 if(target.getOrient() == Orientation.FRIENDLY)
					 interactWithFriendly(target);
				 else{
				 	if(interactWithHostile(target,hero)){}
				 	else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
					}
				 }
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.EXIT){
				 exitFromMap(hero);
			 }
			 flag = true;
		 }
		return flag;
	}

	/**
	 * The method is used to move down
	 */
	private boolean moveDown(int xHero, int yHero, Characters hero) {
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
				 lootItem(item,hero);
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.MONSTER){
				 Characters target=map[xHero+1][yHero].getCharacters();
				 if(target.getOrient() == Orientation.FRIENDLY)
					 interactWithFriendly(target);
				 else{
				 	if(interactWithHostile(target,hero)){}
				 	else{
						map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
					}
				 }

			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.EXIT){
				 exitFromMap(hero);
			 }

			 flag = true;
		 }
		return flag;
	}
	
	private int[] getHeroLocation() {
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
	private void getEntry() {
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
	 */
	private void recoverTheEntry(int currentRow, int currentColumn){
		if(currentRow==rowOfEntry && currentColumn==columnOfEntry)
			map[rowOfEntry][columnOfEntry]=new Cells(TileType.ENTRY, numRows, numCols, new Entry(TileType.ENTRY));
	}

	/**
	 * The method is used to interact with chest
	 */
	private void lootItem(Items item, Characters hero){
		int temp=-1;
		for(int i=0;i<10;i++){
			if(hero.getBackpack().get(i).getName().equals("EMPTY")) {
				temp = i;
				break;
			}
		}
		if(temp==-1){
			System.out.println("the backpack is full,old item will be replaced");
		}
		else{
			hero.getBackpack().set(temp,item);
		}
	}

	/**
	 * The method is used to interact with friendly monsters
	 * The interaction is changing the items with monsters
	 */
	private void interactWithFriendly(Characters friendly){
		//TODO:给他一件制定的，他还你一件随机的
	}

	/**
	 * The method is to interact with hostile monster
	 * the first interaction is killing the monster
	 * the second interaction is to loot its items
	 * @return true if the hostile is live, false if the hostile has dead
	 */
	private boolean interactWithHostile(Characters hostile,Characters hero){
		boolean live;
		if(hostile.getHitpoints()>0){
			live=true;
			hostile.setHitpoints(0);
		}
		else{
			live=false;
			//loot backpack
			for(int i=0; i<10; i++){
				if(hostile.getBackpack().get(i)!=null){
					lootItem(hostile.getBackpack().get(i),hero);
					hostile.getBackpack().set(i,null);
				}
			}
			//loot worn items
			for(int i=0; i<hostile.getInventory().size(); i++){
				if(!hostile.getInventory().get(i).getName().equals("EMPTY")){
					lootItem(hostile.getInventory().get(i),hero);
					hostile.getInventory().set(i,new Items("EMPTY",0));
				}
			}
		}

		return live;
	}

	/**
	 * The method is to ext from the exit of the map
	 */
	private void exitFromMap(Characters hero){
		int level = hero.getLevel();
		hero.setLevel(level+1);
		System.out.println("map index"+playingIndex);

		if(playingIndex>=numberMap){
			JOptionPane.showMessageDialog(null, "There is no map anymore", "Alert", JOptionPane.ERROR_MESSAGE);
			mapFrame.removePanelContainer();

		}
		else{
			mapFrame.changeMap();
			setListeningMatrix();
		}



	}



}
