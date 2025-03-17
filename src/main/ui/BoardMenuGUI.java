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

        boardMenuJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                main.refreshBoards();
                main.show();
                boardMenuJFrame.dispose();
            }
        });
    }

    //EFFECT: Show board menu
    public void show() {
        boardMenuJFrame.setVisible(true);
    }
}
