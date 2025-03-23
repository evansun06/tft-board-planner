package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Planner;

// This object constructs a board setup prompt.
public class BoardSetUpPopUp extends PopupInternalFrame implements ActionListener {
    private MainMenuGUI main;
    private JTextField textField;
    private JButton confirm;
    

    // EFFECT: Make a popupframe with the dimensions of the superclass
    public BoardSetUpPopUp(MainMenuGUI main) {
        super("Board Setup");
        this.main = main;
        addComponents();

    }

    // EFFECT: Add nessecary labels and textfield.
    private void addComponents() {
        JLabel question = new JLabel();
        question.setBounds(125, 30, 250, 30);
        question.setText("Give your board a name");

        textField = new JTextField();
        textField.setBounds(50,60,400,30);

        confirm = new JButton("Confirm");
        confirm.setBounds(125, 80, 250, 30);
        confirm.addActionListener(this);
        this.add(confirm);
        this.add(textField);
        this.add(question);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.confirm) {
            Planner p = main.getPlanner();
            p.addBoard(textField.getText());
            main.mainToBoardSwitch(p.getBoard(textField.getText()));
            main.setIsMakingBoard(false); 
            this.dispose();
        }
    }

    
}