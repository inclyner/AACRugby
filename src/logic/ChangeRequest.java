package logic;

public class ChangeRequest {

    String newInfo, oldInfo;
    Long playerCC;

    public ChangeRequest(String newInfo, String oldInfo, Long playerCC) {
        this.newInfo = newInfo;
        this.oldInfo = oldInfo;
        this.playerCC = playerCC;
    }

    public String getNewInfo() {
        return newInfo;
    }

    public void setNewInfo(String newInfo) {
        this.newInfo = newInfo;
    }

    public String getOldInfo() {
        return oldInfo;
    }

    public void setOldInfo(String oldInfo) {
        this.oldInfo = oldInfo;
    }

    public Long getPlayerCC() {
        return playerCC;
    }

    public void setPlayerCC(Long playerCC) {
        this.playerCC = playerCC;
    }
}
