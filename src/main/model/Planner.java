package model;

import java.util.ArrayList;

// Planner object that holds all the boards for the user
public class Planner {
    private ArrayList<Board> boardDeck;

    // EFFECT: Creates an empty deck of Boards
    public Planner() {
        boardDeck = new ArrayList<>();
    }

    // REQUIRES: name be unique
    // MODIFIES: this
    // EFFECT: Adds a board to the deck with unique name
    public void addBoard(Set set, String name) {
        boardDeck.add(new Board(set, name));
    }

    // MODIFIES: this
    // EFFECT: Removes the corresponding board from the boardDeck
    //         if no corresponding name, does nothing.
    public void removeBoard(String name) {
        int index = 0;
        for(Board b: boardDeck) {
            if (b.getName() == name) {
                index = boardDeck.indexOf(b);
            }
        }
        boardDeck.remove(index);
    }

    // Getters
    public ArrayList<Board> getBoardDeck() {
        return this.boardDeck;
    }


    
}
