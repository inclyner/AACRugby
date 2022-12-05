package Users.tests;

import Users.Coach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {
    @Test
    public void insertNewNotes() {
        Coach coach = null;
        try {
            coach = new Coach("fvgrgfg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals("Insert notes",coach.InsertPlayersPunishement("Camilo Tavares", "sadd sad as", 2));
    }
}