package logic;

import java.util.ArrayList;

public class Game extends Appointment{

    private int id;
    private String local;
    private String date;
    private ArrayList<Long> players;
    private String oponentTeam;

    public Game(Long nCCAuthor, String initialTime, String finalTime,String local, ArrayList<Long> players, String oponentTeam, String date) {
        super(nCCAuthor, initialTime, finalTime, date);
        this.local = local;
        this.players = players;
        this.oponentTeam = oponentTeam;
    }

    public ArrayList<Long> getPlayers() {
        return players;
    }
}
