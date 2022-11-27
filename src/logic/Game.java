package logic;

import java.util.ArrayList;

public class Game extends Appointment{

    private String local;
    private ArrayList<Long> players;
    private String oponentTeam;

    public Game(String nameAuthor, String initialTime, String finalTime,String local, ArrayList<Long> players, String oponentTeam) {
        super(nameAuthor, initialTime, finalTime);
        this.local = local;
        this.players = players;
        this.oponentTeam = oponentTeam;
    }

}
