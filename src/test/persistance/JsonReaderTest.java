package persistance;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;
import model.Planner;
import model.Set;

// Used parts of JsonSerilizationDemo.JSONReaderTest UBC 210
public class JsonReaderTest {
    JsonReader testReader;
    Set testSet;

    @BeforeEach
    void setUp() {
        testReader = new JsonReader("data/setThirteen.json");
        testSet = new Set("String");
    }

    @Test
    void constructorTest() {
        assertEquals("data/setThirteen.json", testReader.getAddress());
    }

    @Test
    void readJsonSetFaultyAddressTest() {
        testReader = new JsonReader("data/noSuchThing.json");
        try {
            testReader.readJson();
            fail("exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readJsonSetCorrectAddressTest() {
        testReader.setAddress("data/setThirteen.json");
        String json = "";
        try {
             json = testReader.readJson();
        } catch (IOException e) {
            fail("proper read expected");
        }
        assertFalse(json == null);
    }

    @Test
    void loadChampionsToSetCorrectAddressTest() {
        testSet.reset();
        assertNull(testSet.findChampionTemplate("Ambessa"));

        try {
            testReader.loadChampionsToSet(testSet);
        } catch (IOException e) {
            fail("Exception not expected");
        }
        assertEquals("Ambessa", testSet.findChampionTemplate("Ambessa").getName());
        assertEquals(1100, testSet.findChampionTemplate("Ambessa").getHealth());
    }

    @Test
    void loadPlannerToWrongAddress() {
        try {
            testReader.setAddress("dummyAddress");
            testReader.plannerJsonToObject();
            fail("Should throw IOException");
        } catch (IOException e) {
            // pass
        }
    }
    
    @Test
    void loadPlannerCorrectAddress() {
        Planner retrievedPlanner;
        Board retrievedBoard;
        try {
            testReader.setAddress("data/testPlanner.json");
            retrievedPlanner = testReader.plannerJsonToObject();
            assertEquals(2, retrievedPlanner.getBoardDeck().size());
            retrievedBoard = retrievedPlanner.getBoard("TestBoard");
            assertTrue(retrievedBoard != null);
            assertEquals("Jayce", retrievedBoard.getChampionFromBoard(0, 0).getName());
            assertEquals(1, retrievedBoard.getChampionFromBoard(0, 0).getInstanceId());
        } catch (Exception e) {
            fail("should not fail");
        }
    }
    
    

    
}
