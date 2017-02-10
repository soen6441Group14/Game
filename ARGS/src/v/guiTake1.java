package v;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
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

/**
 * @author SNaKeRUBIN
 */
public final class guiTake1 {

    public static void main(String[] args) {
        int x = 5, y = 5;
        JButton[][] matrix = new JButton[x][y];

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                JButton b = new JButton();
                b.setText("(" + i + ", " + j + ")");
                b.setMargin(buttonMargin);
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

        int[][] map = new int[x][y];
        map[3][3] = 2;
        map[3][4] = 2;
        map[0][0] = 1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int temp = map[i][j];

                switch (temp) {

                    case 0:
                        matrix[i][j].setBackground(Color.LIGHT_GRAY);
                        break;
                    case 1:
                        matrix[i][j].setBackground(Color.GREEN);
                        break;
                    case 2:
                        matrix[i][j].setBackground(Color.RED);
                        break;
                }
            }
        }
        // map[9][0] = 0;
        //  map[8][1] = 1;
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
}
