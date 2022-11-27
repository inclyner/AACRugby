package logic;

public class Appointment {
    private String nameAuthor;
    private String initialTime;
    private String finalTime;

    public Appointment(String nameAuthor, String initialTime, String finalTime) {
        this.nameAuthor = nameAuthor;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getInitialTime() {
        return initialTime;
    }

    public String getFinalTime() {
        return finalTime;
    }
}
