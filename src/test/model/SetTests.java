package model;

import static org.junit.Assert.*;

import java.io.IOException;

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
        assertTrue(!testSet.getPlaceableHashMap().isEmpty());
    }

    @Test 
    void constructorTestForceIOException() {
        assertThrows(AssertionError.class, () -> {
        new Set("ForceException");
    });

    }

    @Test
    void resetTest() {
        assertEquals("Set 0", testSet.getName());
        assertTrue(!testSet.getPlaceableHashMap().isEmpty());
        assertEquals(33, testSet.getPlaceableHashMap().size());
        testSet.reset();
        assertTrue(testSet.getPlaceableHashMap().isEmpty());
        assertNull(testSet.getName());
    }

    @Test
    void findChampionTemplateTest() {
        testSet.reset();
        //Search on empty list
        assertNull(testSet.findChampionTemplate("Leona"));

        //Test Single ChampionTemplate
        testSet.templateAdd(jayce);
        assertTrue(testSet.findChampionTemplate("Jayce") instanceof ChampionTemplate);
        assertEquals(jayce, testSet.findChampionTemplate("Jayce"));
        assertNull(testSet.findChampionTemplate("Leona"));
        //Test Multiple ChampionTemplate Search
        testSet.templateAdd(leona);
        assertEquals(jayce, testSet.findChampionTemplate("Jayce"));
        assertEquals(leona, testSet.findChampionTemplate("Leona"));
        assertNull(testSet.findChampionTemplate("Jax"));
        


    }

    @Test 
    void addTemplateTest() {
        testSet.reset();
        assertTrue(testSet.getPlaceableHashMap().isEmpty());
        testSet.templateAdd(jayce);
        assertEquals(jayce, testSet.findChampionTemplate("Jayce"));
        assertEquals(1, testSet.getPlaceableHashMap().size());

    }

    




}
