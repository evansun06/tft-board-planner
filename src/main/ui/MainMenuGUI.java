package ui;

import javax.swing.*;


import persistance.JsonReader;
import persistance.JsonWriter;
import model.*;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;


// MainMenuGUI is the graphical interface that users are first introduced to
//
public class MainMenuGUI implements ActionListener { 
    // Aesthetic Constants
    public static final Color DARK = Color.decode("#1A1423");
    public static final Color COMP1 = Color.decode("#4A505E");
    public static final Color COMP2 = Color.decode("#7A8B99");

    // MainMenuGUI JFrame Layers
    private JFrame mainMenuJFrame;
    private JLayeredPane mainMenuLayers;
    private Container contentPane;

    // JFrame Components
    private JPanel optionPanel;
    private JButton addBoardButton;
    private JInternalFrame popup;
    private JPanel boardPanel;
    private Boolean isMakingBoard;

    //Persistance
    private JsonReader jsonReader = new JsonReader("data/userPersistance.json");
    private JsonWriter jsonWriter = new JsonWriter("data/userPersistance.json");

    //Application State
    private static Planner planner;
    

    // EFFECT: constructor that sets the size and attribuites of the Jframe.
    public MainMenuGUI() {
        mainMenuJFrame = new DefaultFrame("TFT Board Planner");
        mainMenuJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        configureMainJFrame();
        setMainMenuComponents();
        setPersistance();
        promptSessionRecovery();
        isMakingBoard = false;
        mainMenuJFrame.setVisible(true);
       
        
    }

    //EFFECT: Configure MainJFrame with according dimensions
    public void configureMainJFrame() {
        mainMenuLayers = mainMenuJFrame.getLayeredPane();
        contentPane = mainMenuJFrame.getContentPane();
    }

    // REQUIRES: should only be called from MainMenuGUI constructor
    // EFFECTS: Prepares the labels and options of the main menu.
    private void setMainMenuComponents() {
        optionPanel = new JPanel();
        optionPanel.setLayout(null);
        boardPanel = new JPanel();
        boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        optionPanel.setBounds(0,0,200,800);
        boardPanel.setBounds(200,0,800,800);
        optionPanel.setBackground(DARK);
        setMenuBarComponents();
        boardPanel.setBackground(COMP1);
        contentPane.add(optionPanel);
        contentPane.add(boardPanel);
        
    }

    // EFFECT: Resets the boardpanel and repaints
    public void refreshBoards() {
        boardPanel.removeAll();
        boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        boardPanel.setBounds(200,0,800,800);
        displayBoardDeck();
        
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


    // EFFECT: Ensures when the user exits the application the data is saved.
    public void setPersistance() {
        mainMenuJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    jsonWriter.writePlannerToFile(planner);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } finally {
                    printEventLog();
                    mainMenuJFrame.dispose();
                }
            }
        });
    }
    
    // EFFECT: Print EventLog
    public void printEventLog() {
        for (Event e :EventLog.getInstance()) {
            System.out.println(e.getDescription());
        }
    }

    // EFECT: display boards top-bottom in the boardPanel
    public void displayBoardDeck() {
        for (Board b: planner.getBoardDeck()) {
            JButton boardButton = new JButton(b.getName());
            boardButton.addMouseListener(new MouseAdapter() {
                @Override
                // EFFECT: When the button for a board is clicked, open the board menu associated
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        mainToBoardSwitch(b);
                    }
                }
            }); 
            boardButton.setPreferredSize(new Dimension(750, 50));
            addMouseListenerForBoardButton(boardButton);
            boardPanel.add(boardButton);
        }
        boardPanel.setVisible(true);
    }

    // EFFECT: adds a mouse listener for board button that opens a new board menu
    public void addMouseListenerForBoardButton(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            // EFFECT: reads JSON to this.planner state and disposes of the prompt popup
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println(planner.getBoard(button.getText()));
                }
            }
        }); 
    }


    // EFFECTS: Display a pop-up a tab that asks the user if they want to recover the previous session.
    //          Will not let user continue unless a descision is made.
    public void promptSessionRecovery() {
        popup = new PopupInternalFrame("Session Recovery");
        popup.setClosable(false);
        addSessionRecoveryButtons(popup); // Add buttons from helper.
        JLabel prompt = new JLabel("Do you want to recover your previous session?");
        prompt.setSize(300,30);
        prompt.setLocation(100,30);
        popup.add(prompt);
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
        yes.setBackground(COMP1);
        no.setBackground(COMP1);
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
                    displayBoardDeck();
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
    
    //EFFECT: loads a JInternal frame that collects a name.
    public void boardSetUpPrompt() {
        JInternalFrame setupPopup = new BoardSetUpPopUp(this);
        setupPopup.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                setIsMakingBoard(false);
            }
        });

        mainMenuLayers.add(setupPopup, JLayeredPane.POPUP_LAYER);
        setupPopup.setVisible(true);
    
    }
    
    //EFFECT: Switch the Jframe from MainMenuGUI to BoardMenuGui
    public void mainToBoardSwitch(Board b) {
        BoardMenuGUI boardMenuGUI = new BoardMenuGUI(this, b);
        this.hide();
        mainMenuJFrame.setAlwaysOnTop(false);
        boardMenuGUI.setLocation(mainMenuJFrame.getX(), mainMenuJFrame.getY());
        boardMenuGUI.show();
    }

    // EFFECT: hide the main menu
    public void hide() {
        mainMenuJFrame.setVisible(false);
    }

    // EFFECT: show the main menu
    public void show() {
        mainMenuJFrame.setVisible(true);
    }

    // EFFECT: change the status of isMakingBoard
    public void setIsMakingBoard(Boolean b) {
        this.isMakingBoard = b;
    }

    // EFFECT: Set location of the MainMenu Jframe
    public void setLocation(int x, int y) {
        mainMenuJFrame.setLocation(x, y);
    }
    
    // Getter
    public Planner getPlanner() {
        return MainMenuGUI.planner;
    }


    @Override
    // EFFECT: Customize functions as a result of different actions
    // 1. Create new board when the AddNewBoard button gets triggered
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBoardButton) {
            if (planner != null && !isMakingBoard) {
                boardSetUpPrompt();
                setIsMakingBoard(true);
            }
        } 
    }



    public static void main(String[] args) {
        new MainMenuGUI();
    }

}
