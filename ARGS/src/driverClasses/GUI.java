package driverClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Controller.MyActionListener;
import enumClasses.tileType;
import mainClasses.cell;

/**
 * @author SNaKeRUBIN
 */
public class GUI {

    public void makeGUI(cell[][] map) {
        int x = map.length;
        int y = map[0].length;

        JButton[][] matrix = new JButton[x][y];

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                JButton b = new JButton();
                b.putClientProperty("row", i);
                b.putClientProperty("column", j);
                b.setText("(" + b.getClientProperty("row") + ", " + b.getClientProperty("column") + ")");
                b.setMargin(buttonMargin);
                b.addActionListener(new MyActionListener());
                b.setIcon(null);

                b.setBackground(Color.LIGHT_GRAY);
                matrix[i][j] = b;
            }
        }

        JPanel gui = new JPanel(new BorderLayout(3, 3));
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel board = new JPanel(new GridLayout(0, y));
        board.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.WHITE)
        ));
        board.setBackground(Color.black);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board.add(matrix[i][j]);
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cell temp = map[i][j];

                switch (temp.type) {

                    case EMPTY:
                        matrix[i][j].setBackground(Color.LIGHT_GRAY);
                        break;
                    case CHARCTER:
                        matrix[i][j].setBackground(Color.GREEN);
                        break;
                    case WALL:
                        matrix[i][j].setBackground(Color.RED);
                        break;
                    case CHEST:
                        matrix[i][j].setBackground(Color.yellow);
                        break;
                }
            }
        }

        gui.add(new JLabel("Heading"), BorderLayout.PAGE_START);
        gui.add(board, BorderLayout.CENTER);
        gui.add(new JButton(), BorderLayout.LINE_END);
        JFrame frame = new JFrame("Demo 1");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gui);
        frame.setMinimumSize(new Dimension(300, 300));

        frame.pack();

        frame.setVisible(true);
    }

    public static void main(String[] args) {

        cell[][] map = new cell[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new cell();

                if (i == j) {
                    map[i][j].type = tileType.CHEST;
                }
            }
        }

        GUI gui = new GUI();
        gui.makeGUI(map);
    }
}
