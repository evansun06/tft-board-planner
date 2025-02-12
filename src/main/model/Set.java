package model;

import java.util.ArrayList;

// Set represents the predefined champions and traits available in TFT.
// New Sets are introduced to TFT every six months.
public class Set {
    private String name;
    private ArrayList<Placeable> placeables;

    public Set(String name) {
        this.name = name;
        placeables = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Resets the set to have no name 
    //          and no placeable objects.
    public void reset() {
        name = null;
        placeables = new ArrayList<>();
    }

    

    // REQUIRES: name corresponds to a valid Placeable
    //           in the arrayList Placeables
    // MODIFIES: none
    // EFFECTS: returns the desired placeable within the set
    //          if arraylist is empty or no result return null.
    public Placeable findPlaceable(String name) {
        if (!placeables.isEmpty()) {
            for (Placeable temp: placeables){
                if (name == temp.getName()) {
                    return temp;
                }
            }
        }

        return null;
    }

    // REQUIRES: template isn't a subclass
    // MODIFIES: this
    // EFFECT: adds a template to a sets available champions
    public void templateAdd(ChampionTemplate template) {
        placeables.add(template);
    }



    //Getters
    public String getName() {
        return this.name;
    }

    public ArrayList<Placeable> getPlaceables () {
        return this.placeables;
    }

    // MODIFIES: this
    // EFFECTS: Loads in Set Thirteen, an official set.
    private void loadSet13() {
        

        

    }

    
    
}
