package frame;

import javax.swing.*;
import java.awt.*;

/**
 * The class is to build the logging window in which the information about attacking will be show
 */
public class LoggingWindow extends Frame{

    //singleton
    private LoggingWindow loggingWindow;

    private JPanel jPanel;
    private JTextField attackingCharacter;
    private JTextField attackedCharacter;
    private JTextField attackRoll;
    private JTextField attackBonus;
    private JTextField strModifier;
    private JTextField attackTotal;

    private JTextField attackedArmor;
    private JTextField hitOrNot;
    private JTextField roll1D8;
    private JTextField damageModifier;
    private JTextField damageTotoal;

    private final String attackStr=" attack : ";
    private final String attackRollStr="attack roll (1D20) : ";
    private final String ABStr="attack bonus : ";
    private final String StrMoStr="strength modifier : ";
    private final String attackTotalStr="Total : ";

}
