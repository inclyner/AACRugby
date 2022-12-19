package logic.tests;

import Users.Player;
import logic.ModelManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
class ModelManagerTest {

        @Test
        void loginWorks () throws SQLException {
            ModelManager modelManager = new ModelManager();
            assertTrue(modelManager.login("camilotavares@acc.com", "n+#d6d"));
        }
        @Test
        void loginWrongPass () throws SQLException {
            ModelManager modelManager = new ModelManager();
            assertFalse(modelManager.login("camilotavares@acc.com", "adbg54"));
        }
        @Test
        void loginWrongUser () throws SQLException {
            ModelManager modelManager = new ModelManager();
            assertFalse(modelManager.login("camiloMaria@aac.pt", "n+#d6d"));
        }

    }