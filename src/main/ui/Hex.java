package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.Board;
import model.ChampionInstance;
import model.ChampionTemplate;

//This is a specific JPanel that can be configured to draw a hexagon
//The Hex Class also contains the respective champion assigned to it
public class Hex extends JPanel {
    private ChampionInstance championOnRoster;
    // Coordinate for board
    private int hexX;
    private int hexY;
    private Timer timer;
    
    private Color rimColor;
    private Color fillColor;
    public static final int HEXRADIUS = 70;
    private JLabel champLabel;

    // EFFECT: Constructor
    public Hex(int x, int y, int xx, int yy) {
        super();
        this.setLayout(null);
        hexX = xx;
        hexY = yy;
        rimColor = Color.WHITE;
        fillColor = MainMenuGUI.DARK;
        configureChampLabel();
        this.setBounds(x - (int)(HEXRADIUS * Math.sqrt(3) / 2), y - HEXRADIUS,
                (int)(2 * HEXRADIUS * Math.sqrt(3) / 2), HEXRADIUS * 2);
                
        //this.setBorder(new LineBorder(BoardMenuGUI.COSTCOLORS.get(1), 1));
        this.setOpaque(false); // SET TRANSPARENT.
        championOnRoster = null;
    }

    @Override
    // EFFECT: Override default retangle in JPanel to replace with custom hexagon
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast to Graphics2D for better control
        Graphics2D g2d = (Graphics2D) g;

        // Calculate the center of the panel
        int centerX = (int)(HEXRADIUS * Math.sqrt(3) / 2);
        int centerY = HEXRADIUS;

        // Set the fill color for the hexagon
        g2d.setColor(fillColor);
        fillHex(g2d, centerX, centerY, HEXRADIUS);

        // Set the outline color
        g2d.setColor(rimColor);
        drawHex(g2d, centerX, centerY, HEXRADIUS);
    }

    // EFFECT: Draw a hex polygon with a rotated orientation
    protected void drawHex(Graphics g, int x, int y, int r) {
        // X points going clockwise, starting from the bottom-left corner
        int[] xpoints = {x, x + (int)(r * Math.sqrt(3) / 2), x + (int)(r * Math.sqrt(3) / 2),
                x, x - (int)(r * Math.sqrt(3) / 2), x - (int)(r * Math.sqrt(3) / 2)};
        
        // Y points going clockwise, starting from the bottom-left corner
        int[] ypoints = {y + r, y + r / 2, y - r / 2, y - r, y - r / 2, y + r / 2};
        g.drawPolygon(xpoints, ypoints, 6);
    }

    // Helper method to fill the hexagon with a rotated orientation
    protected void fillHex(Graphics2D g2d, int x, int y, int r) {
        // X points going clockwise, starting from the bottom-left corner
        int[] xpoints = {x, x + (int)(r * Math.sqrt(3) / 2), x + (int)(r * Math.sqrt(3) / 2),
                x, x - (int)(r * Math.sqrt(3) / 2), x - (int)(r * Math.sqrt(3) / 2)};
        
        // Y points going clockwise, starting from the bottom-left corner
        int[] ypoints = {y + r, y + r / 2, y - r / 2, y - r, y - r / 2, y + r / 2};
        g2d.fillPolygon(xpoints, ypoints, 6);
    }

    // EFFECT: Highlight
    protected void highlight() {
        rimColor = Color.RED;
        this.repaint();
    }

    // EFFECT: UnHighlight
    protected void unhighlight() {
        rimColor = Color.WHITE;
        this.repaint();
    }

    // REQUIRES: Champion to be assigned to this hex
    // EFFECT: Changes the fill color according to the champion assigned
    protected void displayChampion() {
        if (championOnRoster != null) {
            fillColor = BoardMenuGUI.COSTCOLORS.get(championOnRoster.getCost());
            rimColor = Color.WHITE;
            champLabel.setText(championOnRoster.getName());
            champLabel.setVisible(true);
            this.repaint();
        }    
    }

    // EFFECT: Create a champ label
    protected void configureChampLabel() {
        champLabel = new JLabel();
        champLabel.setBounds(42, 45,100, 20);
        this.add(champLabel);
    }

    // EFFECT: Return hex to default display and no assigned champion
    protected void returnToDefault() {
        rimColor = Color.WHITE;
        fillColor = MainMenuGUI.DARK;
        champLabel.setVisible(false);
        championOnRoster = null;
        this.repaint();
    }

    // EFFECT: Assign champion to this board hex specifically a NEW instance
    protected void assignChampion(ChampionTemplate template, Board b) {
        b.addChampionToBoard(template, hexX, hexY);
        championOnRoster = b.getChampionFromBoard(hexX, hexY);
        displayChampion();
    }

    // EFFECT: Assign a champion instance (Used for swaps)
    protected void assignChampion(ChampionInstance instance) {
        championOnRoster = instance;
        displayChampion();
    }

    



    // GETTERS
    public int getHexX() {
        return hexX;
    }

    public int getHexY() {
        return hexY;
    }

    public ChampionInstance getChampionAtHex() {
        return this.championOnRoster;
    }
    


    
    






    
}
