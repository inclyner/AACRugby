package Users;

import logic.ChangeRequest;
import logic.Game;
import logic.MedicalAppointment;
import logic.Practise;

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
        Connection db = DriverManager.getConnection(DATABASE_URL);
        dbConn = db;
        return dbConn;
    }

    public void closeDb() throws SQLException {
        dbConn.close();
    }

    public static Connection getDbConnection() throws SQLException {
        return dbConn = createDb();
    }

    public Long getnCC(String e) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        String query = "SELECT email, nCC from user";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            String email = resultSet.getString("email");
            Long nCC = resultSet.getLong("nCC");
            if(email.equals(e)) {
                closeDb();
                return nCC;
            }
        }
        closeDb();
        return null;
    }

    public String getEmail() {
        return email;
    }
    public ArrayList<ChangeRequest> getChangeRequests() throws SQLException {
        ArrayList<ChangeRequest> changeRequests = new ArrayList<>();
        Statement statement = getDbConnection().createStatement();
        String query = "SELECT * FROM changeRequest";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            String newInfo = resultSet.getString("newInfo");
            String oldInfo = resultSet.getString("oldInfo");
            Long playerCC = resultSet.getLong("playerCC");
            changeRequests.add(new ChangeRequest(newInfo, oldInfo, playerCC));
        }
        closeDb();
        return changeRequests;

    }

    public ArrayList<MedicalAppointment> getAppointments() throws SQLException {
        ArrayList<MedicalAppointment> appointments = new ArrayList<>();
        Statement statement = getDbConnection().createStatement();
        String query = "SELECT * FROM medicalAppointment";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            String initialTime = resultSet.getString("startTime");
            String finalTime = resultSet.getString("endTime");
            String date = resultSet.getString("date");
            Long playerCC = resultSet.getLong("playerCC");
            Long doctorCC = resultSet.getLong("doctorCC");
            appointments.add(new MedicalAppointment(doctorCC, initialTime, finalTime, date, playerCC));
        }
        closeDb();
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
            closeDb();

            return games;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Practise> getPractise(){
        ArrayList<Practise> practises = new ArrayList<>();
        ArrayList<Long> players = new ArrayList<>();

        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from practice";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int idPractice = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String horaInicio = resultSet.getString("startTime");
                String horaFinal = resultSet.getString("endTime");
                String local = resultSet.getString("local");
                Long nCCCoach = resultSet.getLong("coachCC");
                String sqlQuery = "SELECT playerCC from practice_player WHERE idPractice = " + idPractice+"";
                ResultSet resultSet1 = statement.executeQuery(sqlQuery);
                while (resultSet1.next()){
                    players.add(resultSet1.getLong("playerCC"));
                }
                practises.add(new Practise(nCCCoach, players,horaInicio,horaFinal,local,date));
                players.clear();
            }
            closeDb();
            return practises;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from user WHERE typeUserId=2";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long nCCPlayer = resultSet.getLong("nCC");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String sex = resultSet.getString("sex");
                String birthDate = resultSet.getString("birthDate");
                Long phoneNumber = resultSet.getLong("phoneNumber");
                players.add(new Player(nCCPlayer, name, email, sex, birthDate, phoneNumber));
            }
            closeDb();
            return players;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Player> getPlayersAvailable() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from user WHERE typeUserId=2";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long nCCPlayer = resultSet.getLong("nCC");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String sex = resultSet.getString("sex");
                String birthDate = resultSet.getString("birthDate");
                Long phoneNumber = resultSet.getLong("phoneNumber");
                Boolean aptitude = resultSet.getBoolean("aptitude");
                if(aptitude){
                    players.add(new Player(nCCPlayer, name, email, sex, birthDate, phoneNumber));
                }
            }
            closeDb();
            return players;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Manager> getManagers(){
        ArrayList<Manager> managers = new ArrayList<>();
        try{
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from user WHERE typeUserId=4";
            ResultSet resultSet = statement.executeQuery(query);
            String email = resultSet.getString("email");
            while (resultSet.next()) {
                managers.add(new Manager(email));
            }
            closeDb();
            return managers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Coach> getCoaches(){
        ArrayList<Coach> coaches = new ArrayList<>();
        try{
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from user WHERE typeuserId=3";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String email = resultSet.getString("email");
                coaches.add(new Coach(email));
            }
            closeDb();
            return coaches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Doctor> getDoctors(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        try{
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from user WHERE typeuserId=1";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Long nCCDoctor = resultSet.getLong("nCC");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String sex = resultSet.getString("sex");
                String birthDate = resultSet.getString("birthDate");
                Long phoneNumber = resultSet.getLong("phoneNumber");
                doctors.add(new Doctor(nCCDoctor,name,email,sex,birthDate,phoneNumber));
            }
            closeDb();
            return doctors;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getDATABASE_URL() {
        return DATABASE_URL;
    }


    //String sqlQuery = "SELECT nCC, logged, email, name, password, birthDate, sex, phoneNumber, aptitude, position, weight, height, typeUser FROM users";

}
