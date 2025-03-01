package persistance;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Set;

// Used parts of JsonSerilizationDemo.JSONReaderTest UBC 210
public class JsonReaderTest {
    JsonReader reader;
    Set testSet;

    @BeforeEach
    void setUp() {
        reader = new JsonReader("data/setThirteen.json");
        testSet = new Set("String");
    }

    @Test
    void constructorTest() {
        assertEquals("data/setThirteen.json", reader.getAddress());
    }

    @Test
    void readJsonSetFaultyAddressTest() {
        reader = new JsonReader("data/noSuchThing.json");
        try {
            reader.readJson();
            fail("exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readJsonSetCorrectAddressTest() {
        reader.setAddress("data/setThirteen.json");
        String json = "";
        try {
             json = reader.readJson();
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
            reader.loadChampionsToSet(testSet);
        } catch (IOException e) {
            fail("Exception not expected");
        }
        assertEquals("Ambessa", testSet.findChampionTemplate("Ambessa").getName());
        assertEquals(1100, testSet.findChampionTemplate("Ambessa").getHealth());
    }

    @Test
    void loadPlannerToWrongAddress() {
        
    }
    
    

    
}
