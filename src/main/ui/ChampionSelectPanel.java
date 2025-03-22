package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.ChampionTemplate;

// A JPanel with specific aesthetics and sizing for the selection menu.
public class ChampionSelectPanel extends JPanel {
    ChampionTemplate champ;


    // EFFECT: Constructs a JPanel for the associated championTemplate
    public ChampionSelectPanel(ChampionTemplate c) {
        super();
        this.champ = c;
        this.setSize(80, 50);
        this.setBackground(MainMenuGUI.DARK);
        this.setBorder(new LineBorder(BoardMenuGUI.COSTCOLORS.get(c.getCost()), 1));

        JLabel championLabel = new JLabel();
        championLabel.setText(c.getName());
        championLabel.setForeground(Color.WHITE);
        championLabel.setBounds(15,10,50, 30);
        this.add(championLabel);
    }

}
