package ui;

import javax.swing.JFrame;

// Default frame for the board and main menu GUI
public class DefaultFrame extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public DefaultFrame(String name) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(name);
    }
    
}
