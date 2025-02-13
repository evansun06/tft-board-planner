package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTests {
    Board testBoard;
    Set setThirteen;

    @BeforeEach
    void setUp() {
        setThirteen = new Set("Set13");
        setThirteen.loadSet13();
        testBoard = new Board(setThirteen, "MyFirstBoard");
    }

    @Test
    void constructorTest() {
        assertTrue(testBoard.getRoster().isEmpty());
        assertTrue(testBoard.getWinHistory().isEmpty());
        assertEquals("MyFirstBoard", testBoard.getName());
    }

    @Test
    void addSingleChampionToBoardTest() {
        //Ensure Proper findChampionTempalte returns
        assertTrue(setThirteen.findChampionTemplate("Jayce") instanceof ChampionTemplate);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Jayce"), 0, 0);
        assertEquals(1, testBoard.getRoster().size());
        //Ensure that roster is composed of ChampionInstance
        assertTrue(testBoard.getRoster().get(0) instanceof ChampionInstance);
        assertEquals(testBoard.getChampionFromBoard(0, 0), testBoard.getRoster().get(0));
    }

    @Test
    void ensureOnlyReadilyPlaceableChampionsAreAdded() {
        //Ensure only readilyPlaceable champions are added
        
        ChampionTemplate notReadilyPlaceable = setThirteen.findChampionTemplate("Corki");
        assertEquals(0, testBoard.getRoster().size());
        notReadilyPlaceable.readilyPlaceable = false;
        testBoard.addChampionToBoard(notReadilyPlaceable, 0, 0);
        assertEquals(0, testBoard.getRoster().size());
    }

    @Test
    void addMultipleChampionToBoardTest() {
        //Placing different champion on same location
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Jayce"), 0, 0);
        assertEquals(1, testBoard.getRoster().size());
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Camille"), 0, 0);
        assertEquals(1, testBoard.getRoster().size());
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Camille"), 3, 0);
        assertEquals(2, testBoard.getRoster().size());

        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 1, 0);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 2, 0);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 4, 0);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Camille"), 5, 0);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Camille"), 6, 0);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 1, 1);
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 2, 1);
        assertEquals(9, testBoard.getRoster().size());
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 4, 1);
        assertEquals(10, testBoard.getRoster().size());

        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 4, 2);
        assertEquals(10, testBoard.getRoster().size());
    }

    @Test
    void removeChampionFromRoster() {
        testBoard.addChampionToBoard(testBoard.getSet().findChampionTemplate("Corki"), 0, 0);
        testBoard.addChampionToBoard(testBoard.getSet().findChampionTemplate("Camille"), 1, 0);
        assertEquals(2, testBoard.getRoster().size());
        testBoard.removeChampionFromRoster(0, 0);
        assertEquals(1, testBoard.getRoster().size());
        assertEquals("Camille", testBoard.getRoster().get(0).getName());

        testBoard.removeChampionFromRoster(3, 3);
        assertEquals(1, testBoard.getRoster().size());
    }

    @Test 
    void locationIsEmptyTest() {
        testBoard.addChampionToBoard(setThirteen.findChampionTemplate("Corki"), 3, 3);
        assertTrue(testBoard.locationIsEmpty(1, 3));
        assertFalse(testBoard.locationIsEmpty(3, 3));
    }

    @Test 
    void getChampionFromBoardTest() {
        assertNull(testBoard.getChampionFromBoard(0, 0));
        testBoard.addChampionToBoard(testBoard.getSet().findChampionTemplate("Corki"), 0, 0);
        assertNull(testBoard.getChampionFromBoard(0, 1));
        assertNull(testBoard.getChampionFromBoard(1, 0));
        assertEquals(testBoard.getChampionFromBoard(0, 0),testBoard.getRoster().get(0));
    }

    @Test
    void addToHistoryTest() {
        assertTrue(testBoard.getWinHistory().isEmpty());
        testBoard.addToHistory(1);
        assertEquals(1, testBoard.getWinHistory().get(0));

    }

    
    

}
