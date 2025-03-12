package ui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SessionRecoveryPopup extends JInternalFrame {

    // EFFECT: Make a unclosable JInternalFrame that centers for the DefaultFrame dimensions
    public SessionRecoveryPopup() {
        super("Session Recovery", true, false, false, false);
        this.setSize(500,200);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation((DefaultFrame.WIDTH / 2 - this.getWidth() / 2),
                (DefaultFrame.HEIGHT / 2 - this.getHeight() / 2));
        JLabel prompt = new JLabel("Do you want to recover your previous session?");
        prompt.setSize(300,30);
        prompt.setLocation(100,30);
        this.add(prompt);
        
    }
    
}
