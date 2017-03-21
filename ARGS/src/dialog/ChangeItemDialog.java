package dialog;

import objects.Items;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The class is to construct a dialog to change the items with friendly monsters
 */
public class ChangeItemDialog extends JDialog{

    private JPanel centerView;
    private JComboBox<String> jcomItemsList;
    private JButton yesButton;
    private JButton cancelButton;


    private String selectedItem;
    private ArrayList<String> itemsList;


    /**
     * constructor
     * to construct the view
     * to add actionListener
     */
    public ChangeItemDialog(ArrayList<String>itemsNameList) {

        this.itemsList=itemsNameList;

        this.setSize(250,250);
        this.setLocation(100,100);
        this.getContentPane().setLayout(new BorderLayout());

        //center view
        centerView =new JPanel();
        centerView.setLayout(new FlowLayout());
        centerView.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(centerView, BorderLayout.CENTER);

        //jcomCharacters
        jcomItemsList=new JComboBox<String>(obtainItemsNames());
        jcomItemsList.setSelectedIndex(-1);
        centerView.add(jcomItemsList);

        //yesButton
        yesButton=new JButton();
        yesButton.setText("Change");
        centerView.add(yesButton);

        //cancelButton
        cancelButton=new JButton();
        cancelButton.setText("Cancel");
        centerView.add(cancelButton);

        /* Action listeners*/

        yesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcomItemsList.getSelectedIndex()==-1)
                    return;
                selectedItem=(String)jcomItemsList.getSelectedItem();
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedItem="EMPTY";
                setVisible(false);
            }
        });


        /* other works*/

        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    /**
     * The method is used to get the selected item to change in PaneListener
     * @return "EMPTY" if no select
     */
    public String getSelectedName() {return selectedItem;}


    /**
     * The method is to change the ArrayList to String Array
     */
    private String[] obtainItemsNames(){
        String[] itemsNames=new String[itemsList.size()];
        for(int i=0; i<itemsList.size();i++){
            itemsNames[i]=itemsList.get(i);
        }
        return itemsNames;
    }

}
