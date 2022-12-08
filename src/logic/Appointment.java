package logic;

public class Appointment {
    private Long nCCAuthor;
    private String initialTime;
    private String finalTime;

    private String date;

    public Appointment(Long nCCAuthor, String initialTime, String finalTime, String date) {
        this.nCCAuthor = nCCAuthor;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.date = date;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public Long getNameAuthor() {
        return nCCAuthor;
    }

    public String getInitialTime() {
        return initialTime;
    }

    public String getFinalTime() {
        return finalTime;
    }
}
