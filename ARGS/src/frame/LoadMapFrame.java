package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import load.LoadMap;
import objects.Cells;
import objects.Matrix;
/**
 * LoadMapFrame is used to load an existed map
 * @author grey
 * @version 2.0
 */
public class LoadMapFrame {
	
	int numRows;
	int numCols;
	/**
	 * constructor method
	 * @param map	Map object
	 * @param jFrame2  main frame
	 * @param allMaps  ArrayList<Matrix>
	 */
	public LoadMapFrame(Map map, JFrame jFrame2, ArrayList<Matrix> allMaps){
		JFrame jFrame = new JFrame("Load the map");
		JButton jButton = new JButton("Load");
		JLabel jLabelName = new JLabel("Input a map name");
		JTextField mapName = new JTextField();
		
		mapName.setSize(new Dimension(50, 30));
		mapName.setColumns(8);
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(jLabelName);
		jFrame.add(mapName);
		jFrame.add(jButton);
		
		jFrame.setLocationRelativeTo(null);//put the screen in the center
		jFrame.setSize(new Dimension(300, 300));
		jFrame.setVisible(true);
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				jFrame.dispose();
				jFrame2.setEnabled(true);
			}
		});
		
		//load a map
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				try {
					Cells[][] cells = null;
					cells = new LoadMap().loadMap(allMaps,mapName.getText());
					
					// if the map did not exist, then give an alert
					if(cells == null)
						JOptionPane.showMessageDialog(null, "There is no such a map", "Alert", JOptionPane.ERROR_MESSAGE);
					
					else{
					numRows = cells[0][0].getX();
					numCols = cells[0][0].getY();
					map.setMap(cells,numRows,numCols);
					map.drawMap(2);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
			}
		});
	}

}
