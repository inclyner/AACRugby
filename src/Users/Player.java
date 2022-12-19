package Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private String approveCellPhone(String phoneNumber){
        Pattern special = Pattern.compile("[!@#$%&*()_+=`£@;,//<>§€^ºª|<>?{}«»´\\[\\]~-]");
        Pattern letter = Pattern.compile("[a-zA-z]");

        if (phoneNumber.length() != 9) return "Incomplete Phone Number";
        Matcher hasSpecial = special.matcher(phoneNumber.toString());
        Matcher hasLetters = letter.matcher(phoneNumber.toString());
        if (hasLetters.find() || hasSpecial.find()) return "Invalid Phone Number";

        else return null;
    }

    private String checkEmail(String email){
        Pattern pat = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$");
        if (!pat.matcher(email).matches()) return "Please insert a valid email";
        String terminate="";
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT email from user";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next() && terminate.equals("")) {
                String e = resultSet.getString("email");
                if (email.equals(e)) terminate="Email Already Exists";
            }
            statement.close();
            resultSet.close();
            return terminate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String requestChangePersonalData(String oldInfo, String newInfo, Long nCC) {
        if(checkEmail(newInfo).equals("") || approveCellPhone(newInfo) != null)
            return "Wrong type";
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
        return "Request Sended";
    }
}