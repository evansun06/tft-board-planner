package persistance;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Set;

public class JsonWriterTest {
    
    @Test
    void constructorTest() {
        JsonWriter writer = new JsonWriter("address");
        assertEquals("address", writer.getAddress());
    }

    
}
