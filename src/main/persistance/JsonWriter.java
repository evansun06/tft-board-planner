package persistance;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Board;
import model.ChampionInstance;
import model.Placeable;
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

    // EFFECT: write the given json into a file
    public void writePlannerToFile(Planner p) throws FileNotFoundException {
         
    }

    // EFFECT: returns a json object of a planner
    public JSONObject plannerToJson(Planner p) {
        JSONObject json = new JSONObject();
        json.put("boardDeck", boardsToJson(p.getBoardDeck()));
        return json;
    }

    // EFFECT: returns a json array of boards
    public JSONArray boardsToJson(ArrayList<Board> boardDeck) {
        JSONArray boardsJson = new JSONArray();
        for (Board b: boardDeck) {
            boardsJson.put(singleBoardToJson(b));
        }
        return boardsJson;
    }

    // EFFECT: returns a json object of a board
    public JSONObject singleBoardToJson(Board b) {
        JSONObject boardJson = new JSONObject();
        boardJson.put("name", b.getName());
        boardJson.put("roster", rosterToJson(b.getRoster()));
        boardJson.put("history", winHistoryToJson(b.getWinHistory()));
        return boardJson;
    }

    // EFFECT: returns a json array of champion instances
    public JSONArray rosterToJson(ArrayList<Placeable> roster) {
        JSONArray rosterJson = new JSONArray();
        for (Placeable p: roster) {
            ChampionInstance c = (ChampionInstance) p;
            rosterJson.put(championInstanceToJson(c));
        }
        return rosterJson;
    }

    // EFFECT: returns a Json Object of a Champion instance
    public JSONObject championInstanceToJson(ChampionInstance c) {
        // ENDED HERE START FROM HERE AHDIOEHFIOAFHAEIHFAO
        return null;
    }

    //EFFECT: returns a Json Array of a boards win history
    public JSONArray winHistoryToJson(ArrayList<Integer> history) {
        return null;
    }

    // GETTERS
    public String getAddress() {
        return this.address;
    }
}
