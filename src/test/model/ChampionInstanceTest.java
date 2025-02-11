package model;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChampionInstanceTest {
    ChampionTemplate zeriTemplate;
    ChampionInstance zeri1;

    @BeforeEach
    void setUp() {
        zeriTemplate = new ChampionTemplate("Zeri", 100, 10, 5, 50, 10,10, 10, 6, 2);
        zeri1 = new ChampionInstance(zeriTemplate, 1, 3);
    }

}
