package Users;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
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
                int n = resultSet.getString("name");
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

    private void callUpPlayers(){

    }

    private void repportNonAttendance(){

    }

    private void insertNotesAboutPlayer(){

    }

    private void SceduleTrainningSession(){

    }

}
