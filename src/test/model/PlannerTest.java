package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlannerTest {
    Planner testPlanner;
    Set set13;

    @BeforeEach
    void setUp() {
        testPlanner = new Planner();
        set13 = new Set("Set 13");
        set13.loadSet13();
    }

    @Test
    void constructorTest() {
        assertTrue(testPlanner.getBoardDeck().isEmpty());
    }

    @Test
    void addBoardTest() {
        assertTrue(testPlanner.getBoardDeck().isEmpty());
        testPlanner.addBoard(set13, "MyNewBoard1");
        assertEquals(1, testPlanner.getBoardDeck().size());
        testPlanner.addBoard(set13, "MyNewoard2");
        assertEquals(2, testPlanner.getBoardDeck().size());
    }

    @Test
    void removeBoardTest() {
        assertEquals(0, testPlanner.getBoardDeck().size());
        testPlanner.addBoard(set13, "MyNewBoard1");
        testPlanner.addBoard(set13, "MyNewBoard2");
        assertEquals(2, testPlanner.getBoardDeck().size());
        testPlanner.removeBoard("MyNewBoard1");
        assertEquals(1, testPlanner.getBoardDeck().size());
    }
}

