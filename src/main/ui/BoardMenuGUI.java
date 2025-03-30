package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;


import model.Board;
import model.ChampionInstance;
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
    public static final Map<Integer, Color> COSTCOLORS = Map.of(
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
    private JPanel statPanel;
    private JLabel statlabel;

    private Hex[][] hexBoard = new Hex[7][4];

    private ChampionTemplate selectedChampionTemplate;
    private ChampionInstance toSwap;


    // EFFECT: Create new board menu
    public BoardMenuGUI(MainMenuGUI main, Board b) {
        this.mainMenu = main;
        this.board = b;
        this.selectedChampionTemplate = null;
        boardMenuJFrame = new DefaultFrame(b.getName());
        boardMenuJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configureLayers();
        configureCornerMenu();
        configureChampionDisplay();
        bindEsc();
        configureBoardDisplay();
        configureStatPanel();
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
                    mainMenu.getPlanner().removeBoard(board.getName());
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
                    if (selectedChampionTemplate == null) {
                        highlightHexes();
                        displayStats(t);
                        selectedChampionTemplate = t;
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
        boardDisplayPanel.setBackground(MainMenuGUI.COMP2);
        displayNewHexboard();
        contentPane.add(boardDisplayPanel);
    }

    

    public void configureStatPanel() {
        statPanel = new JPanel();
        statlabel = new JLabel();
        statlabel.setBounds(0,20, 1000, 50);
        statlabel.setForeground(Color.WHITE);
        statlabel.setLocation(10, 10);
        statPanel.setBackground(MainMenuGUI.COMP2);
        statPanel.setBounds(0,0, 1000, 50);
        statPanel.add(statlabel);
        statPanel.setVisible(false);
        boardDisplayPanel.add(statPanel);
    }


    // EFFECT: Displays the full hexboard with consideration for champions
    public void displayNewHexboard() {
        int gap = 10;
        int x = 70;
        int xoffset = 70 + (int)(Hex.HEXRADIUS * (Math.sqrt(3) / 2)) + gap / 2;
        int y = 150;
        int dx = (int)(Hex.HEXRADIUS * Math.sqrt(3)) + gap;
        int dy = (3 * Hex.HEXRADIUS) / 2 + gap;
        for (int yy = 0; yy < 4; yy++) {
            for (int xx = 0; xx < 7; xx++) {
                configureHex(x, y, xx, yy);                
                x += dx;
            }
            if (yy % 2 == 0) {
                x = xoffset;
            } else {
                x = 70;
            }
            y += dy;
        }
    }

    // EFFECT: Configure hex depending on the status of the board (APP STATE)
    public void configureHex(int x, int y, int xx, int yy) {
        if (board.getChampionFromBoard(xx, yy) == null) { // No Champion
            Hex newHex = new Hex(x, y, xx, yy);
            configureHexListeners(newHex);
            hexBoard[xx][yy] = newHex;
            boardDisplayPanel.add(newHex);
        } else {
            Hex championHex = new Hex(x, y, xx, yy);
            hexBoard[xx][yy] = championHex;
            championHex.assignChampion(board.getChampionFromBoard(xx, yy));
            boardDisplayPanel.add(championHex);
            configureHexListeners(championHex);
        } 
    }

    // EFFECT: Create action listeners that allow the assignment, swap, and removal of champions on the roster.
    @SuppressWarnings("methodlength")
    public void configureHexListeners(Hex hex) {
        hex.addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("methodlength")
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (selectedChampionTemplate != null) {
                        if (!board.isFull()) {
                            hex.assignChampion(selectedChampionTemplate, board);
                            clearStats();
                        } else {
                            displayRosterFullPopup();
                        }
                        unselect();
                    } else if (toSwap == null) {
                        if (hex.getChampionAtHex() != null) {
                            toSwap = hex.getChampionAtHex();
                            highlightHexes();
                        }

                    } else if (toSwap != null) {
                        if (hex.getChampionAtHex() != null) {
                            Hex old = hexBoard[toSwap.getX()][toSwap.getY()]; 
                            int x1 = old.getHexX(); // Get old hex's X coordinate
                            int y1 = old.getHexY(); // Get old hex's Y coordinate
                            int x2 = hex.getHexX(); // Get target hex's X coordinate
                            int y2 = hex.getHexY(); // Get target hex's Y coordinate
                            ChampionInstance champ1 = old.getChampionAtHex(); // Champion currently in old hex
                            ChampionInstance champ2 = hex.getChampionAtHex(); // Champion currently in target hex

                            // Swap the champion references in the hexes
                            old.assignChampion(champ2);
                            hex.assignChampion(champ1);

                            // Update champion locations to match their new hex positions
                            champ1.setLocation(x2, y2);
                            champ2.setLocation(x1, y1);

                            unselect();
                        } else {
                            Hex old = hexBoard[toSwap.getX()][toSwap.getY()]; 
                            old.returnToDefault();
                            hex.assignChampion(toSwap);
                            toSwap.setLocation(hex.getHexX(), hex.getHexY());
                            unselect();
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (hex.getChampionAtHex() != null) {
                        ChampionInstance aboutToDelete = hex.getChampionAtHex();
                        board.removeChampionFromRoster(aboutToDelete.getX(), aboutToDelete.getY());
                        hex.returnToDefault();
                    }
                }
            }  
            
        }); 
    }

    // EFFECT: Clear Text from stats
    public void clearStats() {
        statlabel.setText("");
        statPanel.setVisible(false);
    }

    // EFFECT: display stats at the top of the board
    // EFFECT: Display stats at the top of the board
    public void displayStats(ChampionTemplate c) {
        statlabel.setText(
                "Name: " + c.getName()  
                + " | HP: " + c.getHealth()  
                + " | ARM: " + c.getArmour() 
                + " | MR: " + c.getMagicResist() 
                + " | AD: " + c.getAttackDamage() 
                + " | AttackSpd: " + c.getAttackSpeed()
                + " | AP: " + c.getAbilityPower()
                + " | Crit Chance: " + c.getCritChance()
                + " | Crit Multiplier: " + c.getCritMultiplier() 
                + " | Range: " + c.getRange() 
                + " | Cost: " + c.getCost()
        );
        statPanel.setVisible(true);
    }



    public void displayRosterFullPopup() {
        PopupInternalFrame fullBoardPopup = new PopupInternalFrame("Board Full");
        JLabel warning = new JLabel();
        warning.setText("You're Board Is Maxed");
        warning.setBounds(125, 30, 250, 30);
        fullBoardPopup.add(warning);
        boardMenuLayers.add(fullBoardPopup, JLayeredPane.POPUP_LAYER);
        fullBoardPopup.setVisible(true);
        
    }

    // EFFECT: Bind Escape as the key to disellect everything
    public void bindEsc() {
        boardMenuJFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("ESCAPE"), "deselectChampion");
    
        boardMenuJFrame.getRootPane().getActionMap().put("deselectChampion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedChampionTemplate != null || toSwap != null) {
                    unselect();
                    clearStats();
                }
            }
        });
    }
    
    // EFFECT: Unselect all parameters
    public void unselect() {
        unhighlightHexes();
        selectedChampionTemplate = null;
        toSwap = null;
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
        for (int yy = 0; yy < 4; yy++) {
            for (int xx = 0; xx < 7; xx++) {
                if (board.getChampionFromBoard(xx, yy) == null) {
                    hexBoard[xx][yy].unhighlight();
                } else {
                    hexBoard[xx][yy].displayChampion();
                }
                
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
