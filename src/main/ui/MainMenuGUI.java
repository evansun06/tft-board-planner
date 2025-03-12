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
    // Aesthetic Constants
    public final Color dark = Color.decode("#1A1423");
    public final Color comp1 = Color.decode("#4A505E");
    public final Color comp2 = Color.decode("#7A8B99");

    // MainMenuGUI JFrame Layers
    private JFrame mainMenuJFrame;
    private JLayeredPane mainMenuLayers;
    private Container contentPane;

    // OptioPanel Components
    private JPanel optionPanel;
    private JButton addBoardButton;
    private JInternalFrame popup;
    private JPanel boardPanel;

    //Persistance
    private JsonReader jsonReader = new JsonReader("data/userPersistance.json");

    //Application State
    protected Planner planner;
    

    // EFFECT: constructor that sets the size and attribuites of the Jframe.
    public MainMenuGUI() {
        mainMenuJFrame = new DefaultFrame("TFT Board Planner");
        configureMainJFrame();
        promptSessionRecovery();
        setMainMenuComponents();
        mainMenuJFrame.setVisible(true);
        
        
    }

    //EFFECT: Configure MainJFrame with according dimensions
    public void configureMainJFrame() {
        // mainMenuJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainMenuJFrame.setSize(1000, 800);
        // mainMenuJFrame.setLayout(null);
        // mainMenuJFrame.setResizable(false);
        mainMenuLayers = mainMenuJFrame.getLayeredPane();
        contentPane = mainMenuJFrame.getContentPane();
    }

    // REQUIRES: should only be called from MainMenuGUI constructor
    // EFFECTS: Prepares the labels and options of the main menu.
    private void setMainMenuComponents() {
        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        boardPanel = new JPanel();
        optionPanel.setBounds(0,0,200,800);
        boardPanel.setBounds(200,0,800,800);
        optionPanel.setBackground(dark);
        setMenuBarComponents();
        boardPanel.setBackground(comp1);
        contentPane.add(optionPanel);
        contentPane.add(boardPanel);
        
    }

    // EFFECT: Adds buttons components to the menu bar component
    public void setMenuBarComponents() {
        addBoardButton = new JButton();
        addBoardButton.setSize(150,50);
        addBoardButton.setLocation(25,700);
        addBoardButton.setText("New Board");
        addBoardButton.addActionListener(this);
        optionPanel.add(addBoardButton);

    }


    // EFFECTS: Display a pop-up a tab that asks the user if they want to recover the previous session.
    //          Will not let user continue unless a descision is made.
    public void promptSessionRecovery() {
        popup = new SessionRecoveryPopup();
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
        yes.setBackground(comp1);
        no.setBackground(comp1);
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
    
    //
    

    @Override
    // EFFECT: Customize functions as a result of different actions
    // 1. Create new board when the AddNewBoard button gets triggered
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBoardButton) {
            
        }
    }

    public static void main(String[] args) {
        new MainMenuGUI();
    }

}
