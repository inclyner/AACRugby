package logic.tests;

import Users.Player;
import logic.ModelManager;
import org.testng.annotations.Test;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
class ModelManagerTest {

        @Test
        void loginWorks () throws SQLException {
            ModelManager modelManager = new ModelManager();
            assertTrue(modelManager.login("camiloTavares@aac.pt", "n+#d6D"));
        }
        @Test
        void loginWrongPass () throws SQLException {
            ModelManager modelManager = new ModelManager();
            assertFalse(modelManager.login("camiloTavares@aac.pt", "adbg54"));
        }
        @Test
        void loginWrongUser () throws SQLException {
            ModelManager modelManager = new ModelManager();
            assertFalse(modelManager.login("camiloMaria@aac.pt", "n+#d6D"));
        }

    }