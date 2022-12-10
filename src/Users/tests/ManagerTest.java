package Users.tests;

import Users.Manager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

class ManagerTest {

/*
    // delete users tests
    @Test
    void testDeleteUser_userExists() throws Exception {
        // Arrange
        ArrayList<Long> listaCc = new ArrayList<Long>();
        listaCc.add(423455849L);
        Manager manager = new Manager();
        manager.insertUser(2, 423455849L, "Joao Silva", "rhoikj467@tmail3.com", "pswor123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing");
        // Act
        boolean result = manager.deleteUser(listaCc);

        // Assert
        assertTrue(result);
    }

    @Test
    void testDeleteUser_userDoesNotExist() throws Exception {
        // Arrange
        ArrayList<Long> listaCc = new ArrayList<Long>();
        listaCc.add(111111111L);
        Manager manager = new Manager();

        // Act
        boolean result = manager.deleteUser(listaCc);

        // Assert
        assertFalse(result);
    }

    //insert user tests
    @Test
    public void insertCorrectUserPlayerAllFields() {

        Manager manager = new Manager();
        ArrayList<Long> lista= new ArrayList<Long>();
        lista.add(423455849L);
        remove_user423455849();
        assertEquals( "User is now in the System",manager.insertUser(2, 423455849L, "Joao Silva", "rhoikj467@tmail3.com", "pswor123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
        remove_user423455849();
    }
    @Test
    public void insertRepeatEmailUser() {
        Manager manager = new Manager();
        assertEquals("Email Already Exists",manager.insertUser(2, 423455849L, "Joao Silva", "camiloTavares@aac.pt", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertRepeatCCUser() {
        Manager manager = new Manager();
        assertEquals("Citizen Card Number Already Exists",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertCorrectUserDoctorAllFields() {
        Manager manager = new Manager();
        remove_user423455849();
        assertEquals("User is now in the System",manager.insertUser(1, 423412849L, "Joao Baiao", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L,null,Float.NaN,Float.NaN,""));
        remove_user423455849();
    }
    @Test
    public void insertCorrectUserCoachAllFields() {
        Manager manager = new Manager();
        remove_user423455849();
        assertEquals("User is now in the System",manager.insertUser(3, 423643849L, "Joao Costa", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, null,Float.NaN,Float.NaN,""));

    }
    @Test
    public void insertCorrectUserOnlyRequired() {
        Manager manager = new Manager();
        remove_user423455849();
        assertEquals("User is now in the System",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, Float.NaN, Float.NaN, "Wing"));
        remove_user423455849();
    }
    @Test
    public void insertUserInvalidCCTooMany() {
        Manager manager = new Manager();
        assertEquals("Incomplete Citizen Card",manager.insertUser(2, 42345512349L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertUserInvalidCCTooFew() {
        Manager manager = new Manager();
        assertEquals("Incomplete Citizen Card",manager.insertUser(2, 423449L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void noUserName() {
        Manager manager = new Manager();
        assertEquals("please Insert a name!",manager.insertUser(2, 423455849L, "", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void smallUserName() {
        Manager manager = new Manager();
        assertEquals("Name is too small!",manager.insertUser(2, 423455849L, "joao", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void bigUserName() {
        Manager manager = new Manager();
        assertEquals("Please Insert a Smaller name!",manager.insertUser(2, 423455849L, "alexandrino carlos monteiro ", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void invalidUserName() {
        Manager manager = new Manager();
        assertEquals("Please insert a name with just letters",manager.insertUser(2, 423455849L, "alex4ndr3", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertWrongEmailFormat() {
        Manager manager = new Manager();
        assertEquals("Please insert a valid email",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyik@j467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertNoEmailFormat() {
        Manager manager = new Manager();
        assertEquals("Please insert a valid email",manager.insertUser(2, 423455849L, "Joao Silva", "", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertNoPassword() {
        Manager manager = new Manager();
        assertEquals("Please Insert a Password",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertSmallPassword() {
        Manager manager = new Manager();
        assertEquals("Password is too small!",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "12345", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertBigPassword() {
        Manager manager = new Manager();
        assertEquals("Please Insert a Smaller Password",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "12345678910", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertNoSpecialPassword() {
        Manager manager = new Manager();
        assertEquals("Please insert a password with digits and special character",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "145678910", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertSmallPhoneNumber() {
        Manager manager = new Manager();
        assertEquals("Incomplete Phone Number",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 156789L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertBigPhoneNumber() {
        Manager manager = new Manager();
        assertEquals("Incomplete Phone Number",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 12345678910L, true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertShortHeight() {
        Manager manager = new Manager();
        assertEquals("We don't want anyone that small",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 90F, 190F, "Wing"));
    }
    @Test
    public void insertBigHeight() {
        Manager manager = new Manager();
        assertEquals("There's no one that high",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 380F, 190F, "Wing"));
    }
    @Test
    public void insertUnderWeight() {
        Manager manager = new Manager();
        assertEquals("Weight's too high",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 340F, "Wing"));
    }
    @Test
    public void insertOverWeight() {
            Manager manager = new Manager();
            assertEquals("Weight's too low",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 25F, "Wing"));
        }
    @Test
    public void OverWeight() {
        Manager manager = new Manager();
        assertEquals("Weight's too low",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", 123456789L, true, 180F, 25F, "Wing"));
    }

    @Test
    public void approveChangeRequestRight() {
        Manager manager = new Manager();
        assertEquals("Option Validated",manager.approveChangeRequest(2, true));
    }

    @Test
    public void approveChangeRequestWrongEmail() {
        Manager manager = new Manager();
        assertEquals("Invalid Values",manager.approveChangeRequest(2, true));
    }
    @Test
    public void approveChangeRequestWrongCellPhone() {
        Manager manager = new Manager();
        assertEquals("Invalid Values",manager.approveChangeRequest(2, true));
    }






// change request tests
    @Test
    void testApproveChangeRequest_playerNotFound() {
        // Arrange
        int id = 1;
        boolean bool = true;
        Manager manager = new Manager();

        // Act
        String result = manager.approveChangeRequest(id, bool);

        // Assert
        assertEquals("Player not found", result);
    }

    @Test
    void testApproveChangeRequest_approveCellPhone() {
        // Arrange
        int id = 1;
        boolean bool = true;
        Manager manager = new Manager();

        // Act
        String result = manager.approveChangeRequest(id, bool);

        // Assert
        assertEquals("Option Validated", result);
    }

    @Test
    void testApproveChangeRequest_checkEmail() {
        // Arrange
        int id = 1;
        boolean bool = true;
        Manager manager = new Manager();

        // Act
        String result = manager.approveChangeRequest(id, bool);

        // Assert
        assertEquals("Option Validated", result);
    }

    @Test
    void testApproveChangeRequest_invalidValues() {
        // Arrange
        int id = 1;
        boolean bool = true;
        Manager manager = new Manager();

        // Act
        String result = manager.approveChangeRequest(id, bool);

        // Assert
        assertEquals("Invalid Values", result);
    }

    void remove_user423455849() {
        Manager manager = new Manager();
        ArrayList<Long> lista= new ArrayList<Long>();
        lista.add(423643849L);
        try {
            manager.deleteUser(lista);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}


