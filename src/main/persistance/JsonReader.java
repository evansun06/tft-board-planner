package persistance;

import model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;

// JsonReader is multip functional object that can read json files.
// Functionality includes loading set champions that are integral to the app,
// also able to load previous board jsons.
// This reduces startup runtime.
public class JsonReader {
    private String address;

    // EFFECT: Assign set to the object and provides a relative file address 
    // MODIFIES: this
    public JsonReader(String address) {
        this.address = address;
    }

    // EFFECT: Convert JSON file into a string with a given relative path.
    //         thows IOException if not valid location.
    public String readJson() throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(this.address), StandardCharsets.UTF_8)) {
            stream.forEach(string -> builder.append(string));
        } 
        return builder.toString();
    }


    // EFFECT: Load Champions in the JSON to set.
    //       : Throws IOExcepiton if:
    //          1. Address is invalid
    //          2. Wrong JSON file
    // MODIFIES: set
    public void loadChampionsToSet(Set set) throws IOException {
        setAddress("data/setThirteen.json");
        String jsonData = readJson();
        JSONObject setThirteenJson = new JSONObject(jsonData);
        JSONArray championJsonArray = setThirteenJson.getJSONArray("champions");


        //This is for testing in the Set Class.
        if (set.getName() == "ForceException") {
            throw new IOException();
        }
        for (Object championJson: championJsonArray) {
            JSONObject champ = (JSONObject) championJson;
            addChampion(set, champ);
        }
    }

    // EFFECT: helper for loadChampionsToSet
    //         loads a single ChampionTemplate to the Set
    public void addChampion(Set set, JSONObject c) {
        ChampionTemplate template = new ChampionTemplate(
                c.getString("name"),
                c.getInt("health"),
                c.getInt("ar"),
                c.getInt("mr"),
                c.getInt("ad"),
                c.getInt("ap"),
                c.getInt("crit%"),
                c.getInt("critX"),
                c.getInt("atspd"),
                c.getInt("range"),
                c.getInt("cost")
        );
        set.templateAdd(template);
    }

    // REQUIRES: Json to be properly formatted for a planner
    // EFFECT: reads data at the given address and returns a planner object
    //         throws if invalid relative address
    public Planner plannerJsonToObject() throws IOException {
        Planner retrievedPlanner = new Planner();
        String stringJson = readJson();
        JSONObject plannerJson = new JSONObject(stringJson);
        JSONArray boardDeckJson = plannerJson.getJSONArray("boardDeck");

        for (Object board: boardDeckJson) {
            JSONObject boardJson = (JSONObject) board;
            retrievedPlanner.getBoardDeck().add(boardJsonToObject(boardJson));
        }
        return retrievedPlanner;
    }

    // REQUIRES: JSONObject be formatted for Board
    // EFFECT: produces a Board object from a valid JSONObject
    public Board boardJsonToObject(JSONObject b) {
        Board retrievedBoard = new Board(b.getString("name"));
        JSONArray rosterJson = b.getJSONArray("roster");
        JSONArray historyJson = b.getJSONArray("history");
        
        for (Object champ: rosterJson) {
            JSONObject champJson = (JSONObject) champ;
            String champName = champJson.getString("name");
            ChampionTemplate template = retrievedBoard.getSet().findChampionTemplate(champName);
            template.setReadilyPlaceable(true);
            int x = champJson.getInt("x");
            int y = champJson.getInt("y");
            retrievedBoard.addChampionToBoard(template, x, y);
        }
        for (int i = 0; i < historyJson.length(); i++) {
            retrievedBoard.addToHistory(historyJson.getInt(i));
        }
        return retrievedBoard;
    }



    // EFFECT: changes address
    public void setAddress(String address) {
        this.address = address;
    }


    // GETTERS
    public String getAddress() {
        return this.address;
    }
  
}
