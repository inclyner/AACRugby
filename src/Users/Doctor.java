package Users;

import logic.MedicalAppointment;
import logic.Practise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Doctor extends CommonFeatures{
    public Doctor(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(email);
    }

    public Doctor(String email) throws SQLException {
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
                    sqlQuery = "UPDATE dietNotes SET notes='" + notes.concat(aux) + "', doctorCC ='" + getnCC(getEmail()) + "'WHERE nCC ='" + cc + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    statement.close();
                    return "Update diet notes";
                }
            }
            //create diet notes
            sqlQuery = "INSERT INTO dietNotes (playerCC, doctorCC, notes) VALUES ('" + cc + "','" + getnCC(getEmail()) + "','" + notes + "')";
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
                    sqlQuery = "UPDATE medicalNotes SET notes='" + notes.concat(aux) + "', doctorCC ='" + getnCC(getEmail()) + "'WHERE nCC ='" + cc + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    statement.close();
                    return "Update medical notes";
                }
            }
            //create diet notes
            sqlQuery = "INSERT INTO medicalNotes (playerCC, doctorCC, notes) VALUES ('" + cc + "','" + getnCC(getEmail()) + "','" + notes + "')";
            statement.executeUpdate(sqlQuery);
            resultSet.close();
            statement.close();
            return "Create medical notes";
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public String ScheduleMedicalAppointments(Long playerCC, String date, String startTime, String endTime) throws ParseException, SQLException {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        Date dataAtual = cal.getTime();
        Date practiseDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        if(dataAtual.after(practiseDate)) return "You need a time machine for that";

        LocalTime initialTime = LocalTime.parse(startTime);
        LocalTime finalTime = LocalTime.parse(endTime);

        for(MedicalAppointment appointment: getAppointments()){
            if(getAppointments().size()==0) break;
            LocalTime begin = LocalTime.parse(appointment.getInitialTime());
            LocalTime end = LocalTime.parse(appointment.getFinalTime());
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(appointment.getDate());
            if(appointment.getPlayerCC().equals(playerCC) && getnCC(this.getEmail()).equals(appointment.getnCCAuthor())) {
                if (date1 == practiseDate) {
                    if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                       return "Player is not able to attend at this time.";
                }
            }
        }
        for(Practise practise: getPractise()){
            if(getPractise().size()==0) break;
            LocalTime begin = LocalTime.parse(practise.getInitialTime());
            LocalTime end = LocalTime.parse(practise.getFinalTime());
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(practise.getDate());
            if(practiseDate==date1 && getnCC(this.getEmail()).equals(practise.getnCCAuthor())){
                if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                    return "You are busy with a practise, choose another time";
            }
                if(practise.getPlayers().contains(playerCC)){
                    if(date1==practiseDate) {
                        if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                            return "Player is not able to attend at this time.";
                    }
                }
            }


        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "INSERT INTO medicalAppointment VALUES (NULL,'"
                        + getnCC(getEmail()) + "','"+ playerCC + "','" + date + "','" + startTime + "','" + endTime + "')";
            statement.executeUpdate(sqlQuery);
            statement.close();
            closeDb();
            return "Medical Appointment insert in database";
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
