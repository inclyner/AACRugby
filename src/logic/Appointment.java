package logic;

public class Appointment {
    private Long nCCAuthor;
    private String initialTime;
    private String finalTime;

    public Appointment(Long nCCAuthor, String initialTime, String finalTime) {
        this.nCCAuthor = nCCAuthor;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
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
