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
    private Connection dbConn;
    public CommonFeatures(){

    };//efeitos de teste

    public CommonFeatures(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException
    {
        File f = new File("AACRugby.db");
        this.DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        dbConn = DriverManager.getConnection(DATABASE_URL);
        this.nCC = nCC;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.dbConn = createDb();
    }
    
    public Connection createDb() throws SQLException {
        File f = new File("AACRugby\\bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        return dbConn;
    }

    public Connection getDbConnection() {
        return dbConn;
    }


    public String getDATABASE_URL() {
        return DATABASE_URL;
    }


    //String sqlQuery = "SELECT nCC, logged, email, name, password, birthDate, sex, phoneNumber, aptitude, position, weight, height, typeUser FROM users";

}
