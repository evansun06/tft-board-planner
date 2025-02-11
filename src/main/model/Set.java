package model;

import java.util.ArrayList;

// Set represents the predefined champions and traits available in TFT.
// New Sets are introduced to TFT every six months.
public class Set {
    private String name;
    private ArrayList<Placeable> placeables;

    public Set() {
        placeables = new ArrayList<>();
    }

    private void loadSet() {
        reset();

    }

    // MODIFIES: this
    // EFFECTS: Resets the set to have no name 
    //          and no placeable objects.
    private void reset() {
        name = null;
        placeables = new ArrayList<>();
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public ArrayList<Placeable> getPlaceables () {
        return this.placeables;
    }

    
    
}
