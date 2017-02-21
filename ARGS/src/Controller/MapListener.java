package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class MapListener implements ActionListener{
//	public Map map;
//	int[][] newMap;
//	int rows;
//	int cols;
//	public MapListener(Map map,int rows,int cols) {
//		this.map = map;
//		this.rows = rows;
//		this.cols = cols;
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		jButton.addKeyListener(new KeyAdapter() {
			 
//			public void keyPressed(KeyEvent e){
//				if(e.getKeyCode()==KeyEvent.VK_0){
//					int x = (int) jButton.getClientProperty("Rows");
//					int y = (int) jButton.getClientProperty("Cols");
//					newMap = map.getMap();
//					newMap[x][y] =0;
//					map.setMap(newMap);
//					map.drawMap(2);
//					}
//			else if(e.getKeyCode()==KeyEvent.VK_1){
//				int x = (int) jButton.getClientProperty("Rows");
//				int y = (int) jButton.getClientProperty("Cols");
//				newMap = map.getMap();
//				newMap[x][y] =1;
//				map.setMap(newMap);
//				map.drawMap(2);
//				}
//			else if(e.getKeyCode()==KeyEvent.VK_2)
//			{
//				int x = (int) jButton.getClientProperty("Rows");
//				int y = (int) jButton.getClientProperty("Cols");
//				newMap = map.getMap();
//				newMap[x][y] =2;
//				map.setMap(newMap);
//				map.drawMap(2);
//			}
//			else if(e.getKeyCode()==KeyEvent.VK_3)
//			{
//				int x = (int) jButton.getClientProperty("Rows");
//				int y = (int) jButton.getClientProperty("Cols");
//				newMap = map.getMap();
//				newMap[x][y] =3;
//				map.setMap(newMap);
//				map.drawMap(2);
//			}
//			else if(e.getKeyCode()==KeyEvent.VK_4)
//			{
//				int x = (int) jButton.getClientProperty("Rows");
//				int y = (int) jButton.getClientProperty("Cols");
//				newMap = map.getMap();
//				newMap[x][y] =4;
//				map.setMap(newMap);
//				map.drawMap(2);
//			}
//			}
			});
	
	}

	
}


