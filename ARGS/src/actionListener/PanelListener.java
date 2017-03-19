package actionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enumclass.Orientation;
import enumclass.TileType;
import frame.Map;
import objects.Cells;
import objects.Characters;
import objects.Ground;

public class PanelListener implements KeyListener {
	Map mapFrame;
	Cells[][] map;
	int numRows,numCols;
	
	public PanelListener(Map map) {
		this.mapFrame = map;
		this.map = map.getMap();
		this.numRows = map.getNumRows();
		this.numCols = map.getNumCols();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Characters hero = null;
		 
		 int[] position = getLocation();
		 int xHero = position[0];
		 int yHero = position[1];
		 hero = map[xHero][yHero].getCharacters();
		
		 
		 if(e.getKeyCode() == KeyEvent.VK_S){
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
						 map[xHero+1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
					 }
				 }
				 else if(map[xHero+1][yHero].getTileType() == TileType.EXIT){
					 
				 }
			 }
			 mapFrame.drawMap(2);
		 }
		 else if (e.getKeyCode() == KeyEvent.VK_W){
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
						 map[xHero-1][yHero] = new Cells(TileType.HERO, numRows, numCols, hero);
					 }
				 }
				 else if(map[xHero-1][yHero].getTileType() == TileType.EXIT){
					 
				 }
			 }
			 mapFrame.drawMap(2);
			 
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_A){
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
						 map[xHero][yHero-1] = new Cells(TileType.HERO, numRows, numCols, hero);
					 }
				 }
				 else if(map[xHero][yHero-1].getTileType() == TileType.EXIT){
					 
				 }
			 }
			 mapFrame.drawMap(2);
			 
		 }
		 else if(e.getKeyCode() == KeyEvent.VK_D){
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
				 }
				 else if(map[xHero][yHero+1].getTileType() == TileType.MONSTER){
					 if(map[xHero][yHero+1].getCharacters().getOrient() == Orientation.FRIENDLY){
						 
					 }
					 else{
						 map[xHero][yHero] = new Cells(TileType.GROUND, numRows, numCols, new Ground(TileType.GROUND));
						 map[xHero][yHero+1] = new Cells(TileType.HERO, numRows, numCols, hero);
					 }
				 }
				 else if(map[xHero][yHero+1].getTileType() == TileType.EXIT){
					 
				 }
			 }
			 mapFrame.drawMap(2);
			 
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
}
