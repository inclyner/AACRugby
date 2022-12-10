package Users;

import logic.Appointment;
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
    public CommonFeatures(){}//efeitos de teste

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

    public Long getnCC() throws SQLException {
        Statement statement = getDbConnection().createStatement();
        String query = "SELECT email, nCC from user";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            String email = resultSet.getString("email");
            Long nCC = resultSet.getLong("nCC");
            if(this.email.equals(email))
                return nCC;
        }
        return null;
    }


    public ArrayList<Appointment> getAppointments() throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Statement statement = getDbConnection().createStatement();
        String sqlQuery = "SELECT typeUserId FROM user WHERE nCC = "+getnCC()+"";
        ResultSet resultSet1 = statement.executeQuery(sqlQuery);
        int type  = resultSet1.getInt("typeUserId");
        String query = "SELECT * FROM medicalAppointment WHERE playerCC = " + getnCC() +" OR doctorCC = " + getnCC()+"";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            String initialTime = resultSet.getString("startTime");
            String finalTime = resultSet.getString("endTime");
            String date = resultSet.getString("date");
            Long playerCC = resultSet.getLong("playerCC");
            Long doctorCC = resultSet.getLong("doctorCC");
            if(type==1)
                appointments.add(new Appointment(doctorCC, initialTime, finalTime, date));
            else if(type==2)
                appointments.add(new Appointment(playerCC,  initialTime, finalTime, date));

        }
        return appointments;

    }

    public ArrayList<Game> getGames(){
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

                String sqlQuery = "SELECT playerCC from game_player WHERE idGame = " + idGame+"";
                ResultSet resultSet1 = statement.executeQuery(sqlQuery);
                while (resultSet1.next()){
                    players.add(resultSet1.getLong("id"));
                }
                games.add(new Game(nCCCoach, horaInicio, horaFinal, local, players, equipaAdv, date));
                players.clear();
            }
            return games;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getDATABASE_URL() {
        return DATABASE_URL;
    }


    //String sqlQuery = "SELECT nCC, logged, email, name, password, birthDate, sex, phoneNumber, aptitude, position, weight, height, typeUser FROM users";

}
