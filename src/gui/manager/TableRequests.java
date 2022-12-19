package gui.manager;
// not done not tested
public class TableRequests {
    private String name;
    private String fromValue;
    private String toValue;

    public TableRequests(String name, String fromValue, String toValue) {
        this.name = name;
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    public String getName() {
        return name;
    }

    public String getFromValue() {
        return fromValue;
    }

    public String getToValue() {
        return toValue;
    }
}
