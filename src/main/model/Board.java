package model;

import java.util.ArrayList;

// Represents a single 7 x 4 Board playground in which
// Champions can be placed;
public class Board {
    public static final int xMin = 0;
    public static final int xMax = 7;
    public static final int yMin = 0;
    public static final int yMax = 3;
    private final int maxRosterSize = 10;
    private static Set set = new Set("SetThirteen");

    private ArrayList<Placeable> roster;
    private ArrayList<Integer> winHistory;
    private String name;


    // EFFECT: Creates blank board for the currentSet.
    //         with an empty roster.  
    public Board(String name) {
        this.name = name;
        this.roster = new ArrayList<>();
        this.winHistory = new ArrayList<>();
    }


    // REQUIRES: Base stat champion with name != null
    //           Champ must be within the set
    // MODIFIES: this
    // EFFECT: Adds a new champion instance to the roster if:
    //         -  rostersize is not maxed
    //         -  location on the board available
    //         -  championTemplate is readily placeable
    //         Else, does nothing.
  
    public void addChampionToBoard(ChampionTemplate champ, int x, int y) {
        if ((champ.readilyPlaceable) && !isFull() && (locationIsEmpty(x, y))) {
            roster.add(new ChampionInstance(champ, x, y));
        }
    }

    // REQUIRES:  0 <= x <= 6, 0 <= y <= 3
    // MODIFIES: this
    // EFFECT: Removes the champion at location (x,y) from the
    //         roster.
    //         Does nothing if no champion at location.
    public void removeChampionFromRoster(int x, int y) {
        roster.remove(getChampionFromBoard(x, y));
    }

    // REQUIRES:  0 <= x <= 6, 0 <= y <= 3.
    // MODIFIES: none.
    // EFFECT: Returns true if location is available on board
    public Boolean locationIsEmpty(int x, int y) {
        for (Placeable p: roster) {
            if (p.getX() == x && p.getY() == y) {
                return false;
            }
        }
        
        return true;
    }


    // REQUIRES: 1 <= placement <= 8
    // MODIFIES: this
    // EFFECT: adds a game placement to history
    public void addToHistory(int placement) {
        this.winHistory.add(placement);
    }

    // REQUIRES: 0 <= x <= 6, 0 <= y <= 3
    // MODIFIES: this 
    // EFFECT: returns the ChampionInstance for the given location
    //         else null
    public ChampionInstance getChampionFromBoard(int x, int y) {
        for (Placeable p: roster) {
            if (p.getX() == x && p.getY() == y) {
                return (ChampionInstance)p;
            }
        }
        return null;
    }

    // EFFECTS: returns weather the board is full or not
    public Boolean isFull() {
        return (roster.size() >= maxRosterSize);
    }
 
    // Getters
    public ArrayList<Placeable> getRoster() {
        return this.roster;
    }

    public Set getSet() {
        return set;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Integer> getWinHistory() {
        return this.winHistory;
    }

    

    



}
