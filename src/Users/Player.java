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

    public Boolean getAptitude() {
        return aptitude;
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


    public void requestChangePersonalData(String oldInfo, String newInfo, Long nCC) {
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery2 = "INSERT INTO changeRequest VALUES (NULL,'" + newInfo + "','" + oldInfo + "','" + nCC +"')";
            statement.executeUpdate(sqlQuery2);
            statement.close();
            closeDb();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}
