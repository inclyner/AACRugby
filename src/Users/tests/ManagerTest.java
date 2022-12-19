package Users.tests;

import Users.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Users.CommonFeatures.getDbConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {


    // delete users tests
   /* @Test
    void testDeleteUser_userExists() throws Exception {
        // Arrange
        ArrayList<Long> listaCc = new ArrayList<Long>();
        listaCc.add(423455849L);
        Manager manager = new Manager();
        manager.insertUser(2, 423455849L, "Joao Silva", "rhoikj467@tmail3.com", "pswor123!", "male", "12-10-99", 123456789L, true, 180F, 190F, "Wing");
        // Act
        manager.deleteUser(listaCc);

        boolean result=true;
        Statement statement = getDbConnection().createStatement();
        String query = "SELECT nCC, email from user";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Long nCartao = resultSet.getLong("nCC");
            if (nCartao.equals(423455849L))
                result=false;
        }
        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteUser_userDoesNotExist() throws Exception {
        // Arrange
        ArrayList<Long> listaCc = new ArrayList<Long>();
        listaCc.add(111111111L);
        Manager manager = new Manager();

        // Act
        //boolean result = manager.deleteUser(listaCc);

        // Assert
       //Assertions.assertFalse(result);
    }

    //insert user tests
    @Test
    public void insertCorrectUserPlayerAllFields() throws SQLException, MessagingException {

        Manager manager = new Manager();
        ArrayList<Long> lista= new ArrayList<Long>();
        lista.add(423455849L);
        remove_user423455849();
        assertEquals( "User is now in the System",manager.insertUser(2, "423455890", "Joao Silva", "rhomaha467@tmail3.com", "pswor123!", "male", "12-10-99", "123456789", "true", "180", "190", "Wing"));
        remove_user423455849();
    }*/
    @Test
    public void insertRepeatEmailUser() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Email Already Exists",manager.insertUser(2, "423455891", "Joao Silva", "rrtr467@tmail3.com", "pswor123!", "male", "12-10-99", "123456789", true, "180", "190", "Wing"));
    }
    @Test
    public void insertRepeatCCUser() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Citizen Card Number Already Exists",manager.insertUser(2, "423455890", "Joao Silva", "rrtr467@tmail3.com", "pswor123!", "male", "12-10-99", "123456789", true, "180", "190", "Wing"));
    }
    @Test
    public void insertCorrectUserDoctorAllFields() throws SQLException, MessagingException {
        Manager manager = new Manager();
        //remove_user423455849();
        assertEquals("User is now in the System",manager.insertUser(1, "423412849", "Joao Baiao", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", "123456789",null,null,null,""));
        //remove_user423455849();
    }
    @Test
    public void insertCorrectUserCoachAllFields() throws SQLException, MessagingException {
        Manager manager = new Manager();
        //remove_user423455849();
        assertEquals("User is now in the System",manager.insertUser(3, "423412349", "Joao Baiao", "rh24ttj467@tmail3.com", "psword123!", "male", "12-10-99", "123456789",null,null,null,""));

    }
    /*@Test
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
        assertEquals("please Insert a name!",manager.insertUser(2, 423455849L, "", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }
    @Test
    public void smallUserName() {
        Manager manager = new Manager();
        assertEquals("Name is too small!",manager.insertUser(2, 423455849L, "joao", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }
    @Test
    public void bigUserName() {
        Manager manager = new Manager();
        assertEquals("Please Insert a Smaller name!",manager.insertUser(2, 423455849L, "alexandrino carlos monteiro ", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }
    @Test
    public void invalidUserName() {
        Manager manager = new Manager();
        assertEquals("Please insert a name with just letters",manager.insertUser(2, 423455849L, "alex4ndr3", "rhoyikj467@tmail3.com", "psword123!", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertWrongEmailFormat() {
        Manager manager = new Manager();
        assertEquals("Please insert a valid email",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyik@j467@tmail3.com", "psword123!", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertNoEmailFormat() {
        Manager manager = new Manager();
        assertEquals("Please insert a valid email",manager.insertUser(2, 423455849L, "Joao Silva", "", "psword123!", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }
    @Test
    public void insertNoPassword() {
        Manager manager = new Manager();
        assertEquals("Please Insert a Password",manager.insertUser(2, 423455849L, "Joao Silva", "rhoyikj467@tmail3.com", "", "male", "12-10-99", "123456789", true, 180F, 190F, "Wing"));
    }*/
    @Test
    public void insertSmallPassword() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Password is too small!",manager.insertUser(2, "423446849", "Joao Silva", "rhoyj467@tmail3.com", "w", "male", "12-10-99", "123456789", Boolean.valueOf("true"), "180", "190", "Wing"));
    }
    @Test
    public void insertBigPassword() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Please Insert a Smaller Password",manager.insertUser(2, String.valueOf(1114558411L), "Joao Silva", "rhoyikj@hotmail3.com", "psword123!!!", "male", "12-10-99", String.valueOf(123456789L), true, String.valueOf(380F), String.valueOf(190F), "Wing"));
    }
    @Test
    public void insertNoSpecialPassword() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Please insert a password with digits and special characters",manager.insertUser(2, String.valueOf(4214558411L), "Joao Silva", "rhoyikj@hotmail3.com", "err123", "male", "12-10-99", String.valueOf(123456789L), true, String.valueOf(380F), String.valueOf(190F), "Wing"));
    }
    @Test
    public void insertSmallPhoneNumber() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Incomplete Phone Number",manager.insertUser(2, String.valueOf(421422841L), "Joao Silva", "ryikj46@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123489L),true, String.valueOf(380F), String.valueOf(190F), "Wing"));
    }
    @Test
    public void insertBigPhoneNumber() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Incomplete Phone Number",manager.insertUser(2, String.valueOf(421455841L), "Joao Silva", "rhoyikj46@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123456784664569L), true, String.valueOf(160), String.valueOf(190), "Wing"));
    }
    @Test
    public void insertShortHeight() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("We don't want anyone that small",manager.insertUser(2, String.valueOf(121455840), "Joao Silva", "rhoaj46@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123456789), true, String.valueOf(50), String.valueOf(100), "Wing"));
    }
    @Test
    public void insertBigHeight() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("There's no one that high",manager.insertUser(2, String.valueOf(421455841L), "Joao Silva", "rhoyikj46@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123456789L), true, String.valueOf(350), String.valueOf(40), "Wing"));
    }
    @Test
    public void insertUnderWeight() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Invalid Weight",manager.insertUser(2, String.valueOf(423455249L), "Joao Silva", "rhoyikj67@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123456789L), true, String.valueOf(180F), String.valueOf(340F), "Wing"));
    }
    @Test
    public void insertOverWeight() throws SQLException, MessagingException {
            Manager manager = new Manager();
            assertEquals("Invalid Weight",manager.insertUser(2, String.valueOf(423455049L), "Joao Silva", "rhyikj467@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123456789L),true, String.valueOf(180F), String.valueOf(25F), "Wing"));
        }
    @Test
    public void OverWeight() throws SQLException, MessagingException {
        Manager manager = new Manager();
        assertEquals("Invalid Weight",manager.insertUser(2, String.valueOf(423454849L), "Joao Silva", "rhoyik467@tmail3.com", "psword123!", "male", "12-10-99", String.valueOf(123456789L), true, String.valueOf(180F), String.valueOf(25F), "Wing"));
    }

    @Test
    public void approveChangeRequestRight() {
        Manager manager = new Manager();
        assertEquals("Option Validated",manager.approveChangeRequest(837587143L, true));
    }

    @Test
    public void approveChangeRequestWrongEmail() {
        Manager manager = new Manager();
        assertEquals("Invalid Values",manager.approveChangeRequest(585005353L, true));
    }
    @Test
    public void approveChangeRequestWrongCellPhone() {
        Manager manager = new Manager();
        assertEquals("Invalid Values",manager.approveChangeRequest(451707431L, true));
    }






// change request tests
   /* @Test
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
        ArrayList<String> lista= new ArrayList<>();
        lista.add(String.valueOf(423643849L));
        try {
            manager.deleteUser(lista);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}


