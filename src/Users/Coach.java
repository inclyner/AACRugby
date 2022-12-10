package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Coach extends CommonFeatures {

    private ArrayList<Long> cc = new ArrayList<Long>();

    public ArrayList<Long> getCc() {
        return cc;
    }

    public Coach(String email) throws SQLException {
        super(email);
    }

    public String InsertPlayersPunishement(String name, String notes, int nGames){
        //Falta obter cc do coach para meter na base de dados a ultima pessoa q alterou tudo
        if (name == null || notes == null /*|| String.valueOf(nGames) == null*/) {
            return "Falta de argumentos";
        }
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT nCC, name FROM user WHERE typeUserId=2";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                long nCC = resultSet.getLong("nCC");
                String n = resultSet.getString("name");
                if (Objects.equals(n, name)) {
                    String sqlQuery1 = "SELECT * FROM externalPunishments";
                    ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
                    while (resultSet1.next()) {
                        if (Objects.equals(resultSet1.getLong("playerCC"), nCC)) {
                            notes=resultSet1.getString("notes").concat(notes);
                            nGames+=resultSet1.getInt("numberGames");
                            sqlQuery1 = "UPDATE externalPunishments SET coachCC ='" + 423455848L + "', notes ='" + notes + "', numberGames ='" + nGames + "'WHERE playerCC=" + nCC;
                            statement.executeUpdate(sqlQuery1);
                            resultSet.close();
                            resultSet1.close();
                            statement.close();
                            return "Update the notes";
                        }
                    }
                    //Alterar a parte de coachCC, esta mal
                    sqlQuery1 = "INSERT INTO externalPunishments (playerCC, coachCC, notes, numberGames) VALUES ('"+nCC+"','"+nCC+"','"+notes+"','"+nGames+"')";
                    statement.executeUpdate(sqlQuery1);
                    statement.close();
                    return "Insert notes";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Nothing";
    }

    public String callUpPlayers(ArrayList<Long>playersCC, int idgame) {
        int i=0;
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
                    sqlQuery = "UPDATE nonAtendance SET counter='" + n + "'WHERE nCC ='" + CC + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    statement.close();
                    return;
                }
            }
            sqlQuery = "INSERT INTO nonAtendance (playerCC, counter) VALUES ('" + CC + "','" + n + "')";
            statement.executeUpdate(sqlQuery);
            resultSet.close();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void insertNotesAboutPlayer(Long cc, int idgame, String notes, boolean fit){
        String aux;
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM game_players";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            //update
            while (resultSet.next()) {
                long playercc = resultSet.getInt("playerCC");
                int idGame = resultSet.getInt("idGame");
                if (Objects.equals(playercc, cc) && Objects.equals(idGame, idgame)) {
                    aux = resultSet.getString("notes");
                    sqlQuery = "UPDATE game_players SET notes='" + notes.concat(aux) + "'WHERE nCC ='" + cc + "'AND idGame='" + idgame + "';";
                    statement.executeUpdate(sqlQuery);
                    resultSet.close();
                    String sqlQuery1 = "SELECT nCC, aptitude FROM user WHERE typeUserId=2";
                    ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
                    while (resultSet1.next()) {
                        if (Objects.equals(resultSet1.getLong("nCC"), cc)) {
                            if (Objects.equals(resultSet1.getBoolean("aptitude"), fit)) {
                                resultSet1.close();
                                statement.close();
                                return;
                            } else {
                                sqlQuery1 = "UPDATE user SET aptitude='" + fit + "'WHERE nCC ='" + cc + "';";
                                statement.executeUpdate(sqlQuery1);
                                resultSet1.close();
                                statement.close();
                                return;
                            }
                        }
                    }
                }
            }
            //create data
            sqlQuery = "INSERT INTO game_players (idGame, playerCC, notes) VALUES ('" + idgame + "','" + cc + "','" + notes + "')";
            statement.executeUpdate(sqlQuery);
            String sqlQuery1 = "SELECT nCC, aptitude FROM user WHERE typeUserId=2";
            ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
            while (resultSet1.next()) {
                if (Objects.equals(resultSet1.getLong("nCC"), cc)) {
                    if (Objects.equals(resultSet1.getBoolean("aptitude"), fit)) {
                        resultSet1.close();
                        statement.close();
                        return;
                    } else {
                        sqlQuery1 = "UPDATE user SET aptitude='" + fit + "'WHERE nCC ='" + cc + "';";
                        statement.executeUpdate(sqlQuery1);
                        resultSet1.close();
                        statement.close();
                        return;
                    }
                }
            }
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void ScheduleTrainingSession(ArrayList<Long>playersCC, int idPractice, String local, Date date, Time startTime, Time endTime, long coachCC){
        int i=0;
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM practice_player";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("idPractice");
                if (Objects.equals(id, idPractice))
                    return;
            }
            while(i<=playersCC.size()){
                sqlQuery = "INSERT INTO game (idPractice, local, date, startTime, endTime, coachCC) VALUES ('"
                        + idPractice + "','"+ local + "','" + date + "','" + startTime + "','" + endTime + "','" + coachCC +"')";
                statement.executeUpdate(sqlQuery);
                i++;
            }
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


}
