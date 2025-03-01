package persistance;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Set;

// Used parts of JsonSerilizationDemo.JSONReaderTest UBC 210
public class JsonReaderTest {
    JsonReader testSetLoader;
    Set testSet;

    @BeforeEach
    void setUp() {
        testSetLoader = new JsonReader("data/setThirteen.json");
        testSet = new Set("String");
    }

    @Test
    void constructorTest() {
        assertEquals("data/setThirteen.json", testSetLoader.getAddress());
    }

    @Test
    void readJsonSetFaultyAddressTest() {
        testSetLoader = new JsonReader("data/noSuchThing.json");
        try {
            testSetLoader.readJson();
            fail("exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readJsonSetCorrectAddressTest() {
        testSetLoader.setAddress("data/setThirteen.json");
        String json = "";
        try {
             json = testSetLoader.readJson();
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
            testSetLoader.loadChampionsToSet(testSet);
        } catch (IOException e) {
            fail("Exception not expected");
        }
        assertEquals("Ambessa", testSet.findChampionTemplate("Ambessa").getName());
        assertEquals(1100, testSet.findChampionTemplate("Ambessa").getHealth());
    }


    
    

    
}
