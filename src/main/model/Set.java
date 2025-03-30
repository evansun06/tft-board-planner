package model;

import java.io.IOException;
import java.util.HashMap;
import java.io.IOException;
import persistance.JsonReader;
import static org.junit.Assert.*;


// Set represents the predefined champions and traits available in TFT.
// New Sets are introduced to TFT every six months.
public class Set {
    private String name;
    private HashMap<String, ChampionTemplate> placeablesMap;
    
    //EFFECT: creates a set with the given name, and loads the current json file
    //        with all champions in the current set (TFT 13.2)
    public Set(String name) {
        JsonReader reader = new JsonReader("data/setThirteen.json");
        this.name = name;
        placeablesMap = new HashMap<>();
        try {
            reader.loadChampionsToSet(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: Resets the set to have no name 
    //          and no placeable objects.
    public void reset() {
        name = null;
        placeablesMap = new HashMap<>();
    }

    

    // REQUIRES: name corresponds to a valid Placeable
    //           in the arrayList Placeables
    // MODIFIES: none
    // EFFECTS: returns the desired ChampionTemplate within the set
    //          if arraylist is empty or no result return null.
    //          if not ChampionTemplate rather other Placeable return null.
    public ChampionTemplate findChampionTemplate(String name) {
        if (placeablesMap.get(name) instanceof ChampionTemplate) {
            return ((ChampionTemplate)placeablesMap.get(name));
        }
        return null;
    }

    // REQUIRES: template isn't a subclass
    // MODIFIES: this
    // EFFECT: adds a template to a sets available champions
    public void templateAdd(ChampionTemplate template) {
        placeablesMap.put(template.getName(), template);
    }


    //Getters
    public String getName() {
        return this.name;
    }

    public HashMap<String, ChampionTemplate> getPlaceableHashMap() {
        return this.placeablesMap;
    }

    
}
