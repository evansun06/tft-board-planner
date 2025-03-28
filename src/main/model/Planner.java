package model;

import java.util.ArrayList;

import org.json.JSONObject;

// Planner object that holds all the boards for the user
public class Planner {
    private ArrayList<Board> boardDeck;

    // EFFECT: Creates an empty deck of Boards
    public Planner() {
        boardDeck = new ArrayList<>();
    }

    // REQUIRES: name be unique
    // MODIFIES: this
    // EFFECT: Adds a board with set13 champions to the deck with unique name
    public void addBoard(String name) {
        EventLog.getInstance().logEvent(new Event("New board called `" + name + "` added to planner"));
        boardDeck.add(new Board(name));
    }

    // MODIFIES: this
    // EFFECT: Removes the corresponding board from the boardDeck
    //         if no corresponding name, does nothing.
    public void removeBoard(String name) {
        int index = -1;
        for (Board b: boardDeck) {
            if (b.getName() == name) {
                index = boardDeck.indexOf(b);
            }
        }
        if (index != -1) {
            boardDeck.remove(index);
            EventLog.getInstance().logEvent(new Event("Deleted board called " + name + "from planner"));
        }
    }

    // REQUIRES: boardDeck has only unique board names
    // EFFECT: get the corresponding board if name matches
    //         else return null
    public Board getBoard(String name) {
        for (Board b: boardDeck) {
            if (b.getName().equals(name)) {
                return b;
            }
        }
        return null;
    }

    // Getters
    public ArrayList<Board> getBoardDeck() {
        return this.boardDeck;
    }




    
}
