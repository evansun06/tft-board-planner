package model;

import java.util.ArrayList;
import java.util.HashMap;

// Set represents the predefined champions and traits available in TFT.
// New Sets are introduced to TFT every six months.
public class Set {
    private String name;
    private HashMap<String, Placeable> placeablesMap;
    

    public Set(String name) {
        this.name = name;
        placeablesMap = new HashMap<>();
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
    public void templateAdd(Placeable template) {
        placeablesMap.put(template.getName(), template);
    }


    //Getters
    public String getName() {
        return this.name;
    }

    public HashMap<String, Placeable> getPlaceableHashMap() {
        return this.placeablesMap;
    }

    // MODIFIES: this
    // EFFECTS: Loads in Set Thirteen, an official set.
    @SuppressWarnings("methodlength")
    public void loadSet13() {
        templateAdd(new ChampionTemplate("Akali", 1000, 30, 20, 50, 0, 25, 200, 0.8, 1, 2));
        templateAdd(new ChampionTemplate("Ambessa", 1100, 40, 25, 60, 0, 20, 180, 0.75, 1, 4));
        templateAdd(new ChampionTemplate("Amumu", 1200, 50, 30, 40, 0, 15, 150, 0.7, 1, 1));
        templateAdd(new ChampionTemplate("Blitzcrank", 1300, 60, 35, 70, 0, 10, 140, 0.65, 2, 3));
        templateAdd(new ChampionTemplate("Caitlyn", 800, 20, 15, 80, 0, 30, 220, 0.9, 4, 5));
        templateAdd(new ChampionTemplate("Camille", 1000, 35, 25, 65, 0, 20, 190, 0.75, 1, 2));
        templateAdd(new ChampionTemplate("Cassiopeia", 900, 25, 30, 50, 80, 20, 170, 0.85, 3, 3));
        templateAdd(new ChampionTemplate("Corki", 950, 30, 20, 70, 0, 25, 210, 0.8, 3, 4));
        templateAdd(new ChampionTemplate("Darius", 1200, 50, 30, 90, 0, 15, 160, 0.65, 1, 1));
        templateAdd(new ChampionTemplate("DrMundo", 1500, 60, 40, 50, 0, 10, 130, 0.6, 1, 4));
        templateAdd(new ChampionTemplate("Draven", 900, 25, 20, 100, 0, 35, 240, 0.95, 3, 1));
        templateAdd(new ChampionTemplate("Ekko", 950, 30, 25, 60, 40, 25, 180, 0.8, 1, 4));
        templateAdd(new ChampionTemplate("Elise", 850, 20, 20, 50, 50, 20, 160, 0.85, 1, 4));
        templateAdd(new ChampionTemplate("Ezreal", 900, 25, 20, 75, 0, 30, 200, 0.9, 3, 3));
        templateAdd(new ChampionTemplate("Gangplank", 1100, 35, 25, 70, 0, 20, 190, 0.75, 1, 3));
        templateAdd(new ChampionTemplate("Garen", 1400, 60, 35, 80, 0, 15, 140, 0.7, 1, 4));
        templateAdd(new ChampionTemplate("Heimerdinger", 850, 20, 30, 50, 90, 20, 170, 0.85, 4, 4));
        templateAdd(new ChampionTemplate("Illaoi", 1200, 45, 25, 80, 0, 20, 180, 0.7, 1, 4));
        templateAdd(new ChampionTemplate("Irelia", 1100, 35, 25, 90, 0, 25, 200, 0.85, 1, 1));
        templateAdd(new ChampionTemplate("Jayce", 1000, 40, 20, 70, 0, 20, 180, 0.75, 2, 5));
        templateAdd(new ChampionTemplate("Jinx", 900, 25, 20, 85, 0, 30, 210, 0.9, 4, 5));
        templateAdd(new ChampionTemplate("Kogmaw", 950, 20, 30, 65, 90, 25, 200, 0.85, 4, 3));
        templateAdd(new ChampionTemplate("Leblanc", 800, 20, 25, 40, 95, 30, 200, 0.9, 3, 5));
        templateAdd(new ChampionTemplate("Leona", 1400, 60, 40, 50, 0, 15, 150, 0.65, 1, 2));
        templateAdd(new ChampionTemplate("Loris", 1100, 45, 25, 60, 0, 20, 180, 0.75, 1, 3));
        templateAdd(new ChampionTemplate("Lux", 900, 25, 30, 50, 100, 20, 190, 0.9, 3, 1));
        templateAdd(new ChampionTemplate("Maddie", 1000, 30, 25, 70, 0, 20, 180, 0.8, 1, 1));
        templateAdd(new ChampionTemplate("Malzahar", 850, 20, 30, 50, 80, 25, 170, 0.85, 3, 5));
        templateAdd(new ChampionTemplate("Mel", 950, 30, 20, 65, 0, 20, 200, 0.8, 2, 6));
        templateAdd(new ChampionTemplate("Mordekaiser", 1400, 60, 35, 90, 0, 15, 160, 0.65, 1, 5));
        templateAdd(new ChampionTemplate("Morgana", 900, 25, 30, 50, 90, 20, 190, 0.85, 3, 1));
        templateAdd(new ChampionTemplate("Nami", 800, 20, 30, 40, 100, 20, 200, 0.9, 3, 3));
        templateAdd(new ChampionTemplate("Nocturne", 1000, 35, 25, 80, 0, 25, 200, 0.8, 1, 2));
        templateAdd(new ChampionTemplate("Nunu", 1300, 50, 30, 60, 0, 20, 180, 0.75, 1, 3));
        templateAdd(new ChampionTemplate("Powder", 850, 20, 20, 75, 0, 30, 210, 0.9, 4, 1));
        templateAdd(new ChampionTemplate("Rell", 1200, 60, 35, 70, 0, 15, 150, 0.65, 1, 2));
        templateAdd(new ChampionTemplate("Renata", 900, 25, 30, 50, 80, 20, 170, 0.85, 3, 2));
        templateAdd(new ChampionTemplate("Ziggs", 800, 20, 30, 40, 100, 20, 200, 0.9, 3, 2));
        templateAdd(new ChampionTemplate("Zoe", 850, 20, 25, 50, 90, 25, 200, 0.85, 3, 4));
        templateAdd(new ChampionTemplate("Zyra", 900, 25, 30, 50, 100, 20, 190, 0.9, 3, 1));
    }

    
    
}
