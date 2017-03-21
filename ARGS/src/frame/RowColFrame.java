package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * This class is used to show the RowColFrame to change rows and columns.
 * In this way, users can create a map they want
 * 
 * @author grey
 * @version 2.0
 */
public class RowColFrame {
	
	private JTextField jTextRows = new JTextField();
	private JTextField jTextColumns = new JTextField();
	public Map map;
	
	/**
	 * This method is used to transmit the modified rows and columns to the main frame
	 * @param map  The main frame of map
	 * @param jFrame2  main frame
	 */
	
	public RowColFrame(Map map, JFrame jFrame2)
	{
		JFrame jFrame = new JFrame("Rows and Columns");
		JButton jButton = new JButton("Comfirm");
		JLabel jLabelRows = new JLabel("Input a row(<=20)");
		JLabel jLabelColumns = new JLabel("Input a column(<=20)");
		
		jTextRows.setSize(new Dimension(100, 30));
		jTextRows.setColumns(10);
		jTextColumns.setSize(new Dimension(100, 30));
		jTextColumns.setColumns(10);
		
		
		jFrame.setLayout(new FlowLayout());
		jFrame.add(jLabelRows);
		jFrame.add(jTextRows);
		jFrame.add(jLabelColumns);
		jFrame.add(jTextColumns);
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
		
		// draw the map according to different rows and columns
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				map.setNumRows(Integer.parseInt(jTextRows.getText()));
				map.setNumCols(Integer.parseInt(jTextColumns.getText()));
				map.drawMap(1);
				jFrame2.setEnabled(true);
				jFrame.dispose();
				
			}
		});

	
	}

}
