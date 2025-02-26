package persistance;

import model.Set;
import java.io.IOException;

// Set loader will read a constant JSON file with all the premade champions available.
// This reduces startup runtime.
public class SetLoader {
    

    // EFFECT: Assign set to the object and provides a relative file address 
    // MODIFIES: this
    public SetLoader(String address) {

    }

    public String readJson() throws IOException {
        return "";
    }

    // EFFECT: Load Champions in the JSON to set
    // MODIFIES: set
    // REQUIRES: A properly formatted JSON file.
    public void loadChampionsToSet(Set set) throws IOException {

    }



    // GETTERS
    public String getAddress() {
        return "";
    }

    public Set getSet() {
        return null;
    }   
}
