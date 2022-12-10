package gui.manager;

public class TableRequests {
    private String name;
    private String requestedChange;
    private String fromValue;
    private String toValue;

    public TableRequests(String name, String requestedChange, String fromValue, String toValue) {
        this.name = name;
        this.requestedChange = requestedChange;
        this.fromValue = fromValue;
        this.toValue = toValue;
    }

    public String getName() {
        return name;
    }

    public String getRequestedChange() {
        return requestedChange;
    }

    public String getFromValue() {
        return fromValue;
    }

    public String getToValue() {
        return toValue;
    }
}
