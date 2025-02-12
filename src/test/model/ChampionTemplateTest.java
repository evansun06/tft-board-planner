package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChampionTemplateTest {
    ChampionTemplate ahri;

    @BeforeEach
    void setUp() {
        ahri = new ChampionTemplate("Ahri", 100, 50, 50, 25, 20, 0,
                                          100, 10, 3, 2);                                       
    }

    @Test 
    void constructorTest() {
        assertEquals(100, ahri.getHealth());
        assertEquals("Ahri", ahri.getName());
        assertTrue(ahri.readilyPlaceable);
        assertEquals(-1, ahri.locx);
    }

    


    

}
