package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Board;

public class BoardMenuGUI {
    private Board board;
    private JFrame boardMenuJFrame;

    // EFFECT: Create new board menu
    public BoardMenuGUI(MainMenuGUI main, Board b) {
        this.board = b;
        boardMenuJFrame = new DefaultFrame(b.getName());
        boardMenuJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configureWindowListener(main);
        
    }

    // EFFECT: Add window listener that ensures switchback to main menu
    public void configureWindowListener(MainMenuGUI main) {
        boardMenuJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                main.refreshBoards();
                main.show();
                main.setLocation(boardMenuJFrame.getX(), boardMenuJFrame.getY());
                boardMenuJFrame.dispose();
            }
        });
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
