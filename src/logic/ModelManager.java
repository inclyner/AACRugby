package logic;

//import Users.CommonFeatures;
import Users.*;

import java.io.File;
import java.sql.*;

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
        String sqlQuery = "SELECT email, password from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String e = resultSet.getString("email");
            String p = resultSet.getString("password");
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


    public int checksTypeUser(String email, String password) throws SQLException{
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, password, typeUserId from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String e = resultSet.getString("email");
            String p = resultSet.getString("password");
            if(email.equals(e) && password.equals(p)) {
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
    /*public String insertUser(int type, Long nCC, String name, String email, String pass, String sex, String birthDate, Long phoneNumber, Boolean aptitude, Float height, Float weight, String position){

    }*/
}

