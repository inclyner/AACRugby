package Users;

import logic.Game;
import logic.MedicalAppointment;
import logic.Practise;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;

public class Coach extends CommonFeatures {

    private ArrayList<Long> cc = new ArrayList<Long>();
    CommonFeatures cf;

    public ArrayList<Long> getCc() {
        return cc;
    }

    public Coach(String email) throws SQLException {
        super(email);
    }

    public String InsertPlayersPunishement(Long ncc, String notes, int nGames){
        if (ncc == null || notes == null /*|| String.valueOf(nGames) == null*/) {
            return "Falta de argumentos";
        }
        try {
            Statement statement = getDbConnection().createStatement();
                String sqlQuery1 = "SELECT * FROM externalPunishments";
                ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
                while (resultSet1.next()) {
                    if (Objects.equals(resultSet1.getLong("playerCC"), ncc)) {
                        notes=resultSet1.getString("notes").concat(notes);
                        nGames+=resultSet1.getInt("numberGames");
                        sqlQuery1 = "UPDATE externalPunishments SET coachCC ='" + getnCC(getEmail()) + "', notes ='" + notes + "', numberGames ='" + nGames + "'WHERE playerCC=" + ncc;
                        statement.executeUpdate(sqlQuery1);
                        resultSet1.close();
                        statement.close();
                        closeDb();
                        return "Update the notes";
                    }
                }
                //Alterar a parte de coachCC, esta mal
                sqlQuery1 = "INSERT INTO externalPunishments (playerCC, coachCC, notes, numberGames) VALUES ('"+ncc+"','"+getnCC(getEmail())+"','"+notes+"','"+nGames+"')";
                statement.executeUpdate(sqlQuery1);
                statement.close();
                closeDb();
                return "Insert notes";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String callUpPlayers(ArrayList<Long> playersCC, int idgame) {
        int i=0;
        System.out.println(playersCC.size());
        if (playersCC.size()-1>18) /*|| String.valueOf(idgame)==null*/
            return "Extra players";
        else if (playersCC.size()-1<15)
            return "Not enough players";
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM game_player";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("idGame");
                if (Objects.equals(id, idgame)){
                    resultSet.close();
                    statement.close();
                    closeDb();
                    return "Already have call up of this game";
                }
            }
            while(i<playersCC.size()){
                sqlQuery = "INSERT INTO game_player (idGame, playerCC) VALUES ('"+idgame+"','"+playersCC.get(i)+"')";
                statement.executeUpdate(sqlQuery);
                i++;
            }
            resultSet.close();
            statement.close();
            closeDb();
            return "Insert date about call up in the database";
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void repportNonAttendance(Long CC){
        int n=1;
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM nonAtendance";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                long cc = resultSet.getInt("playerCC");
                if (Objects.equals(cc, CC)) {
                    n = resultSet.getInt("counter");
                    n++;
                    sqlQuery = "UPDATE nonAtendance SET counter='" + n + "'WHERE playerCC ='" + CC + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    statement.close();
                    closeDb();
                    return;
                }
            }
            sqlQuery = "INSERT INTO nonAtendance (playerCC, counter) VALUES ('" + CC + "','" + n + "')";
            statement.executeUpdate(sqlQuery);
            resultSet.close();
            statement.close();
            closeDb();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public void insertNotesAboutPlayer(Long cc, int idgame, String notes, boolean fit){
        String aux;
        String sqlQuery2;
        try {
            closeDb();
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM game_player";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            //update
            while (resultSet.next()) {
                long playercc = resultSet.getInt("playerCC");
                int idGame = resultSet.getInt("idGame");
                if (Objects.equals(playercc, cc) && Objects.equals(idGame, idgame)) {
                    aux = resultSet.getString("notes");
                    if(aux!=null)
                        sqlQuery2 = "UPDATE game_player SET notes='" + notes.concat(aux) + "' WHERE playerCC ='" + cc + "' AND idGame='" + idgame + "';";
                    else
                        sqlQuery2 = "UPDATE game_player SET notes='" + notes + "' WHERE playerCC ='" + cc + "' AND idGame='" + idgame + "';";
                    statement.executeUpdate(sqlQuery2);
                    statement.close();
                    resultSet.close();
                    closeDb();
                    statement=getDbConnection().createStatement();
                    String sqlQuery1 = "SELECT nCC, aptitude FROM user WHERE typeUserId=2";
                    ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
                    while (resultSet1.next()) {
                        if (Objects.equals(resultSet1.getLong("nCC"), cc)) {
                            System.out.println(resultSet1.getString("aptitude"));
                            System.out.println(fit);
                            if (resultSet1.getString("aptitude").equals(String.valueOf(fit))) {
                                resultSet1.close();
                                statement.close();
                                closeDb();
                                return;
                            } else {
                                sqlQuery1 = "UPDATE user SET aptitude='" + fit + "'WHERE nCC ='" + cc + "';";
                                statement.executeUpdate(sqlQuery1);
                                resultSet1.close();
                                statement.close();
                                closeDb();
                                return;
                            }
                        }
                    }
                }
            }
            //create data
            closeDb();
            statement = getDbConnection().createStatement();
            sqlQuery = "INSERT INTO game_player (idGame, playerCC, notes) VALUES ('" + idgame + "','" + cc + "','" + notes + "')";
            statement.executeUpdate(sqlQuery);
            String sqlQuery1 = "SELECT nCC, aptitude FROM user WHERE typeUserId=2";
            ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
            while (resultSet1.next()) {
                if (Objects.equals(resultSet1.getLong("nCC"), cc)) {
                    if (Objects.equals(resultSet1.getBoolean("aptitude"), fit)) {
                        resultSet1.close();
                        statement.close();
                        closeDb();
                        return;
                    } else {
                        sqlQuery1 = "UPDATE user SET aptitude='" + fit + "'WHERE nCC ='" + cc + "';";
                        statement.executeUpdate(sqlQuery1);
                        resultSet1.close();
                        statement.close();
                        closeDb();
                        return;
                    }
                }
            }
            statement.close();
            closeDb();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public String scheduleTrainingSession(ArrayList<Long>playersCC, String local, String date, String startTime, String endTime) throws SQLException, ParseException {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        Date dataAtual = cal.getTime();
        Date practiseDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        if(dataAtual.after(practiseDate)) return "You need a time machine for that";

        LocalTime initialTime = LocalTime.parse(startTime);
        LocalTime finalTime = LocalTime.parse(endTime);

        if(finalTime.isBefore(initialTime)) return "Wrong Values";

        Statement statement = getDbConnection().createStatement();
        for(MedicalAppointment appointment: getAppointments()){
            if(getAppointments().size()==0) break;
            LocalTime begin = LocalTime.parse(appointment.getInitialTime());
            LocalTime end = LocalTime.parse(appointment.getFinalTime());
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(appointment.getDate());
            if(playersCC.contains(appointment.getPlayerCC())) {
                if (date1.equals(practiseDate)) {
                    if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                        playersCC.remove(appointment.getPlayerCC());
                    else if(begin.equals(initialTime) || end.equals(finalTime))
                        playersCC.remove(appointment.getPlayerCC());
                }
            }
        }

        for(Game game: getGames()){
            if(getGames().size()==0) break;
            LocalTime begin = LocalTime.parse(game.getInitialTime());
            LocalTime end = LocalTime.parse(game.getFinalTime());
            Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(game.getDate());
            if(practiseDate==date1 && getnCC(this.getEmail()).equals(game.getnCCAuthor())){
                if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                    return "You are busy with a game, choose another time";
                if(begin.equals(initialTime) || end.equals(finalTime))
                    return "You are busy with a game, choose another time";
            }
            for(Long p: playersCC){
                if(game.getPlayers().contains(p)){
                    if(date1==practiseDate) {
                        if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                            playersCC.remove(p);
                        else if(begin.equals(initialTime) || end.equals(finalTime))
                            playersCC.remove(p);
                    }
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
                else if(begin.equals(initialTime) || end.equals(finalTime))
                    return "You are busy with a practise, choose another time";
            }
            for(Long p: playersCC){
                if(practise.getPlayers().contains(p)){
                    if(date1==practiseDate) {
                        if (begin.isAfter(initialTime) || end.isBefore(finalTime))
                            playersCC.remove(p);
                        else if(begin.equals(initialTime) || end.equals(finalTime))
                            playersCC.remove(p);
                    }
                }
            }
        }
        try {
            String sqlQuery= "INSERT INTO practice VALUES(NULL,'"+local+"','"+date+"','"+startTime+"','"+endTime+"','"+getnCC(getEmail())+"')";
            statement.executeUpdate(sqlQuery);
            statement.close();

            sqlQuery = "SELECT id from practice WHERE local='"+ local +"' AND date='"+ date +"'";
            ResultSet resultSet1 = statement.executeQuery(sqlQuery);
            int idGame = resultSet1.getInt("id");
            System.out.println(idGame);
            for(Long p: playersCC) {
                sqlQuery = "INSERT INTO practice_player (idPractice, playerCC) VALUES(" + idGame + "," + p + ")";
                statement.executeUpdate(sqlQuery);
                statement.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException();

        }
        closeDb();
        return "Operation Successful";
    }


}
