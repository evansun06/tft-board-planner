package ui;

import javax.swing.*;

import persistance.JsonReader;
import persistance.JsonWriter;
import model.*;

import java.awt.BorderLayout;

// MainMenuGUI is the graphical interface that users are first introduced to
//
public class MainMenuGUI extends JFrame {
    private Planner planner;
    private JsonReader jsonReader = new JsonReader("data/userPersistance.json");
    private JsonWriter jsonWriter = new JsonWriter("data/userPersistance.json");
    private JDesktopPane desktopPane;



    // EFFECT: constructor that sets the size and attribuites of the Jframe.
    public MainMenuGUI() {
        super("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800);
        desktopPane = new JDesktopPane();
        this.add(desktopPane);
        promptSessionRecovery();
        this.setVisible(true);

    }

    // EFFECTS: Prepares the labels and options of the main menue.
    public void setMainMenuComponents() {
        
    }

    // EFFECTS: Display a pop-up a tab that asks the user if they want to recover the previous session.
    //          Will not let user continue unless a descision is made.
    public void promptSessionRecovery() {
        JInternalFrame popup = new JInternalFrame("Session Recovery", true, false, false, false);
        popup.setSize(500,200);
        popup.setLayout(null);
        desktopPane.add(popup);
        popup.setResizable(false);
        JLabel prompt = new JLabel("Do you want to recover your previous session?");
        prompt.setBounds(75, 50,400,30);
        popup.add(prompt);

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        yes.setBounds(120, 100, 100, 30);
        no.setBounds(230, 100, 100, 30);
        popup.add(yes);
        popup.add(no);

        //Dynamically Center
        popup.setLocation((this.getWidth() / 2 - popup.getWidth() / 2),
                (this.getHeight() / 2 - popup.getHeight() / 2));
        popup.setVisible(true);
        
    }



    
    public static void main(String[] args) {
        new MainMenuGUI();
    }

}
