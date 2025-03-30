package model;

import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e1;
    private Event e2;
    private Date date;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e1 = new Event("Moved Ahri To (2,3)");   // (1)
        e2 = new Event("Moved Ahri To (2,3)");
        date = e1.getDate();  // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Moved Ahri To (2,3)", e1.getDescription());
        assertEquals(date, e1.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Moved Ahri To (2,3)", e1.toString());
    }

    @Test
    public void testEquals() {
        assertTrue(e1.equals(e2));
    }

}
