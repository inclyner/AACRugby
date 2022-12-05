package Users;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Coach extends CommonFeatures{

    public Coach(Long nCC, String name, String email, String sex, String birthDate, Long phoneNumber) throws SQLException {
        super(nCC, name, email, sex, birthDate, phoneNumber);
    }

    private void InsertPlayersPunishement(String name, String notes, int nGames) {
        if (name==null || notes==null || String.valueOf(nGames)==null){
            return;
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
                            return;
                        }
                    }
                    sqlQuery1 = "INSERT INTO externalPunishment SET notes='" + notes + "', " +
                            "numberGames='" + nGames + "' WHERE playerCC=" + nCC;
                    statement.executeUpdate(sqlQuery);
                    statement.close();
                }
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
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
                sqlQuery = "INSERT INTO game_players SET idGame='" + idgame + "', " +
                        "playerCC='" + playersCC.get(i);
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

    //criar campo a dizer notes e idgames no users
    private void insertNotesAboutPlayer(Long cc, int idgame, String notes, boolean fit){
        try {
            Statement statement = getDbConnection().createStatement();
            String sqlQuery = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {

            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void SceduleTrainningSession(){

    }

}
