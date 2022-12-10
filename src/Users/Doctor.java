package Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Doctor extends CommonFeatures{
    public Doctor(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(email);
    }

    public String InsertDiet(Long cc, String notes) {
        try{
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM dietNotes";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                long playercc = resultSet.getInt("playerCC");
                if (Objects.equals(playercc, cc)){
                    String aux = resultSet.getString("notes");
                    sqlQuery = "UPDATE dietNotes SET notes='" + notes.concat(aux) + "', doctorCC ='" + getnCC() + "'WHERE nCC ='" + cc + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    statement.close();
                    return "Update diet notes";
                }
            }
            //create diet notes
            sqlQuery = "INSERT INTO dietNotes (playerCC, doctorCC, notes) VALUES ('" + cc + "','" + getnCC() + "','" + notes + "')";
            statement.executeUpdate(sqlQuery);
            resultSet.close();
            statement.close();
            return "Create diet notes";
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public String InsertNotes(Long cc, String notes){
        try{
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM medicalNotes";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                long playercc = resultSet.getInt("playerCC");
                if (Objects.equals(playercc, cc)){
                    String aux = resultSet.getString("notes");
                    sqlQuery = "UPDATE medicalNotes SET notes='" + notes.concat(aux) + "', doctorCC ='" + getnCC() + "'WHERE nCC ='" + cc + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    statement.close();
                    return "Update medical notes";
                }
            }
            //create diet notes
            sqlQuery = "INSERT INTO medicalNotes (playerCC, doctorCC, notes) VALUES ('" + cc + "','" + getnCC() + "','" + notes + "')";
            statement.executeUpdate(sqlQuery);
            resultSet.close();
            statement.close();
            return "Create medical notes";
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public String ScheduleMedicalAppointments(Long playerCC, Date date, Time startTime, Time endTime){
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM medicalAppointment";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Long cc = resultSet.getLong("playerCC");
                Date d = resultSet.getDate("date");
                Time ts = resultSet.getTime("startTime");
                Time te = resultSet.getTime("endTime");
                if (Objects.equals(playerCC, cc) && Objects.equals(date, d) && Objects.equals(startTime, ts) && Objects.equals(endTime, te))
                    return "Medical Appointment already exist";
            }
            sqlQuery = "INSERT INTO medicalAppointment (doctorCC, playerCC, date, startTime, endTime) VALUES ('"
                        + getnCC() + "','"+ playerCC + "','" + date + "','" + startTime + "','" + endTime + "')";
            statement.executeUpdate(sqlQuery);
            statement.close();
            return "Medical Appointment insert in database";
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
