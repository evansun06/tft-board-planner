package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlannerTest {
    Planner testPlanner;

    @BeforeEach
    void setUp() {
        testPlanner = new Planner();
    }

    @Test
    void constructorTest() {
        assertTrue(testPlanner.getBoardDeck().isEmpty());
    }

    @Test
    void addBoardTest() {
        assertTrue(testPlanner.getBoardDeck().isEmpty());
        testPlanner.addBoard( "MyNewBoard1");
        assertEquals(1, testPlanner.getBoardDeck().size());
        testPlanner.addBoard( "MyNewoard2");
        assertEquals(2, testPlanner.getBoardDeck().size());
    }

    @Test
    void removeBoardTest() {
        assertEquals(0, testPlanner.getBoardDeck().size());
        testPlanner.addBoard( "MyNewBoard1");
        testPlanner.addBoard( "MyNewBoard2");
        assertEquals(2, testPlanner.getBoardDeck().size());
        testPlanner.removeBoard("MyNewBoard1");
        assertEquals(1, testPlanner.getBoardDeck().size());
    }

    @Test
    void getBoardTest() {
        assertNull(testPlanner.getBoard("My board"));
        testPlanner.addBoard( "MyFirstSet");
        testPlanner.addBoard( "SecondSet");
        assertNull(testPlanner.getBoard("thirdset"));
        assertEquals("SecondSet",testPlanner.getBoard("SecondSet").getName());
    }
}

