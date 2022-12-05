package logic;

import Users.Player;

import java.util.ArrayList;

public class Practise extends Appointment{
    private String local;
    private ArrayList<Long> players;
    public Practise(Long nameAuthor, ArrayList<Long> players, String initialTime, String finalTime,String local) {
        super(nameAuthor, initialTime, finalTime);
        this.local = local;
    }
}
