package Users;

import logic.Game;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public abstract class CommonFeatures {

    private Long nCC;
    private String name;
    private String email;
    private String sex;
    private String birthDate;
    private Long phoneNumber;
    private String DATABASE_URL;
    private static Connection dbConn;
    public CommonFeatures(){

    };//efeitos de teste

    public CommonFeatures(String email) throws SQLException {this.email = email;}

    
    public static Connection createDb() throws SQLException {
        //File f = new File("AACRugby.db");
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        return dbConn;
    }

    public static Connection getDbConnection() throws SQLException {
       return dbConn = createDb();
    }

    public ArrayList<String> getGames(){
        ArrayList<Game> games = new ArrayList<>();
        ArrayList<Long> players = new ArrayList<>();

        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from game";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int idGame = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String equipaAdv = resultSet.getString("equipaAdversaria");
                String horaInicio = resultSet.getString("horaInicial");
                String horaFinal = resultSet.getString("horaFinal");
                String local = resultSet.getString("local");
                Long nCCCoach = resultSet.getLong("coachCC");

                String sqlQuery = "SELECT idGame from game_player WHERE idGame = " + idGame+"";
                ResultSet resultSet1 = statement.executeQuery(sqlQuery);
                while (resultSet1.next()){
                    players.add(resultSet1.getLong("id"));
                }
                games.add(new Game(nCCCoach, horaInicio, horaFinal, local, players, equipaAdv));
                players.clear();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;}


    public String getDATABASE_URL() {
        return DATABASE_URL;
    }


    //String sqlQuery = "SELECT nCC, logged, email, name, password, birthDate, sex, phoneNumber, aptitude, position, weight, height, typeUser FROM users";

}
