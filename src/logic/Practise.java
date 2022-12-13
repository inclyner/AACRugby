package logic;

import Users.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Users.CommonFeatures.getDbConnection;

public class Practise extends Appointment{
    private String local;
    private ArrayList<Long> players;
    private int id;
    public Practise(Long nameAuthor, ArrayList<Long> players, String initialTime, String finalTime,String local, String date) {
        super(nameAuthor, initialTime, finalTime, date);
        this.players = players;
        this.local = local;
    }

    public ArrayList<Long> getPlayers() {
        return players;
    }

    public int getId() {
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from practice";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                if (getDate().equals(resultSet.getString("date")) &&
                        local.equals(resultSet.getString("local")) &&
                        getnCCAuthor().equals(resultSet.getLong("coachCC")) &&
                        getInitialTime().equals(resultSet.getString("startTime")) &&
                        getFinalTime().equals(resultSet.getString("endTime")))
                    id=resultSet.getInt("id");
            }
            return id;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "Practise{" +
                "local='" + local + '\'' +
                ", players=" + players +
                '}';
    }
}
