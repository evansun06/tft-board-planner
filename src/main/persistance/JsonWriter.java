package persistance;

import java.io.FileNotFoundException;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Board;
import model.ChampionInstance;
import model.Planner;

// Represents a writer that writes a Planner Object into JSON
// Capturing a Planner's ArrayList<Board>, a Board's ArrayList<ChampionInstance>
// Note: This class partially uses code from CPSC 210's JsonSerializationDemo.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String address;
    
    // EFFECT: Sets the relative address to write JSON
    public JsonWriter(String address) {
        this.address = address;
    }

    // MODIFIES: this
    // EFFECT: opens a file with the given relative address
    //         throws FileNoteFoundException if invalid address.
    public void open(String address) throws FileNotFoundException {
        writer = new PrintWriter(new File(address));
    }

    // EFFECT: Captures a Planner Object into JSON file at the relative address
    public void writeToJson() {
         
    }

    // EFFECT: returns a json object of a planner
    public JSONObject plannerToJson(Planner p) {
        return null;
    }

    // EFFECT: returns a json array of boards
    public JSONArray boardsToJson(Board b) {
        return null;
    }

    // EFFECT: returns a json array of champion instances
    public JSONArray rosterToJson(ChampionInstance c) {
        return null;
    }

    

    // GETTERS
    public String getAddress() {
        return "";
    }
}
