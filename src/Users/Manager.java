package Users;

import logic.Game;
import logic.SendEmail;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends CommonFeatures {

    public Manager(String email) throws SQLException {
        super(email);
    }

    public Manager() {}

    private String approveWeight(String weight){
        Pattern special = Pattern.compile("[!@#$%&*()_+=`£@;,//<>§€^ºª|<>?{}«»´\\[\\]~-]");
        Pattern letter = Pattern.compile("[a-zA-z]");

        if (weight.length() > 3 || weight.length()<1) return "Invalid Weight";
        Matcher hasSpecial = special.matcher(weight);
        Matcher hasLetters = letter.matcher(weight);
        if (hasLetters.find() || hasSpecial.find()) return "Invalid Weight";

        else return null;
    }
    private String approveHeight(String height){
        Pattern special = Pattern.compile("[!@#$%&*()_+=`£@;,//<>§€^ºª|<>?{}«»´\\[\\]~-]");
        Pattern letter = Pattern.compile("[a-zA-z]");

        if (height.length() > 3 || height.length()<1) return "Invalid Height";
        Matcher hasSpecial = special.matcher(height);
        Matcher hasLetters = letter.matcher(height);
        if (hasLetters.find() || hasSpecial.find()) return "Invalid Height";
        else return null;
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

    public String insertUser(int type, String nCC, String name, String email, String pass, String sex, String birthDate, String phoneNumber, String aptitude, String height, String weight, String position) throws SQLException, MessagingException {
        Long cartao=null, phone=null;
        Float peso =null, altura =null;

        /*try {
            Connection db = CommonFeatures.getDbConnection();
            if(db==null) return "Daah";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        String terminate ="";
        Pattern special = Pattern.compile("[!@#$%&*()_+=`£@;,//<>§€^ºª|<>?{}«»´\\[\\]~-]");
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");

        Matcher hasSpecial = special.matcher(name);
        Matcher hasDigits = digit.matcher(name);

        //Check name
        name = name.replaceAll("\\s", "");
        if (name.length() < 5) return "Name is too small!";
        else if (name.length() == 0) return "Please Insert a name!";
        else if (name.length() > 20) return "Please Insert a Smaller name!";
        else if (hasSpecial.find() || hasDigits.find())
            return "Please insert a name with just letters";

        //Check password
        pass = pass.replaceAll("\\s", "");
        hasSpecial = special.matcher(pass);
        hasDigits = digit.matcher(pass);
        if (pass.length() == 0) return "Please Insert a Password";
        else if (pass.length() < 5) return "Password is too small!";
        else if (pass.length() > 10) return "Please Insert a Smaller Password";
        else if (!hasSpecial.find() || !hasDigits.find())
            return "Please insert a password with digits and special characters";

        //Check email
        email = email.replaceAll("\\s", "");
        checkEmail(email);
        //Check phoneNumber
        if(approveCellPhone(phoneNumber)!=null) return approveCellPhone(phoneNumber);
        if(!Objects.equals(checkEmail(email), "")) return checkEmail(email);

        phone = Long.parseLong(phoneNumber);
        //Check nCC
        if (nCC.length() != 9) return "Incomplete Citizen Card";
        hasSpecial = special.matcher(nCC);
        Matcher hasLetters = letter.matcher(nCC);
        if (hasLetters.find() || hasSpecial.find()) return "Invalid Citizen Card";

        cartao = Long.parseLong(nCC);
        //Check height
        if (height!=null) {
            if(approveHeight(height)!=null) return approveWeight(height);
            altura = Float.parseFloat(height);
            if(altura > 300.0) return "There's no one that high";
            else if(altura<100.0) return "We don't want anyone that small";
        }

        //Check weight
        if (weight!=null) {
            if(approveWeight(weight)!=null) return approveWeight(weight);
            peso = Float.parseFloat(weight);
            if(peso > 200.0) return "Weight's too high";
            else if(peso<40.0) return "Weight's too low";
        }
        Statement statement = getDbConnection().createStatement();
        //Check Unique Values
        try {
            String query = "SELECT nCC, email from user";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println();
            while (resultSet.next() && terminate.equals("")) {
                Long nCartao = resultSet.getLong("nCC");
                if (nCartao.equals(cartao)) terminate="Citizen Card Number Already Exists";
                String e = resultSet.getString("email");
                if (email.equals(e)) terminate="Email Already Exists";
            }
            resultSet.close();
            if(!terminate.equals("")) return terminate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            String sqlQuery = "INSERT INTO user VALUES ("+cartao+",'"+"false"+"','"+email.toLowerCase()+"','"+name.toLowerCase()+"','"+pass.toLowerCase()+"','"+birthDate+"','"+sex.toLowerCase()+"',"+phone+",'"+aptitude+"','"+position+"',"+peso+","+altura+",'"+type+"')";
            statement.executeUpdate(sqlQuery);
            statement.close();
            closeDb();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SendEmail sendEmail = new SendEmail(email);
        sendEmail.sendEmail(pass);
        return "User is now in the System";
    }


    public String deleteUser(ArrayList<String> listaEmail) throws SQLException {
        String resposta = null;
        int counter =0;
        ArrayList<Integer> ides = new ArrayList<>();
        Statement statement = getDbConnection().createStatement();

        for(String e : listaEmail) {
            String sqlQuery1 = "SELECT email, typeUserId FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery1);
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                int type = resultSet.getInt("typeUserId");
                if (e.equals(email)) {
                    if(type==1){
                        String sqlQuery = "DELETE FROM medicalAppointment WHERE doctorCC=" + getnCC(email);
                        statement.executeUpdate(sqlQuery);
                    }
                    else if (type == 2) {
                        String sqlQuery = "DELETE FROM medicalAppointment WHERE playerCC=" + getnCC(email);
                        statement.executeUpdate(sqlQuery);

                        sqlQuery = "SELECT * FROM practice_player WHERE playerCC=" + getnCC(email);
                        ResultSet resultSetTemp = statement.executeQuery(sqlQuery);
                        while (resultSetTemp.next()) {
                            int idPractice = resultSet.getInt("idPractice");
                            sqlQuery = "SELECT * FROM practice_player WHERE idPractice =" + idPractice+ ";";
                            ResultSet ids = statement.executeQuery(sqlQuery);
                            while(ids.next()){
                                counter++;
                            }
                            if(counter==1){
                                sqlQuery = "DELETE FROM practice_player WHERE idPractice=" + idPractice+ ";";
                                statement.executeUpdate(sqlQuery);
                                sqlQuery = "DELETE FROM practice WHERE id=" + idPractice+ ";";
                                statement.executeUpdate(sqlQuery);
                            }
                        }
                        for(Game games: getGames()){
                            if(games.getPlayers() != null){
                                if(games.getPlayers().contains(getnCC(email)) ){
                                    if(games.getPlayers().size()<=15){
                                        sqlQuery = "DELETE from game_player WHERE idGame = " + games.getId();
                                        statement.executeUpdate(sqlQuery);
                                    }
                                }
                            }

                        }
                    }
                    else if(type==3){
                        String query = "SELECT coachCC FROM game WHERE coachCC=" + getnCC(email);
                        ResultSet resultSet1 = statement.executeQuery(query);
                        if(resultSet1.next()) return "Can't delete Coach";

                        query = "SELECT id FROM practice WHERE coachCC IN (" + getnCC(email)+");";
                        ResultSet resultSet2 = statement.executeQuery(query);
                        resultSet2.getMetaData();
                        while (resultSet2.next()){
                            int id = resultSet2.getInt("id");
                            System.out.println("RESULTSET:"+resultSet2.getRow());
                            System.out.println("ID"+id);
                            ides.add(id);
                        }
                        for(Integer i: ides) {
                            String sqlQuery = "DELETE FROM practice_player WHERE idPractice=" + i;
                            statement.executeUpdate(sqlQuery);
                        }
                        /*while (resultSet2.next()){
                            System.out.println("RESULTSET:"+resultSet2.getRow());
                            int id = resultSet2.getInt("id");
                            System.out.println(id);
                            String sqlQuery = "DELETE FROM practice_player WHERE idPractice=" + id;
                            statement.executeUpdate(sqlQuery);
                        }*/


                        String sqlQuery = "DELETE FROM practice WHERE coachCC=" + getnCC(email);
                        statement.executeUpdate(sqlQuery);

                    }
                    String sqlQuery="DELETE FROM user WHERE email = '" + email+"'";
                    statement.executeUpdate(sqlQuery);
                }

            }
            statement.close();

        }
        closeDb();
        return "Users Deleted";
    }

    public String approveChangeRequest(Long id, boolean bool) {
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM changeRequest WHERE playerCC=" + id;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            String oldInfo = resultSet.getString("oldInfo");
            String newInfo = resultSet.getString("newInfo");
            if (bool) {
                if (approveCellPhone(newInfo)==null) {
                    sqlQuery="UPDATE user SET phoneNumber = '" + newInfo + "' WHERE nCC=" + id ;
                } else if (Objects.equals(checkEmail(newInfo), "")) {
                    sqlQuery = "UPDATE user SET email = '" + newInfo + "' WHERE nCC=" + id ;
                }else return "Invalid Values";
                statement.executeUpdate(sqlQuery);
            }
            sqlQuery = "DELETE FROM changeRequest WHERE playerCC=" + id + " AND newInfo='" + newInfo + "'";
            statement.executeUpdate(sqlQuery);
            closeDb();
            return "Option Validated";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}