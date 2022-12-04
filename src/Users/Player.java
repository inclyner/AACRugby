package Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Player extends CommonFeatures{

    private double weight;
    private int height;
    private Boolean aptitude;
    private String position;

    public Player(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(nCC, name, email, sex, birthDate, phoneNumber);
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


    public void requestChangePersonalData(String oldInfo, String newInfo) {
        try {
            Statement statement = getDbConnection().createStatement();
            oldInfo = "SELECT email, phoneNumber FROM user WHERE type=2";
            ResultSet resultSet = statement.executeQuery(oldInfo);
            while(resultSet.next()) {
                newInfo = "UPDATE requestChange SET email='" + email + "', " +
                        "phoneNumber='" + phoneNumber + "' WHERE playerCC=" + nCC;;
                statement.executeUpdate(newInfo);
                statement.close();
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

}
