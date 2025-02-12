package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetTests {
    Set testSet;
    ChampionTemplate jayce;
    ChampionTemplate leona;

    @BeforeEach
    void setUp() {
        testSet = new Set("Set 0");
        jayce = new ChampionTemplate("Jayce", 100, 10, 10, 30, 15, 0, 100, 10, 5, 5);
        leona = new ChampionTemplate("Leona", 200, 30, 30, 5, 5, 0, 60, 10, 1, 2);
    }

    @Test 
    void constructorTest() {
        assertEquals("Set 0", testSet.getName());
        assertTrue(testSet.getPlaceables().isEmpty());
    }

    @Test
    void resetTest() {
        assertEquals("Set 0", testSet.getName());
        assertTrue(testSet.getPlaceables().isEmpty());
        testSet.templateAdd(jayce);

        assertEquals(1, testSet.getPlaceables().size());
        testSet.reset();
        assertTrue(testSet.getPlaceables().isEmpty());
        assertNull(testSet.getName());
    }

    @Test
    void findPlaceableTest() {
        //Search on empty list
        assertNull(testSet.findPlaceable("Leona"));

        //Test Single Template
        testSet.templateAdd(jayce);
        assertEquals(jayce, testSet.findPlaceable("Jayce"));
        assertNull(testSet.findPlaceable("Leona"));
        //Test Multiple Template Search
        testSet.templateAdd(leona);
        assertEquals(jayce, testSet.findPlaceable("Jayce"));
        assertEquals(leona, testSet.findPlaceable("Leona"));
        assertNull(testSet.findPlaceable("Jax"));

    }

    @Test 
    void addTemplateTest() {
        assertTrue(testSet.getPlaceables().isEmpty());
        testSet.templateAdd(jayce);
        assertEquals(jayce, testSet.findPlaceable("Jayce"));
        assertEquals(1, testSet.getPlaceables().size());
    }

    @Test
    void loadSet13Test() {
        testSet.loadSet13();
        assertEquals(40, testSet.getPlaceables().size());
        assertEquals("Lux", testSet.findPlaceable("Lux").name);
        

    }




}
