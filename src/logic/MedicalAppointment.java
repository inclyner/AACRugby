package logic;

public class MedicalAppointment extends Appointment{
    private Long playerCC;

    public MedicalAppointment(Long nCCAuthor, String initialTime, String finalTime, String date, long playerCC) {
        super(nCCAuthor, initialTime, finalTime, date);
        this.playerCC = playerCC;
    }

    public Long getPlayerCC() {
        return playerCC;
    }

    @Override
    public String toString() {
        return super.toString() +
                "playerCC=" + playerCC +
                '}';
    }
}
