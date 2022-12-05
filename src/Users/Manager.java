package Users;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends CommonFeatures {

    public Manager(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(nCC, name, email, sex, birthDate, phoneNumber);
    }

    private String insertUser(int type, Long nCC, String name, String email, String pass, String sex, String birthDate, Long phoneNumber) {
        //Player
        Pattern special = Pattern.compile("[!@#$%&*()_+=`£@;,//<>§€^ºª|<>?{}«»´\\[\\]~-]");
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern pat = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$");

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
        if (pass.length() < 5) return "Password is too small!";
        else if (pass.length() == 0) return "Please Insert a Password";
        else if (pass.length() > 10) return "Please Insert a Smaller Password";
        else if (!hasSpecial.find() || !hasDigits.find())
            return "Please insert a password with digits and special characters";

        //Check email
        email = email.replaceAll("\\s", "");
        if(!pat.matcher(email).matches()) return "Please insert a valid email";






        try {
            Statement statement = getDbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users");
            while (resultSet.next()) {
                Long nCartao = resultSet.getLong("nCC");
                if (nCartao.equals(nCC)) return "Número de Cartão de Cidadão já existe";

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Doctor
        String sqlQuery = "INSERT INTO user VALUES ('" + nCC + "','" + 0 + "','" + email + "','" + name + "','" + pass + "','" + birthDate + "','" + sex + "','" + phoneNumber + 1 + "')";
    }


    private void deleteUser(ArrayList<Long> listaCc) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        AtomicReference<String> sqlQuery = new AtomicReference<>("SELECT nCC FROM user");
        ResultSet resultSet = statement.executeQuery(sqlQuery.get());

        listaCc.forEach((n) -> {
            while (resultSet.next()) {
                long cc = resultSet.getLong("nCC");
                int type = resultSet.getInt("type");


                if (n == cc) {
                    if (type == 2) {
                        sqlQuery.set("DELETE FROM medicalAppointment WHERE playerCC=" + cc);
                        statement.executeQuery(sqlQuery.get());

                        sqlQuery.set("SELECT * FROM practice_player WHERE playerCC=" + cc);
                        ResultSet resultSetTemp = statement.executeQuery(sqlQuery.get());
                        // remover treinos em que o player esta convocado sozinho
                        while (resultSetTemp.next()) {
                            int idPractice = resultSet.getInt("idPractice");
                            sqlQuery.set("SELECT * FROM practice_player");
                            ResultSet ids = statement.executeQuery(sqlQuery.get());
                            if (ids.getRow() <= 1) {
                                sqlQuery.set("DELETE FROM practice_player WHERE idPractice=" + idPractice);
                                statement.executeQuery(sqlQuery.get());
                            }
                        }
                    }

                    sqlQuery.set("DELETE FROM user WHERE nCC=" + cc);
                    statement.executeQuery(sqlQuery.get());


                }

            }

        });

    }

    private void approveChangeRequest(int id, boolean bool) {
       Statement statement = getDbConnection().createStatement();
        AtomicReference<String> sqlQuery = new AtomicReference<>("SELECT * FROM changeRequest WHERE id=" + id);
       ResultSet resultSet = statement.executeQuery(sqlQuery.get());
        String oldInfo = resultSet.getString("oldInfo");
        String newInfo = resultSet.getString("newInfo");
        long cc = resultSet.getLong("nCC");
        Pattern pat = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$");

        if (bool) {
            if (oldInfo.length() == 9) {
                sqlQuery.set("UPDATE user SET phoneNumber = '" + newInfo + "' WHERE nCC=" + cc);
            } else if (pat.matcher(oldInfo).matches()) {
                sqlQuery.set("UPDATE user SET email = '" + newInfo + "' WHERE nCC=" + cc);
            }
            statement.executeQuery(sqlQuery.get());
        }
        sqlQuery.set("DELETE FROM changeRequest WHERE id=" + id);
        statement.executeQuery(sqlQuery.get());
    }

}
*/