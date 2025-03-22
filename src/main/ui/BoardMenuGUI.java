package ui;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;


import model.Board;
import model.ChampionTemplate;
import model.Placeable;
import model.Set;

// The BoardMenuGUI provides a simple GUI to handle the design and 
// edits upon a given board(app state).
public class BoardMenuGUI {
    // Application State
    private Board board;
    
    // Parent
    private MainMenuGUI mainMenu;

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
    private JPanel cornerMenuPanel;
    private JScrollPane scrollPane;


    private Hex[][] hexBoard = new Hex[7][4];
    private ChampionTemplate selectedChampion;


    // EFFECT: Create new board menu
    public BoardMenuGUI(MainMenuGUI main, Board b) {
        this.mainMenu = main;
        this.board = b;
        selectedChampion = null;
        boardMenuJFrame = new DefaultFrame(b.getName());
        boardMenuJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configureLayers();
        configureCornerMenu();
        configureChampionDisplay();
        bindEsc();
        configureBoardDisplay();
        configureWindowListener(main); //Show = TRUE
        
    }


    // EFFECT: Add window listener that ensures switchback to main menu
    public void configureWindowListener(MainMenuGUI main) {
        boardMenuJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                main.refreshBoards();
                main.setLocation(boardMenuJFrame.getX(), boardMenuJFrame.getY());
                main.show();
                boardMenuJFrame.dispose();
            }
        });
    }

    // EFFECT: Create a corner JPanel with the option to delete the board.
    private void configureCornerMenu() {
        cornerMenuPanel = new JPanel();
        cornerMenuPanel.setLayout(null);
        cornerMenuPanel.setBounds(0, 600, 200, 200);
        cornerMenuPanel.setBackground(MainMenuGUI.DARK);
        contentPane.add(cornerMenuPanel);
        addDeleteButton();
        addReturnButton();
        
    }

    // EFFECT: Create a button that when clicked deletes this board.
    //         When clicked return to main menu and delete the board.
    private void addDeleteButton() {
        JButton deleteBoardButton = new JButton();
        deleteBoardButton.setText("Delete Permanently");
        deleteBoardButton.setBounds(10, 20, 180, 60);
        deleteBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    MainMenuGUI.planner.removeBoard(board.getName());
                    mainMenu.setLocation(boardMenuJFrame.getX(), boardMenuJFrame.getY());
                    mainMenu.refreshBoards();
                    mainMenu.show();
                    boardMenuJFrame.dispose();
                }
            }
        });
        cornerMenuPanel.add(deleteBoardButton);
    }

    // EFFECT: Create a button that when clicked returns to main menu.
    private void addReturnButton() {
        JButton returnButton = new JButton();
        returnButton.setText("Main Menu");
        returnButton.setBounds(10, 90, 180, 60);
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mainMenu.setLocation(boardMenuJFrame.getX(), boardMenuJFrame.getY());
                    mainMenu.refreshBoards();
                    mainMenu.show();
                    boardMenuJFrame.dispose();
                }
            }
        });
        cornerMenuPanel.add(returnButton);
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
        championPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (selectedChampion == null) {
                        highlightHexes();
                        selectedChampion = t;
                    }
                }
            }
            

        });
        selectChampionPanel.add(championPanel);
    }

    // EFFECT: Configure the Board Display
    public void configureBoardDisplay() {
        boardDisplayPanel = new JPanel();
        boardDisplayPanel.setLayout(null);
        boardDisplayPanel.setBounds(0, 0, 1000, 600);
        boardDisplayPanel.setBackground(MainMenuGUI.DARK);
        displayNewHexboard();
        contentPane.add(boardDisplayPanel);
    }

    // EFFECT: Displays the full hexboard
    public void displayNewHexboard() {
        int gap = 10;
        int x = 70;
        int xOffSet = 70 + (int)(Hex.HEXRADIUS * (Math.sqrt(3)/2)) + gap/2;
        int y = 120;
        int dx = (int)(Hex.HEXRADIUS * Math.sqrt(3)) + gap;
        int dy = (3 * Hex.HEXRADIUS)/2 + gap;
        for (int yy = 0; yy < 4; yy++) {
            for (int xx = 0; xx < 7; xx++) {
                Hex newHex = new Hex(x, y);
                boardDisplayPanel.add(newHex);
                hexBoard[xx][yy] = newHex;
                x += dx;
            }
            if (yy % 2 == 0) {
                x = xOffSet;
            } else {
                x = 70;
            }
            y += dy;
        }
    }

    public void bindEsc() {
        boardMenuJFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("ESCAPE"), "deselectChampion");
    
        boardMenuJFrame.getRootPane().getActionMap().put("deselectChampion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedChampion != null) {
                    System.out.println("CLICKED");
                    unhighlightHexes();
                    selectedChampion = null;
                }
            }
        });
    }
    

    // REQUIRES: The double array of hexes to be instantiated
    // EFFECT: Highlight all hexes
    public void highlightHexes() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 7; x++) {
                hexBoard[x][y].highlight();
            }
        }
    }
    // REQUIRES: The double array of hexes
    public void unhighlightHexes() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 7; x++) {
                hexBoard[x][y].unhighlight();
            }
        }
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
