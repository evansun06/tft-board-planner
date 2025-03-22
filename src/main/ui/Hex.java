package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.ChampionInstance;

//This is a specific JPanel that can be configured to draw a hexagon
//The Hex Class also contains the respective champion assigned to it
public class Hex extends JPanel {
    public ChampionInstance championOnRoster;
    public int hexX;
    public int hexY;
    public Color rimColor;
    public static final int HEXRADIUS = 70;

    // EFFECT: Constructor
    public Hex(int x, int y) {
        super();
        hexX = x;
        hexY = y;
        rimColor = Color.RED;
        this.setBounds(x - (int)(HEXRADIUS * Math.sqrt(3)/2), y - HEXRADIUS, (int)(2* HEXRADIUS * Math.sqrt(3)/2), HEXRADIUS * 2);
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
        int centerX = (int)(HEXRADIUS * Math.sqrt(3)/ 2);
        int centerY = HEXRADIUS;

        // Set the fill color for the hexagon
        g2d.setColor(MainMenuGUI.DARK);
        fillHex(g2d, centerX, centerY, HEXRADIUS);

        // Set the outline color
        g2d.setColor(rimColor);
        drawHex(g2d, centerX, centerY, HEXRADIUS);
    }

    // EFFECT: Draw a hex polygon with a rotated orientation
    protected void drawHex(Graphics g, int x, int y, int r) {
        // X points going clockwise, starting from the bottom-left corner
        int[] xPoints = {x, x + (int)(r * Math.sqrt(3) / 2), x + (int)(r * Math.sqrt(3) / 2),
                        x, x - (int)(r * Math.sqrt(3) / 2), x - (int)(r * Math.sqrt(3) / 2)};
        
        // Y points going clockwise, starting from the bottom-left corner
        int[] yPoints = {y + r, y + r / 2, y - r / 2, y - r, y - r / 2, y + r / 2};
        g.drawPolygon(xPoints, yPoints, 6);
    }

    // Helper method to fill the hexagon with a rotated orientation
    protected void fillHex(Graphics2D g2d, int x, int y, int r) {
        // X points going clockwise, starting from the bottom-left corner
        int[] xPoints = {x, x + (int)(r * Math.sqrt(3) / 2), x + (int)(r * Math.sqrt(3) / 2),
                        x, x - (int)(r * Math.sqrt(3) / 2), x - (int)(r * Math.sqrt(3) / 2)};
        
        // Y points going clockwise, starting from the bottom-left corner
        int[] yPoints = {y + r, y + r / 2, y - r / 2, y - r, y - r / 2, y + r / 2};
        g2d.fillPolygon(xPoints, yPoints, 6);
    }

    // EFFECT: Highlight
    protected void highlight() {
        rimColor = Color.WHITE;
        this.repaint();
    }

    // EFFECT: UnHighlight
    protected void unhighlight() {
        rimColor = Color.RED;
        this.repaint();
    }




    
}
