package ui;

import javax.swing.*;

import persistance.JsonReader;
import model.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


// MainMenuGUI is the graphical interface that users are first introduced to
//
public class MainMenuGUI extends JFrame { 
    protected Planner planner;
    private JsonReader jsonReader = new JsonReader("data/userPersistance.json");
    private JDesktopPane desktopPane;



    // EFFECT: constructor that sets the size and attribuites of the Jframe.
    public MainMenuGUI() {
        super("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        desktopPane = new JDesktopPane();
        this.add(desktopPane);
        promptSessionRecovery();
        this.setVisible(true);

    }

    // EFFECTS: Prepares the labels and options of the main menue.
    public void setMainMenuComponents() {
        
    }

    // EFFECTS: Display a pop-up a tab that asks the user if they want to recover the previous session.
    //          Will not let user continue unless a descision is made.
    public void promptSessionRecovery() {
        JInternalFrame popup = new JInternalFrame("Session Recovery", true, false, false, false);
        popup.setSize(500,200);
        popup.setLayout(null);

        desktopPane.add(popup);
        
        popup.setResizable(false);
        JLabel prompt = new JLabel("Do you want to recover your previous session?");
        prompt.setBounds(100, 50,300,30);
        popup.add(prompt);

        addSessionRecoveryButtons(popup);
        //Dynamically Center
        popup.setLocation((this.getWidth() / 2 - popup.getWidth() / 2),
                (this.getHeight() / 2 - popup.getHeight() / 2));
        popup.setVisible(true);
    }
    
    //REQUIRES: The exact JInternalFrame used for session recovery needs to be passed
    //EFFECTS: Creates and display buttons while handling their responses
    public void addSessionRecoveryButtons(JInternalFrame recoveryPopup) {
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        yes.setBounds(140, 100, 100, 30);
        no.setBounds(260, 100, 100, 30);
        recoveryPopup.add(yes);
        recoveryPopup.add(no);
        handleSessionRecoveryButtons(yes, no, recoveryPopup);
       
    }

    // EFFECT: Adds mouseListener functions for the Yes/No MouseListener to the RecoveryPopup JInternalFrame
    public void handleSessionRecoveryButtons(JButton yes, JButton no, JInternalFrame recoveryPopup) {
        yes.addMouseListener(new MouseAdapter() {
            @Override
            // EFFECT: reads JSON to this.planner state and disposes of the prompt popup
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    loadJson();
                    recoveryPopup.dispose();
                }
            }
        });   

        no.addMouseListener(new MouseAdapter() {
            @Override
            // EFFECT: creates a new Planner with no boards and disposes of the prompt popup
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    planner = new Planner();
                    recoveryPopup.dispose();
                }
            }
        });
    }

    //EFFECT: Load JSON to this.planner 
    //        If IOException print stack trace.
    public void loadJson() {
        try {
            this.planner = jsonReader.plannerJsonToObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    public static void main(String[] args) {
        new MainMenuGUI();
    }

}
