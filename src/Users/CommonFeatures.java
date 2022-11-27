package Users;

import java.sql.*;

public abstract class CommonFeatures {
    private final String DATABASE_URL = "jdbc:sqlite:bd/AACRugby.db";
    private Connection dbConn;

    public CommonFeatures() throws SQLException
    {
        dbConn = DriverManager.getConnection(DATABASE_URL);
    }
    //String sqlQuery = "SELECT nCC, logged, email, name, password, birthDate, sex, phoneNumber, aptitude, position, weight, height, typeUser FROM users";
    public boolean login(String email, String password) throws SQLException {
        Statement statement = dbConn.createStatement();

        String sqlQuery = "SELECT email, password from users";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String e = resultSet.getString("username");
            String p = resultSet.getString("password");
            if(email.equals(e) && password.equals(p)) {
                resultSet.close();
                statement.close();
                return true;
            }
        }
        resultSet.close();
        statement.close();
        return false;
    }
}
