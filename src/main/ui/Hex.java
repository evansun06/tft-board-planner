package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import model.ChampionInstance;

//This is a specific JPanel that can be configured to draw a hexagon
//The Hex Class also contains the respective champion assigned to it
public class Hex extends JPanel {
    public ChampionInstance championOnRoster;
    public int hexX = 100;
    public int hexY = 100;
    public static int hexRadius = 60;

    // EFFECT: Constructor
    public Hex(int x, int y) {
        super();
        this.setBounds(x - hexRadius, y - hexRadius, hexRadius * 2, (int)(hexRadius * Math.sqrt(3)));
        // this.setOpaque(false); // SET OPAQUE
        championOnRoster = null;
    }

    @Override
    // EFFECT: Override default retangle in JPanel to replace with custom hexagon
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast to Graphics2D for better control
        Graphics2D g2d = (Graphics2D) g;

        // Calculate the center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Set the fill color for the hexagon
        g2d.setColor(MainMenuGUI.COMP1);
        fillHex(g2d, centerX, centerY, hexRadius);

        // Set the outline color
        g2d.setColor(Color.RED);
        drawHex(g2d, centerX, centerY, hexRadius);
    }

    // EFFECT: Draw a hex polygon for a specific radius at a 
    //         cetrain x,y.
    protected void drawHex(Graphics g, int x, int y, int r) {
        //X points going clockwise from the most eastern point
        int[] xPoints = {x + r, x + r/2, x - r/2, x - r, x - r/2, x + r/2};

        int a = (int)(Math.sqrt(3)/2 * r);
        //Y points going clockwise from most eastern point
        int[] yPoints = {y, y - a, y - a, y, y + a, y + a };
        g.drawPolygon(xPoints,yPoints, 6);
    }

    // EFFECT: Helper method to fill the hexagon
    protected void fillHex(Graphics2D g2d, int x, int y, int r) {
        int[] xPoints = {x + r, x + r / 2, x - r / 2, x - r, x - r / 2, x + r / 2};
        int a = (int) (Math.sqrt(3) / 2 * r);
        int[] yPoints = {y, y - a, y - a, y, y + a, y + a};
        g2d.fillPolygon(xPoints, yPoints, 6);
    }

    
}
