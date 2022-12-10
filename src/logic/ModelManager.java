package logic;

//import Users.CommonFeatures;
import Users.*;

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
            if(email.equals(e) && password.equals(p) && !logged) {
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

    public String insertUser(int type, String nCC, String name, String email, String pass, String sex, String birthDate, String phoneNumber, String aptitude, String height, String weight, String position) throws SQLException{
        if(checksTypeUser(emailLogged) == 4){
            Manager manager1 = new Manager();
            return manager1.insertUser(type, nCC, name, email, pass, sex,birthDate, phoneNumber,  aptitude, height, weight,position);
        }
        return "Unable to add User";
    }

    public ArrayList<Player> getAllPlayer(){
        Manager manager1 = new Manager();
        return  manager1.getPlayers();
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

}

