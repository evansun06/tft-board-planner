package persistance;

import java.io.FileNotFoundException;

import org.json.JSONObject;

// Represents a writer that writes a Planner Object into JSON
// Capturing a Planner's ArrayList<Board>, a Board's ArrayList<ChampionInstance>
// Note: This class partially uses code from CPSC 210's JsonSerializationDemo.
public class JsonWriter {
    
    // EFFECT: Sets the relative address to write JSON
    public JsonWriter(String address) {

    }

    // MODIFIES: this
    // EFFECT: opens a file with the given relative address
    //         throws FileNoteFoundException if invalid address.
    public void open(String address) throws FileNotFoundException {

    }

    // EFFECT: Captures a Planner Object into JSON file at the relative address
    public void writePlannerToJson() {

    }

    // GETTERS
    public String getAddress() {
        return "";
    }
}
