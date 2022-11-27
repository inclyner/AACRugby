package logic;

import Users.Player;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest {

    @Test
    void login() throws SQLException {
       ModelManager modelManager = new ModelManager();
       assertTrue(modelManager.login("camiloTavares@aac.pt", "n+#d6D"));
    }
}