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



public class PanelListener implements KeyListener {
	Map mapFrame;
	Cells[][] map;
	int numRows,numCols;
	int rowOfEntry;
	int columnOfEntry;
	
	public PanelListener(Map map) {
		this.mapFrame = map;
		this.map = map.getMap();
		this.numRows = map.getNumRows();
		this.numCols = map.getNumCols();
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
		 
		 int[] position = getLocation();
		 int xHero = position[0];
		 int yHero = position[1];
		 hero = map[xHero][yHero].getCharacters();
		 recoverTheEntry(xHero,yHero);
		 
		 if(e.getKeyCode() == KeyEvent.VK_S){
			boolean flag =  moveDown(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
			 mapFrame.drawMap(2);
		 }
		 else if (e.getKeyCode() == KeyEvent.VK_W){
			 boolean flag =  moveUp(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
			 mapFrame.drawMap(2);
			 
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_A){
			 boolean flag =  moveLeft(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
			 mapFrame.drawMap(2);
			 
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_D){
			 boolean flag =  moveRight(xHero,yHero,hero);
			 mapFrame.setMap(map, numRows, numCols);
			 mapFrame.drawMap(2);
			 
		 }
	}


	private boolean moveRight(int xHero, int yHero, Characters hero) {
		boolean flag = false;
		if(yHero+1<numCols){
			 if(map[xHero][yHero+1].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.CHEST){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
				 Items item = map[xHero][yHero+1].getItems();
				 if(hero.getBackpack().size()<=10)
				 {
					 for(Items items :hero.getBackpack())
						 if(items.getName().equals("EMPTY")){
							 int index = hero.getBackpack().indexOf(items);
							 hero.getBackpack().set(index, item);
							 break;
						 }
				 }
				 //需要重新更新玩家背包
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.MONSTER){
				 if(map[xHero][yHero+1].getCharacters().getOrient() == Orientation.FRIENDLY){
					 
				 }
				 else{
					 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
					 map[xHero][yHero+1].getCharacters().setHitpoints(0);
					 //重新draw information
					 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
					 
				 }
			 }
			 else if(map[xHero][yHero+1].getTileType() == TileType.EXIT){
				 //完成object之后才能离开
				 int level = hero.getLevel();
				 hero.setLevel(level+1);
				//重新draw information
			 }
			 flag = true;
		 }
		return flag;
	}

	private boolean moveLeft(int xHero, int yHero, Characters hero) {
		boolean flag = false;
		if(yHero-1>=0){
			 if(map[xHero][yHero-1].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero-1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero-1].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero][yHero-1].getTileType() == TileType.CHEST){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero][yHero-1] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero][yHero-1].getTileType() == TileType.MONSTER){
				 if(map[xHero][yHero-1].getCharacters().getOrient() == Orientation.FRIENDLY){
					 
				 }
				 else{
					 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
					 map[xHero][yHero-1].getCharacters().setHitpoints(0);
					 //重新draw information
					 map[xHero][yHero-1] = new Cells(TileType.HERO, numRows, numCols, hero);
				 }
			 }
			 else if(map[xHero][yHero-1].getTileType() == TileType.EXIT){
				 
			 }
			 flag = true;
		 }
		return flag;
	}

	private boolean moveUp(int xHero, int yHero, Characters hero) {
		boolean flag = false;
		 if(xHero-1>=0){
			 if(map[xHero-1][yHero].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.CHEST){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.MONSTER){
				 if(map[xHero-1][yHero].getCharacters().getOrient() == Orientation.FRIENDLY){
					 
				 }
				 else{
					 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
					 map[xHero-1][yHero].getCharacters().setHitpoints(0);
					 //重新draw information
					 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
				 }
			 }
			 else if(map[xHero-1][yHero].getTileType() == TileType.EXIT){
				 
			 }
			 flag = true;
		 }
		return flag;
	}

	
	private boolean moveDown(int xHero, int yHero, Characters hero) {
		boolean flag = false;
		if(xHero+1<numRows){
			 if(map[xHero+1][yHero].getTileType() == TileType.GROUND){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.WALL){
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.CHEST){
				 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
				 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.MONSTER){
				 if(map[xHero+1][yHero].getCharacters().getOrient() == Orientation.FRIENDLY){
					 
				 }
				 else{
					 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
					 map[xHero+1][yHero].getCharacters().setHitpoints(0);
					 //重新draw information
					 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
				 }
			 }
			 else if(map[xHero+1][yHero].getTileType() == TileType.EXIT){
				 
			 }
			 flag = true;
		 }
		return flag;
	}
	
	private int[] getLocation() {
		int[] position = new int[10];
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

	private void recoverTheEntry(int currentRow, int currentColumn){
		if(currentRow==rowOfEntry && currentColumn==columnOfEntry)
			map[rowOfEntry][columnOfEntry]=new Cells(TileType.ENTRY, numRows, numCols, new Entry(TileType.ENTRY));
	}

}
