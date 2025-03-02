package persistance;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import model.Board;
import model.ChampionInstance;
import model.Planner;


public class JsonWriterTest {
    
    @Test
    void constructorTest() {
        JsonWriter writer = new JsonWriter("address");
        assertEquals("address", writer.getAddress());
    }

    @Test
    void faultyAddressTest() {
        JsonWriter writer = new JsonWriter("no address");
        try {
            writer.open("./data/my\0illegal:fileName.json");
            fail("Should throw exception");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void correctAddressEmptyPlannerToFile() {
        JsonWriter writer = new JsonWriter("data/test.json");
        Planner testplanner = new Planner();
        assertTrue(testplanner.getBoardDeck().isEmpty());
        assertEquals("data/test.json", writer.getAddress());
        try {
            writer.writePlannerToFile(testplanner);
        } catch (Exception e) {
            fail("shouldWriteProperly");
        }
        JsonReader reader = new JsonReader("data/test.json");
        try {
            Planner retrievedPlanner = reader.plannerJsonToObject();
            assertTrue(retrievedPlanner.getBoardDeck().isEmpty());
        } catch (Exception e) {
            fail("shouldReadProperly");
        }
    }

    @Test
    @SuppressWarnings("methodlength")
    void correctAddressNonEmptyPlannerToFile() {
        //Set Up
        JsonWriter writer = new JsonWriter("data/test.json");
        Planner testplanner = new Planner();
        testplanner.addBoard("TestBoard");
        Board testBoard = testplanner.getBoard("TestBoard");
        testBoard.addChampionToBoard(testBoard.getSet().findChampionTemplate("Jayce"), 0, 0);
        testBoard.addToHistory(1);
        testBoard.addToHistory(2);
        assertEquals("data/test.json", writer.getAddress());
        try {
            writer.writePlannerToFile(testplanner);
        } catch (Exception e) {
            fail("shouldWriteProperly");
        }
        JsonReader reader = new JsonReader("data/test.json");
        try {
            Planner retrievedPlanner = reader.plannerJsonToObject();
            assertFalse(retrievedPlanner.getBoardDeck().isEmpty());
            assertEquals(1, retrievedPlanner.getBoardDeck().size());
            Board retrievedBoard = retrievedPlanner.getBoard("TestBoard");
            assertEquals("TestBoard", retrievedBoard.getName());
            ChampionInstance retrievedJayce = retrievedBoard.getChampionFromBoard(0, 0);
            assertEquals("Jayce", retrievedJayce.getName());
            assertEquals(0, retrievedJayce.getAbilityPower());
            assertEquals(2,retrievedBoard.getWinHistory().size());   
        } catch (Exception e) {
            fail("shouldReadProperly");
        }
    }
    
}
