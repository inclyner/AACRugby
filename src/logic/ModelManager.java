package logic;

//import Users.CommonFeatures;

import Users.Coach;
import Users.Doctor;
import Users.Manager;
import Users.Player;
import com.calendarfx.model.Entry;

import javax.mail.MessagingException;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ModelManager {
    Manager manager= new Manager(getEmailLogged());
    Coach coach=new Coach(getEmailLogged());
    Doctor doctor = new Doctor(getEmailLogged());
    Player player = new Player();

    public static String emailLogged;
    private static String nameLogged;



    public String getEmailLogged() {
        return emailLogged;
    }

    public String getNameLogged() {
        return nameLogged;
    }

    public ModelManager() throws SQLException {}
    public boolean login(String email, String password) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, password, logged from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String e = resultSet.getString("email");
            String p = resultSet.getString("password");
            boolean logged = resultSet.getBoolean("logged");
            if(email.equals(e) && password.equals(p)) {
                sqlQuery="UPDATE user SET logged="+true+"";
                statement.executeUpdate(sqlQuery);
                resultSet.close();
                statement.close();
                emailLogged = email;
                nameLogged = getNameUser(email);
                return true;
            }
        }
        resultSet.close();
        statement.close();
        return false;
    }

    public String insertUser(int type, String nCC, String name, String email, String pass, String sex, String birthDate, String phoneNumber, String aptitude, String height, String weight, String position) throws SQLException, MessagingException {
        return manager.insertUser(type, nCC, name, email, pass, sex,birthDate, phoneNumber,  aptitude, height, weight,position);
    }

    public ArrayList<Player> getAllPlayer(){
        Manager manager1 = new Manager();
        return manager1.getPlayers();
    }

    public String approveReq(Long id, boolean answer){
        Manager manager1 = new Manager();
        return manager1.approveChangeRequest(id, answer);
    }

    public ArrayList<Coach> getAllCoach(){
        Manager manager1 = new Manager();
        return  manager1.getCoaches();
    }

    public ArrayList<Manager> getAllManager(){
        Manager manager1 = new Manager();
        return  manager1.getManagers();
    }

    public ArrayList<Doctor> getAllDoctor(){
        Manager manager1 = new Manager();
        return  manager1.getDoctors();
    }
    public ArrayList<ChangeRequest> getAllRequests() throws SQLException {
        Manager manager1 = new Manager();
        return  manager1.getChangeRequests();
    }
    public void deleteUsers(ArrayList<String> emails) throws SQLException {
        Manager manager1 = new Manager(emailLogged);
        manager1.deleteUser(emails);
    }

    public String insertMedicalAppointment(Long playerCC, String date, String startTime) throws SQLException, ParseException {
        return doctor.ScheduleMedicalAppointments(playerCC, date, startTime);
    }

    public ArrayList<Game> getAllGames(){
        Manager manager1 = new Manager();
        return  manager1.getGames();
    }

    public ArrayList<Practise> getAllPractise(){
        return  manager.getPractise();
    }

    public ArrayList<MedicalAppointment> getAllAppointments(){
        return manager.getAppointments();
    }

    public String getNcc(String email) throws SQLException {

        return String.valueOf(manager.getnCC(email));
    }

    public String callup(ArrayList<Long> ncc, int id) throws SQLException {
        this.coach= new Coach(getEmailLogged());
        return coach.callUpPlayers(ncc, id);
    }
    public Long getnCCChange(String newInfo, String oldValue) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from changeRequest WHERE oldInfo='"+oldValue+"' AND newInfo='"+newInfo+"'";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String o = resultSet.getString("oldInfo");
            String n = resultSet.getString("newInfo");
            if(o.equals(oldValue) && n.equals(newInfo)) {
                Long nCC = resultSet.getLong("playerCC");
                resultSet.close();
                statement.close();
                return nCC;
            }
        }
        resultSet.close();
        statement.close();
        dbConn.close();
        return null;
    }

    public String getNameUser(String email) throws SQLException {
        return coach.getNameUser(email);
    }
    public Long getNCccName(String name) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT nCC, name from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("name");
            Long ncc = resultSet.getLong("nCC");
            if(n.equals(name)) {
                resultSet.close();
                statement.close();
                return ncc;
            }
        }
        resultSet.close();
        statement.close();
        dbConn.close();
        return null;
    }

    public String getNameUserNcc(String nCC) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT nCC, name from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String name = resultSet.getString("name");
                resultSet.close();
                statement.close();
                return name;
            }
        }
        resultSet.close();
        statement.close();
        dbConn.close();
        return null;
    }

    private String getNCCEmail(String email) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, nCC from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("email");
            if (n.equals(email)) {
                String name = resultSet.getString("nCC");
                resultSet.close();
                statement.close();
                return name;
            }
        }
        resultSet.close();
        statement.close();
        dbConn.close();
        return null;
    }

    public String getPhoneNumberUserNcc(String nCC) throws SQLException {

        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = null;
        resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String pn = resultSet.getString("phoneNumber");
                resultSet.close();
                statement.close();
                return pn;
            }

        }

        resultSet.close();
        statement.close();
        return "error retrieving...";
    }

    public String getHeightnCC(String nCC) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String height = resultSet.getString("height-cm");
                resultSet.close();
                statement.close();
                return height;
            }

        }
        resultSet.close();
        statement.close();
        return "Not Available";
    }

    public String getWeightnCC(String nCC) throws SQLException {

        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = null;
        dbConn = DriverManager.getConnection(DATABASE_URL);

        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String weight = resultSet.getString("weight-kg");
                resultSet.close();
                statement.close();
                return weight;
            }

        }
        resultSet.close();
        statement.close();
        return "Not Available";
    }

    public String getAptitudenCC(String nCC) throws SQLException {

        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = null;
        dbConn = DriverManager.getConnection(DATABASE_URL);

        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String aptitude = resultSet.getString("aptitude");
                resultSet.close();
                statement.close();
                if(aptitude.equals("true"))
                    return "Is fit";
                else if(aptitude.equals("false"))
                    return "Not fit";
            }
        }
        resultSet.close();
        statement.close();
        return "Not Available";
    }

    public String getpositionnCC(String nCC) throws SQLException {

        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = null;
        dbConn = DriverManager.getConnection(DATABASE_URL);

        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String position = resultSet.getString("position");
                resultSet.close();
                statement.close();
                return position;
            }
        }
        resultSet.close();
        statement.close();
        return "Not Available";
    }

    public String getBirthDatenCC(String nCC) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String birthDate = resultSet.getString("birthDate");
                resultSet.close();
                statement.close();
                return birthDate;
            }

        }
        resultSet.close();
        statement.close();
        return null;
    }

    public int checksTypeUser(String email) throws SQLException{
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT email, typeUserId from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String e = resultSet.getString("email");
            if(email.equals(e)) {
                int type = resultSet.getInt("typeUserId");
                resultSet.close();
                statement.close();
                return type;
            }
        }
        resultSet.close();
        statement.close();
        return 0;
    }

    public String getEmailUserNcc(String nCC) throws SQLException {
        File f = new File("bd\\AACRugby.db");
        String DATABASE_URL = "jdbc:sqlite:" + f.getAbsolutePath();
        Connection dbConn = null;
        dbConn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = dbConn.createStatement();
        String sqlQuery = "SELECT * from user";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            String n = resultSet.getString("nCC");
            if(n.equals(nCC)) {
                String name = resultSet.getString("email");
                resultSet.close();
                statement.close();
                return name;
            }

        }
        resultSet.close();
        statement.close();
        dbConn.close();
        return null;

    }

    public void requestChange(String oldInfo, String newInfo, long l){
        Player p = new Player();
        p.requestChangePersonalData(oldInfo, newInfo, l);
    }

    public String getNameOfGame(Game p) {
        return coach.getNameGame(p);
    }

    public ArrayList<Player> getPlayersAvailable(){
        return coach.getPlayersAvailable();
    }


    public void getinsertNotesAboutPlayer(Long ncc, int id, String text, boolean b) {
        coach.insertNotesAboutPlayer(ncc, id, text, b);
    }

    public void getinsertDiet(Long nCC, String notes){
        doctor.InsertDiet(nCC,notes);
    }

    public void getinsertAppointmentNotes(Long ncc, String notes) {
        doctor.InsertNotes(ncc,notes);
    }

    public ArrayList<Entry<String>> getGamesForCalendar() {

        ArrayList<Entry<String>> games = new ArrayList<>();

        for (Game game : getAllGames()) {
            Entry<String> entry = new Entry<>("Game: AAC - " + game.getOponentTeam());

            ZonedDateTime begin = Utils.Utils.convertDateTimeToEntryCalendar(game.getDate(), game.getInitialTime());
            ZonedDateTime end = Utils.Utils.convertDateTimeToEntryCalendar(game.getDate(), game.getFinalTime());

            entry.setInterval(begin, end);

            games.add(entry);
        }

        return games;
    }

    public ArrayList<Entry<String>> getMedicalAppointmentsForCalendar(String loggedEmail, boolean player) throws SQLException {

        ArrayList<Entry<String>> medicalAppointments = new ArrayList<>();

        for (MedicalAppointment appointment : getAllAppointments()) {

            Entry<String> entry = null;

            long playerCC = appointment.getPlayerCC();
            String playerNameCC = playerCC + "";
            String playerEmail = getEmailUserNcc(playerNameCC);

            long doctorCC = appointment.getnCCAuthor();
            String doctorEmailCC = doctorCC + "";
            String doctorEmail = getEmailUserNcc(doctorEmailCC);

            // Se o email do appointment for igual ao doctor loggado...
            if (doctorEmail.equals(loggedEmail) && !player) {
                String playerName = getNameUserNcc(playerNameCC);
                entry = new Entry<>("Medical Appointment with player - " + playerName);
            } else if (playerEmail.equals(loggedEmail) && player) {
                String doctorName = getNameUserNcc(doctorEmailCC);
                entry = new Entry<>("Medical Appointment with doctor - " + doctorName);
            }

            if (entry != null) {
                ZonedDateTime begin = Utils.Utils.convertDateTimeToEntryCalendar(appointment.getDate(), appointment.getInitialTime());
                ZonedDateTime end = Utils.Utils.convertDateTimeToEntryCalendar(appointment.getDate(), appointment.getFinalTime());

                entry.setInterval(begin, end);

                medicalAppointments.add(entry);
            }
        }


        return medicalAppointments;
    }

    public ArrayList<Entry<String>> getPracticesForCalendar(boolean player) throws SQLException {

        ArrayList<Entry<String>> practices = new ArrayList<>();

        for (Practise practise : getAllPractise()) {
            Entry<String> entry = null;
            if(player){
                String playerEmail = getEmailLogged();
                String playerCCString = getNCCEmail(playerEmail);

                assert playerCCString != null;
                Long playerCC = Long.parseLong(playerCCString);

                if(practise.getPlayers().contains(playerCC)){
                    entry = new Entry<>("Team Practice");
                }
            } else {
                entry = new Entry<>("Team Practice");
            }

            if(entry != null) {
                ZonedDateTime begin = Utils.Utils.convertDateTimeToEntryCalendar(practise.getDate(), practise.getInitialTime());
                ZonedDateTime end = Utils.Utils.convertDateTimeToEntryCalendar(practise.getDate(), practise.getFinalTime());

                entry.setInterval(begin, end);
                practices.add(entry);
            }
        }

        return practices;
    }

    public String schedulePractices(ArrayList<Long>playersCC, String local, String date, String startTime, String endTime) throws SQLException, ParseException {
        return coach.scheduleTrainingSession(playersCC, local, date, startTime, endTime);
    }

}