package Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Player extends CommonFeatures{

    private double weight;
    private int height;
    private Boolean aptitude;
    private String position;

    public Player(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(email);
    }

    public Player() {

    }

    public void setWeight(double weight){
        this.weight = weight;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setAptitude(Boolean aptitude){
        this.aptitude = aptitude;
    }
    public void setPosition(String position){
        this.position = position;
    }


    public String requestChangePersonalData(String oldInfo, String newInfo) {
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT email, phoneNumber FROM user WHERE type=2";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                oldInfo = resultSet.getString("oldInfo");
                long nCC = resultSet.getLong("nCC");
                newInfo = resultSet.getString("newInfo");
                String sqlQuery2 = "UPDATE changeRequest SET id='" + id + "', " + "newInfo=" + newInfo + "', " +"oldInfo" + oldInfo + "', " + "playerCC='" + nCC;
                statement.executeUpdate(sqlQuery2);
            }
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return "Request change data sent";
    }

}
