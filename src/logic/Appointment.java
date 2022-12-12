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

    public Long getnCCAuthor() {
        return nCCAuthor;
    }

    public void setnCCAuthor(Long nCCAuthor) {
        this.nCCAuthor = nCCAuthor;
    }

    public void setInitialTime(String initialTime) {
        this.initialTime = initialTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "Appointment{" +
                "nCCAuthor=" + nCCAuthor +
                ", initialTime='" + initialTime + '\'' +
                ", finalTime='" + finalTime + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
