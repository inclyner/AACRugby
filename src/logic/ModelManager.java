package logic;

//import Users.CommonFeatures;
import Users.*;

import javax.mail.MessagingException;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class ModelManager {
    Manager manager;
    Coach coach;
    Doctor doctor;
    Player player;

    public static String emailLogged;
    private static String nameLogged;

    public String getEmailLogged() {
        return emailLogged;
    }

    public String getNameLogged() {
        return nameLogged;
    }
    public ModelManager() throws SQLException {}
    public boolean login(String email, String password) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, password, logged from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String e = resultSet.getString("email");
            String p = resultSet.getString("password");
            boolean logged = resultSet.getBoolean("logged");
            if(email.equals(e) && password.equals(p)) {
                sqlQuery="UPDATE user SET logged="+true+"";
                statement.executeUpdate(sqlQuery);
                resultSet.close();
                statement.close();
                emailLogged = email;
                nameLogged = getNameUser(email);
                return true;
            }
        }
        resultSet.close();
        statement.close();
        return false;
    }

    public String insertUser(int type, String nCC, String name, String email, String pass, String sex, String birthDate, String phoneNumber, String aptitude, String height, String weight, String position) throws SQLException, MessagingException {
        //if(checksTypeUser(emailLogged) == 4){
        Manager manager1 = new Manager();
        return manager1.insertUser(type, nCC, name, email, pass, sex,birthDate, phoneNumber,  aptitude, height, weight,position);
        //}
        //return "Unable to add User";
    }

    public ArrayList<Player> getAllPlayer(){
        Manager manager1 = new Manager();
        for(Player p: manager1.getPlayers())
            System.out.println(p);
        return manager1.getPlayers();
    }

    public ArrayList<Coach> getAllCoach(){
        Manager manager1 = new Manager();
        return  manager1.getCoaches();
    }

    public ArrayList<Manager> getAllManager(){
        Manager manager1 = new Manager();
        return  manager1.getManagers();
    }

    public ArrayList<Doctor> getAllDoctor(){
        Manager manager1 = new Manager();
        return  manager1.getDoctors();
    }
    public ArrayList<ChangeRequest> getAllRequests() throws SQLException {
        Manager manager1 = new Manager();
        return  manager1.getChangeRequests();
    }
    public String deleteUsers(ArrayList<String> emails) throws SQLException {
        Manager manager1 = new Manager(emailLogged);
        return manager1.deleteUser(emails);
    }



    public ArrayList<Game> getAllGames(){
        Manager manager1 = new Manager();
        return  manager1.getGames();
    }


    public ArrayList<Practise> getAllPractise(){
        Manager manager1 = new Manager();
        return  manager1.getPractise();
    }

    public ArrayList<MedicalAppointment> getAllAppointments(){
        Manager manager1 = new Manager();
        return manager1.getAppointments();
    }

    public String getNameUser(String email) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, name from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String e = resultSet.getString("email");
            if(email.equals(e)) {
                String name = resultSet.getString("name");
                resultSet.close();
                statement.close();
                return name;
            }
        }
        resultSet.close();
        statement.close();
        return null;
    }
    public String getNameUserNcc(String nCC) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT nCC, name from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String name = resultSet.getString("name");
                resultSet.close();
                statement.close();
                return name;
            }
        }
        resultSet.close();
        statement.close();
        return null;
    }

    public String getPhoneNumberUserNcc(String nCC) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT phoneNumber from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String name = resultSet.getString("phoneNumber");
                resultSet.close();
                statement.close();
                return name;
            }

        }
        resultSet.close();
        statement.close();
        return null;
    }



    public int checksTypeUser(String email) throws SQLException{
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, typeUserId from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String e = resultSet.getString("email");
            if(email.equals(e)) {
                int type = resultSet.getInt("typeUserId");
                resultSet.close();
                statement.close();
                return type;
            }
        }
        resultSet.close();
        statement.close();
        return 0;
    }

    public String getEmailUserNcc(String nCC) {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection(DATABASE_URL);
            Statement statement = dbConn.createStatement();
            String sqlQuery = "SELECT email from user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String n = resultSet.getString("nCC");
                if(n.equals(nCC)) {
                    String name = resultSet.getString("email");
                    resultSet.close();
                    statement.close();
                    return name;
                }

            }
            resultSet.close();
            statement.close();
            return null;
        } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public void requestChange(String oldInfo,String newInfo) throws SQLException {
        Player p = new Player();
        p.requestChangePersonalData(oldInfo, newInfo);

    }
}

