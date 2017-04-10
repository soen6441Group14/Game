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
import javax.swing.JTextField;

import load.LoadMap;
import objects.Cells;
import objects.Matrix;
import save.SaveMap;
/**
 *  SaveMapFrame is used to save a map
 * @author grey
 * @version 2.0
 */
public class SaveMapFrame {
	
	/**
	 * constructor method
	 * @param map2 		Map object
	 * @param map		Cells[][]
	 * @param jFrame2   main frame
	 * @param allMaps   ArrayList<Matrix>
	 */
	public SaveMapFrame(Map map2, Cells[][] map, JFrame jFrame2, ArrayList<Matrix> allMaps){
		JFrame jFrame = new JFrame("Save the map");
		JButton jButton = new JButton("Save");
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
		
		// save the map
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Matrix oldMap = null;
				
				try {
					oldMap = new LoadMap().loadMap2(allMaps, mapName.getText());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				Matrix matrix = new Matrix(map, mapName.getText());
				
				if(oldMap == null){
					allMaps.add(matrix);
				}
				else{
					int index = allMaps.indexOf(oldMap);
					allMaps.set(index, matrix);
//					allMaps.remove(oldMap);
//					allMaps.add(matrix);
				}
				
				
				
				try {
					new SaveMap().saveMap(allMaps);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				map2.drawMapBox();
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
			}
		});
	}

	

}
