package logic;

import java.util.ArrayList;

public class Calendar {
    private Long nCCAuthor;
    private ArrayList<Appointment> appointments;

    public Calendar(Long nCCAuthor) {
        this.nCCAuthor = nCCAuthor;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
