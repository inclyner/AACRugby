package Users;

import java.io.File;
import java.sql.*;

public abstract class CommonFeatures {

    private Long nCC;
    private String name;
    private String email;
    private String sex;
    private String birthDate;
    private Long phoneNumber;
    private String DATABASE_URL;
    private static Connection dbConn;
    public CommonFeatures(){

    };//efeitos de teste

    public CommonFeatures(String email) throws SQLException {this.email = email;}

    
    public static Connection createDb() throws SQLException {
        //File f = new File("AACRugby.db");
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        return dbConn;
    }

    public static Connection getDbConnection() throws SQLException {
       return dbConn = createDb();
    }


    public String getDATABASE_URL() {
        return DATABASE_URL;
    }


    //String sqlQuery = "SELECT nCC, logged, email, name, password, birthDate, sex, phoneNumber, aptitude, position, weight, height, typeUser FROM users";

}
