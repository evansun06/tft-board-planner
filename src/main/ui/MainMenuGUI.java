package ui;

import javax.swing.*;

import persistance.JsonReader;
import model.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// MainMenuGUI is the graphical interface that users are first introduced to
//
public class MainMenuGUI implements ActionListener { 
    private JFrame mainMenuJFrame;
    private JLayeredPane mainMenuLayers;
    private Container contentPane;

    private JPanel optionPanel;
    private JPanel boardPanel;
    protected Planner planner;
    private JsonReader jsonReader = new JsonReader("data/userPersistance.json");
    private JInternalFrame popup;
    
    

    // EFFECT: constructor that sets the size and attribuites of the Jframe.
    public MainMenuGUI() {
        mainMenuJFrame = new JFrame();
        configureMainJFrame();
        setMainMenuComponents();
        mainMenuJFrame.setVisible(true);
        promptSessionRecovery();
        
    }
    //EFFECT: Configure MainJFrame with according dimensions
    public void configureMainJFrame() {
        mainMenuJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuJFrame.setSize(1000, 800);
        mainMenuJFrame.setLayout(null);
        mainMenuLayers = mainMenuJFrame.getLayeredPane();
        contentPane = mainMenuJFrame.getContentPane();
    }

    // REQUIRES: should only be called from MainMenuGUI constructor
    // EFFECTS: Prepares the labels and options of the main menu.
    private void setMainMenuComponents() {
        optionPanel = new JPanel();
        boardPanel = new JPanel();
        optionPanel.setBounds(0,0,200,800);
        boardPanel.setBounds(200,0,800,800);
        optionPanel.setBackground(Color.BLUE);
        boardPanel.setBackground(Color.GRAY);
        contentPane.add(optionPanel);
        contentPane.add(boardPanel);
        
    }


    // EFFECTS: Display a pop-up a tab that asks the user if they want to recover the previous session.
    //          Will not let user continue unless a descision is made.
    public void promptSessionRecovery() {
        // Center & Make Popup as JInternalFrame
        popup = new JInternalFrame("Session Recovery", true, false, false, false);
        popup.setSize(500,200);
        popup.setLayout(null);
        popup.setLocation((mainMenuJFrame.getWidth()/2 - popup.getWidth()/2),
                (mainMenuJFrame.getHeight()/2 - popup.getHeight()/2));
        popup.setResizable(false);

        //Make Label
        JLabel prompt = new JLabel("Do you want to recover your previous session?");
        prompt.setSize(300,30);
        prompt.setLocation(100,30);
        popup.add(prompt);

        addSessionRecoveryButtons(popup); // Add buttons from helper.
        
        //Add the popup to the POPUP_LAYER
        mainMenuLayers.add(popup, JLayeredPane.POPUP_LAYER);
        popup.setVisible(true);
        popup.moveToFront();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
