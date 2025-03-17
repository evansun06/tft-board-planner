package ui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;


public class PopupInternalFrame extends JInternalFrame {

    // EFFECT: Make a unclosable JInternalFrame that centers for the DefaultFrame dimensions
    public PopupInternalFrame(String name) {
        super(name, true, false, false, false);
        this.setSize(500,200);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation((DefaultFrame.WIDTH / 2 - this.getWidth() / 2),
                (DefaultFrame.HEIGHT / 2 - this.getHeight() / 2)); 
    }

    
}
