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
            for (Placeable temp: placeables) {
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

    public ArrayList<Placeable> getPlaceables() {
        return this.placeables;
    }

    // MODIFIES: this
    // EFFECTS: Loads in Set Thirteen, an official set.
    @SuppressWarnings("methodlength")
    public void loadSet13() {
        placeables.add(new ChampionTemplate("Akali", 1000, 30, 20, 50, 0, 25, 200, 0.8, 1, 2));
        placeables.add(new ChampionTemplate("Ambessa", 1100, 40, 25, 60, 0, 20, 180, 0.75, 1, 4));
        placeables.add(new ChampionTemplate("Amumu", 1200, 50, 30, 40, 0, 15, 150, 0.7, 1, 1));
        placeables.add(new ChampionTemplate("Blitzcrank", 1300, 60, 35, 70, 0, 10, 140, 0.65, 2, 3));
        placeables.add(new ChampionTemplate("Caitlyn", 800, 20, 15, 80, 0, 30, 220, 0.9, 4, 5));
        placeables.add(new ChampionTemplate("Camille", 1000, 35, 25, 65, 0, 20, 190, 0.75, 1, 2));
        placeables.add(new ChampionTemplate("Cassiopeia", 900, 25, 30, 50, 80, 20, 170, 0.85, 3, 3));
        placeables.add(new ChampionTemplate("Corki", 950, 30, 20, 70, 0, 25, 210, 0.8, 3, 4));
        placeables.add(new ChampionTemplate("Darius", 1200, 50, 30, 90, 0, 15, 160, 0.65, 1, 1));
        placeables.add(new ChampionTemplate("DrMundo", 1500, 60, 40, 50, 0, 10, 130, 0.6, 1, 4));
        placeables.add(new ChampionTemplate("Draven", 900, 25, 20, 100, 0, 35, 240, 0.95, 3, 1));
        placeables.add(new ChampionTemplate("Ekko", 950, 30, 25, 60, 40, 25, 180, 0.8, 1, 4));
        placeables.add(new ChampionTemplate("Elise", 850, 20, 20, 50, 50, 20, 160, 0.85, 1, 4));
        placeables.add(new ChampionTemplate("Ezreal", 900, 25, 20, 75, 0, 30, 200, 0.9, 3, 3));
        placeables.add(new ChampionTemplate("Gangplank", 1100, 35, 25, 70, 0, 20, 190, 0.75, 1, 3));
        placeables.add(new ChampionTemplate("Garen", 1400, 60, 35, 80, 0, 15, 140, 0.7, 1, 4));
        placeables.add(new ChampionTemplate("Heimerdinger", 850, 20, 30, 50, 90, 20, 170, 0.85, 4, 4));
        placeables.add(new ChampionTemplate("Illaoi", 1200, 45, 25, 80, 0, 20, 180, 0.7, 1, 4));
        placeables.add(new ChampionTemplate("Irelia", 1100, 35, 25, 90, 0, 25, 200, 0.85, 1, 1));
        placeables.add(new ChampionTemplate("Jayce", 1000, 40, 20, 70, 0, 20, 180, 0.75, 2, 5));
        placeables.add(new ChampionTemplate("Jinx", 900, 25, 20, 85, 0, 30, 210, 0.9, 4, 5));
        placeables.add(new ChampionTemplate("Kogmaw", 950, 20, 30, 65, 90, 25, 200, 0.85, 4, 3));
        placeables.add(new ChampionTemplate("Leblanc", 800, 20, 25, 40, 95, 30, 200, 0.9, 3, 5));
        placeables.add(new ChampionTemplate("Leona", 1400, 60, 40, 50, 0, 15, 150, 0.65, 1, 2));
        placeables.add(new ChampionTemplate("Loris", 1100, 45, 25, 60, 0, 20, 180, 0.75, 1, 3));
        placeables.add(new ChampionTemplate("Lux", 900, 25, 30, 50, 100, 20, 190, 0.9, 3, 1));
        placeables.add(new ChampionTemplate("Maddie", 1000, 30, 25, 70, 0, 20, 180, 0.8, 1, 1));
        placeables.add(new ChampionTemplate("Malzahar", 850, 20, 30, 50, 80, 25, 170, 0.85, 3, 5));
        placeables.add(new ChampionTemplate("Mel", 950, 30, 20, 65, 0, 20, 200, 0.8, 2, 6));
        placeables.add(new ChampionTemplate("Mordekaiser", 1400, 60, 35, 90, 0, 15, 160, 0.65, 1, 5));
        placeables.add(new ChampionTemplate("Morgana", 900, 25, 30, 50, 90, 20, 190, 0.85, 3, 1));
        placeables.add(new ChampionTemplate("Nami", 800, 20, 30, 40, 100, 20, 200, 0.9, 3, 3));
        placeables.add(new ChampionTemplate("Nocturne", 1000, 35, 25, 80, 0, 25, 200, 0.8, 1, 2));
        placeables.add(new ChampionTemplate("Nunu", 1300, 50, 30, 60, 0, 20, 180, 0.75, 1, 3));
        placeables.add(new ChampionTemplate("Powder", 850, 20, 20, 75, 0, 30, 210, 0.9, 4, 1));
        placeables.add(new ChampionTemplate("Rell", 1200, 60, 35, 70, 0, 15, 150, 0.65, 1, 2));
        placeables.add(new ChampionTemplate("Renata", 900, 25, 30, 50, 80, 20, 170, 0.85, 3, 2));
        placeables.add(new ChampionTemplate("Ziggs", 800, 20, 30, 40, 100, 20, 200, 0.9, 3, 2));
        placeables.add(new ChampionTemplate("Zoe", 850, 20, 25, 50, 90, 25, 200, 0.85, 3, 4));
        placeables.add(new ChampionTemplate("Zyra", 900, 25, 30, 50, 100, 20, 190, 0.9, 3, 1));
    }

    
    
}
