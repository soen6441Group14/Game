package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author SNaKeRUBIN
 */
public class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        System.out.println(b.getClientProperty("row") + " " + b.getClientProperty("column"));
    }

}
