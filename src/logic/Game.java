package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Users.CommonFeatures.getDbConnection;

public class Game extends Appointment{

    private int id;
    private String local;
    private ArrayList<Long> players;
    private String oponentTeam;

    public Game(int id,Long nCCAuthor, String initialTime, String finalTime,String local, String oponentTeam, String date, ArrayList<Long> players) {
        super(nCCAuthor, initialTime, finalTime, date);
        this.id = id;
        this.local = local;
        this.players = players;
        this.oponentTeam = oponentTeam;
    }


    public String getOponentTeam() {
        return oponentTeam;
    }

    public String getLocal() {
        return local;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", local='" + local + '\'' +
                ", players=" + players +
                ", oponentTeam='" + oponentTeam + '\'' +
                '}';
    }

    public int getIdPresent() {
        return id;
    }

    public int getId() {
        try {
            Statement statement = getDbConnection().createStatement();
            String query = "SELECT * from game";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                if (oponentTeam.equals(resultSet.getString("equipaAdversaria")) &&
                        getDate().equals(resultSet.getString("date")) &&
                        local.equals(resultSet.getString("local")) &&
                        getnCCAuthor().equals(resultSet.getLong("coachCC")) &&
                        getInitialTime().equals(resultSet.getString("horaInicial")) &&
                        getFinalTime().equals(resultSet.getString("horaFinal")))
                    id=resultSet.getInt("id");
            }
            return id;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public ArrayList<Long> getPlayers() {
        return players;
    }
}
