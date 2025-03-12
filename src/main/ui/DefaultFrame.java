package ui;

import javax.swing.JFrame;

public class DefaultFrame extends JFrame {
    public final static int WIDTH = 1000;
    public final static int HEIGHT = 800;

    public DefaultFrame(String name) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(name);
    }
    
}
