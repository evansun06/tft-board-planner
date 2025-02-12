package model;

import java.util.ArrayList;

// Represents a single 7 x 4 Board playground in which
// Champions can be placed;
public class Board {
    public static final int xMin = 0;
    public static final int xMax = 7;
    public static final int yMin = 0;
    public static final int yMax = 3;
    public final int maxRosterSize = 10;

    private final Set set;
    private ArrayList<ChampionInstance> roster;


    // EFFECT: Creates blank board for a specific Set(version)
    //         with an empty roster.  
    public Board(Set version) {
        this.set = version;
        this.roster = new ArrayList<>();
    }

    // EFFECT: Creats an blank board with unknown set.
    public Board() {
        this.roster = new ArrayList<>();
        this.set = null;
    }

    // REQUIRES: Base stat champion with name != null
    //           Champ must be within the set
    // MODIFIES: this
    // EFFECT: Adds a new champion instance to the roster.
    //         Does nothing if roster is full.
    public void addToRoster(ChampionInstance champ) {
        
    }

    // REQUIRES: Valid id of a champion on the roster
    // MODIFIES: this
    // EFFECT: Removes the corresponding champion from the
    //         roster.
    public void addToRoster(int id) {
        
    }
    

    // Getters
    public ArrayList<ChampionInstance> getRoster() {
        return (this.roster);
    }

    public Set getSet() {
        return this.set;
    }
    



}
