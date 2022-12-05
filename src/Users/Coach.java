package Users;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Coach extends CommonFeatures{

    public Coach(String email) throws SQLException {
        super(email);
    }

    public String InsertPlayersPunishement(String name, String notes, int nGames) {
        if (name==null || notes==null || String.valueOf(nGames)==null){
            return "Falta de argumentos";
        }
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM user WHERE type=2";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            String sqlQuery1 = "SELECT * FROM externalPunishment";
            ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
            while (resultSet.next()) {
                long nCC = resultSet.getLong("nCC");
                String n = resultSet.getString("name");
                if (Objects.equals(n, name)) {
                    while (resultSet1.next()) {
                        if (resultSet1.getString("playerCC") != null) {
                            sqlQuery1 = "UPDATE externalPunishment SET notes='" + notes + "', " +
                                    "numberGames='" + nGames + "' WHERE playerCC=" + nCC;
                            statement.executeUpdate(sqlQuery);
                            statement.close();
                            return "Update the notes";
                        }
                    }
                }
                sqlQuery1 = "INSERT INTO externalPunishment SET notes='" + notes + "', " +
                        "numberGames='" + nGames + "' WHERE playerCC=" + nCC;
                statement.executeUpdate(sqlQuery);
                statement.close();
                return "Insert notes";
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return "Nothing";
    }

    private void callUpPlayers(ArrayList<Long>playersCC, int idgame){
        int i=0;
        if (playersCC.size()>18 || playersCC.size()<15 || String.valueOf(idgame)==null)
            return;
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM game_player";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("idGame");
                if (Objects.equals(id, idgame))
                    return;
            }
            while(i<=playersCC.size()){
                sqlQuery = "INSERT INTO game_player SET idGame='" + idgame + "', " +
                        "playerCC=" + playersCC.get(i);
                statement.executeUpdate(sqlQuery);
                i++;
            }
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void repportNonAttendance(Long CC){
        int n=1;
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM nonAtendance";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                int cc = resultSet.getInt("playerCC");
                if (Objects.equals(cc, CC)) {
                    n = resultSet.getInt("counter");
                    n++;
                    sqlQuery = "UPDATE nonAtendance SET counter=" + n;
                    statement.executeUpdate(sqlQuery);
                    statement.close();
                    return;
                }
            }
            sqlQuery = "INSERT INTO nonAtendance SET playerCC='" + CC + "', " +
                    "counter='" + n;
            statement.executeUpdate(sqlQuery);
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
                int playercc = resultSet.getInt("playerCC");
                int idGame = resultSet.getInt("idGame");
                if (Objects.equals(playercc, cc) && Objects.equals(idGame, idgame)){
                    aux = resultSet.getString("notes");
                    sqlQuery = "UPDATE game_players SET notes='" + notes.concat(aux);
                    statement.executeUpdate(sqlQuery);
                    String sqlQuery1 = "SELECT * FROM user";
                    ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
                    while (resultSet1.next()) {
                        if(fit){
                            if(resultSet1.getBoolean("aptitude")){
                                statement.close();
                                return;
                            }
                            else {
                                sqlQuery1 = "UPDATE user SET aptitude=" + true;
                                statement.executeUpdate(sqlQuery1);
                                statement.close();
                                return;
                            }
                        }
                        else {
                            if (!resultSet1.getBoolean("aptitude")){
                                statement.close();
                                return;
                            }
                            else {
                                sqlQuery1 = "UPDATE user SET aptitude=" + false;
                                statement.executeUpdate(sqlQuery1);
                                statement.close();
                                return;
                            }
                        }
                    }
                    statement.close();
                }
            }
            //create data
            resultSet.first();
            while (resultSet.next()){
                sqlQuery = "INSERT INTO game_players SET idGames='" + idgame + "', " +
                        "notes='" + notes;
                statement.executeUpdate(sqlQuery);
                String sqlQuery1 = "SELECT * FROM user";
                ResultSet resultSet1 = statement.executeQuery(sqlQuery1);
                while (resultSet1.next()) {
                    if(fit){
                        if(resultSet1.getBoolean("aptitude")){
                            statement.close();
                            return;
                        }
                        else {
                            sqlQuery1 = "UPDATE user SET aptitude=" + true;
                            statement.executeUpdate(sqlQuery1);
                            statement.close();
                            return;
                        }
                    }
                    else {
                        if (!resultSet1.getBoolean("aptitude")){
                            statement.close();
                            return;
                        }
                        else {
                            sqlQuery1 = "UPDATE user SET aptitude=" + false;
                            statement.executeUpdate(sqlQuery1);
                            statement.close();
                            return;
                        }
                    }
                }
                statement.close();
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void SceduleTrainningSession(){

    }

}
