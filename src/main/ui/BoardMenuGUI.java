package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;

import model.Board;
import model.ChampionTemplate;
import model.Placeable;
import model.Set;

// The BoardMenuGUI provides a simple GUI to handle the design and 
// edits upon a given board(app state).
public class BoardMenuGUI {
    // Application State
    private Board board;

    // Aesthetic Assets
    public final static Map<Integer, Color> COSTCOLORS = Map.of(
        1, Color.decode("#AFAFAF"),
        2, Color.decode("#1BC660"),
        3, Color.decode("#0C6CC3"),
        4, Color.decode("#F947C6"),
        5, Color.decode("#FE8902")
    );

    // Layers 
    private JFrame boardMenuJFrame;
    private JLayeredPane boardMenuLayers;
    private Container contentPane;

    // JFrame Components
    private JPanel boardDisplayPanel;
    private JPanel selectChampionPanel;
    private JPanel deleteBoardPanel;
    private JScrollPane scrollPane;

    // EFFECT: Create new board menu
    public BoardMenuGUI(MainMenuGUI main, Board b) {
        this.board = b;
        boardMenuJFrame = new DefaultFrame(b.getName());
        boardMenuJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configureLayers();
        configureDelete();
        configureChampionDisplay();
        configureWindowListener(main); //Show = TRUE
        
    }

    // EFFECT: Add window listener that ensures switchback to main menu
    public void configureWindowListener(MainMenuGUI main) {
        boardMenuJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                main.refreshBoards();
                main.show();
                main.setLocation(boardMenuJFrame.getX(), boardMenuJFrame.getY());
                boardMenuJFrame.dispose();
            }
        });
    }

    // EFFECT: Create a corner JPanel with the option to delete the board.
    public void configureDelete() {
        deleteBoardPanel = new JPanel();
        deleteBoardPanel.setLayout(null);
        deleteBoardPanel.setBounds(0, 600, 200, 200);
        deleteBoardPanel.setBackground(MainMenuGUI.DARK);
        contentPane.add(deleteBoardPanel);
    }

    //EFFECT: Initialize and configure the basic JPanel and JScrollPane Used to hold all the champions
    public void configureChampionDisplay() {
        selectChampionPanel = new JPanel();
        selectChampionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        selectChampionPanel.setPreferredSize(new Dimension(800, 200));
        selectChampionPanel.setBackground(MainMenuGUI.COMP1);
        scrollPane = new JScrollPane(selectChampionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(200, 600, 800, 200);
        contentPane.add(scrollPane);
        loadAllAvailableChampions();
    }

    //EFFECT: Load Champions into the championSelectJPanel as JPanels by cost.
    public void loadAllAvailableChampions() {
        Set setForThisBoard = this.board.getSet();
        ArrayList<Placeable> availableChampions = new ArrayList<>(setForThisBoard.getPlaceableHashMap().values());
        for (int x = 1; x <= 5; x++) {
            for (Placeable p: availableChampions) {
                if (p instanceof ChampionTemplate) {
                    if (((ChampionTemplate)p).getCost() == x) {
                        configureChampionPanel((ChampionTemplate)p);
                    }
                }
            }
        }
    }

    // EFFECT: Create the JPanel and Label for the associating champion
    //         Also create border according to cost.
    public void configureChampionPanel(ChampionTemplate t) {
        JPanel championPanel = new ChampionSelectPanel(t);
        selectChampionPanel.add(championPanel);
    }


    // EFFECT: Assign fields for layers in the JFrame
    public void configureLayers() {
        boardMenuLayers = boardMenuJFrame.getLayeredPane();
        contentPane = boardMenuJFrame.getContentPane();
    }

    // EFFECT: Set location of the BoardMenu Jframe
    public void setLocation(int x, int y) {
        boardMenuJFrame.setLocation(x, y);
    }



    //EFFECT: Show board menu
    public void show() {
        boardMenuJFrame.setVisible(true);
    }

    
}
