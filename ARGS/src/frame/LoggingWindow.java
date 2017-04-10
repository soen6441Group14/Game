package frame;

import objects.Characters;

import javax.swing.*;
import java.awt.*;

/**
 * The class is to build the logging window in which the information about attacking will be show
 */
public class LoggingWindow extends Frame{

    //singleton
    private static LoggingWindow loggingWindow;

    private JPanel jPanel;
    private JTextField attackingCharacter;
    private JTextField weaponType;
    private JTextField attackRoll;
    private JTextField attackBonus;
    private JTextField abilityModifier;
    private JTextField attackTotal;

    private JTextField attackedArmor;
    private JTextField hitOrNot;
    private JTextField roll1D8;
    private JTextField damageModifier;
    private JTextField damageTotal;

    private final String attackStr="- attacks -";
    private final String weaponTypeStr="Weapon : ";
    private final String attackRollStr="attack roll (1D20) : ";
    private final String ABStr="attack bonus : ";
    private final String abilityMoStr=" modifier : ";
    private final String attackTotalStr="Total : ";
    private final String attackedArmorStr="attacked's armor class";
    private final String roll1D8Str="Damage - roll(1D8) : ";
    private final String damageModifierStr="Strength modifier :";
    private final String damageToTalStr="Damage Total :";

    /**
     * Constructor
     */
    private LoggingWindow(){

        this.setSize(200,300);
        this.setName("Logging Window");

        jPanel=new JPanel(new GridLayout(15,1));
        attackingCharacter=new JTextField(attackStr);
        attackingCharacter.setEditable(false);
        jPanel.add(attackingCharacter);


        attackRoll=new JTextField(attackRollStr);
        attackRoll.setEditable(false);
        jPanel.add(attackRoll);

        weaponType=new JTextField(weaponTypeStr);
        weaponType.setEditable(false);
        jPanel.add(weaponType);

        attackBonus=new JTextField(ABStr);
        attackBonus.setEditable(false);
        jPanel.add(attackBonus);

        abilityModifier=new JTextField(abilityMoStr);
        abilityModifier.setEditable(false);
        jPanel.add(abilityModifier);

        attackTotal=new JTextField(attackTotalStr);
        attackTotal.setEditable(false);
        jPanel.add(attackTotal);

        attackedArmor=new JTextField(attackedArmorStr);
        attackedArmor.setEditable(false);
        jPanel.add(attackedArmor);

        hitOrNot=new JTextField();
        hitOrNot.setEditable(false);
        jPanel.add(hitOrNot);

        roll1D8=new JTextField(roll1D8Str);
        roll1D8.setEditable(false);
        jPanel.add(roll1D8);

        damageModifier=new JTextField(damageModifierStr);
        damageModifier.setEditable(false);
        jPanel.add(damageModifier);

        damageTotal=new JTextField(damageToTalStr);
        damageTotal.setEditable(false);
        jPanel.add(damageTotal);

        this.add(jPanel);
    }

    /**
     * the getter of logging window
     * @return LoggingWindow
     */
    public static LoggingWindow getLoggingWindow(){
        if(loggingWindow==null)
            loggingWindow=new LoggingWindow();
        return loggingWindow;
    }

    /**
     * the method is used to update the information of logging window
     * @param attacked the character attacked
     * @param attacking the character who attacks others
     * @param weapType true if the melee ,false if the ranged weapon
     * @param d20 the random number of d20
     * @param d8 the random number of d8
     */
    public void updateInfo(Characters attacking, Characters attacked,boolean weapType,int d20 , int d8){
        attackingCharacter.setText(attacking.getName()+attackStr+attacked.getName());
        if(weapType){
            weaponType.setText(weaponTypeStr+" Melee");
            abilityModifier.setText("Strength "+abilityMoStr+attacking.getModStr());
        }
        else{
            weaponType.setText(weaponTypeStr+"Ranged weapon");
            abilityModifier.setText("Dexterity"+abilityMoStr+attacking.getModDex());
        }


        attackRoll.setText(attackRollStr+d20);
        attackBonus.setText(ABStr+attacking.getAttackBonus());
        attackTotal.setText(attackTotalStr+(d20+attacking.getAttackBonus()+attacking.getModStr()));
        attackedArmor.setText(attackedArmorStr+attacked.getArmorClass());
        if((d20+attacking.getAttackBonus()+attacking.getModStr())>=attacked.getArmorClass()){
            hitOrNot.setText("Hit!!!");
            roll1D8.setText(roll1D8Str+d8);
            damageModifier.setText(damageModifierStr+attacking.getModStr());
            damageTotal.setText(damageToTalStr+(d8+attacking.getModStr()));
        }

        else{
            hitOrNot.setText("Miss!");
            roll1D8.setText("");
            damageModifier.setText("");
            damageTotal.setText("");
        }
        jPanel.repaint();
    }





}
