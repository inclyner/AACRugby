package logic;

import Users.Player;

import java.util.ArrayList;

public class Practise extends Appointment{
    private String local;
    private ArrayList<Long> players;
    public Practise(Long nameAuthor, ArrayList<Long> players, String initialTime, String finalTime,String local, String date) {
        super(nameAuthor, initialTime, finalTime, date);
        this.players = players;
        this.local = local;
    }

    public ArrayList<Long> getPlayers() {
        return players;
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
