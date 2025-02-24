package persistance;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.imageio.IIOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Set;

// Used parts of JsonSerilizationDemo.JSONReaderTest UBC 210
public class SetLoaderTest {
    SetLoader testSetLoader;
    Set testSet;

    @BeforeEach
    void setUp() {
        testSetLoader = new SetLoader("data/setThirteenChampions.json");
        testSet = new Set("String");
    }

    @Test
    void constructorTest() {
        assertEquals("data/setThirteenChampions.JSON", testSetLoader.getAddress());
    }

    @Test
    
    void loadChampionsToSetFaultyAddressTest() {
        testSetLoader = new SetLoader("data/noSuchThing.json");
        try {
            testSetLoader.loadChampionsToSet(testSet);
            fail("exception expected");
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    void loadChampionsToSetCorrectAddresTest() {
        assertNull(testSet.findChampionTemplate("Ahri").getName());

        try {
            testSetLoader.loadChampionsToSet(testSet);
        } catch (Exception e) {
            fail("Exception not expected");
        }
        
        assertEquals("Ahri", testSet.findChampionTemplate("Ahri").getName());

    }


    
    

    
}
