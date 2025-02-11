package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChampionInstanceTest {
    ChampionTemplate zeriTemplate;
    ChampionInstance zeri1;
    ChampionInstance zeri2;

    @BeforeEach
    void setUp() {
        zeriTemplate = new ChampionTemplate("Zeri", 100, 10, 5, 50, 10,10, 10, 10, 6, 2);
        zeri1 = new ChampionInstance(zeriTemplate, 1, 3);
        zeri2 = new ChampionInstance(zeriTemplate, 3, 3);
    }

    @Test
    void constructorTest() {
        //Test superclass constructor
        assertEquals("Zeri", zeri1.getName());
        assertEquals("Zeri", zeri2.getName());
        assertTrue(zeri1.readilyPlaceable);

        //Test unique instance
        assertFalse(zeri1.getInstanceId() == zeri2.getInstanceId());
        assertEquals(1, zeri1.getInstanceId());
        assertEquals(2, zeri2.getInstanceId());
        //Test locations set
        assertEquals(3, zeri2.getX());
        assertEquals(3, zeri2.getY());

        //Test static nextId
        assertTrue(zeri1.getNextId() == zeri2.getNextId());
        assertEquals(3, zeri2.getNextId());

        //Test third unique instance
        ChampionInstance zeri3 = new ChampionInstance(zeriTemplate, 4, 4);
        assertEquals(3, zeri3.getInstanceId());

    }

}
