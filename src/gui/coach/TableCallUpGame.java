package gui.coach;
// done not tested
import javafx.scene.control.CheckBox;

public class TableCallUpGame {
    private String name;
    private CheckBox checkBox;

    public TableCallUpGame(String name) {
        this.name = name;
        this.checkBox = new CheckBox();
    }

    public String getName() {
        return name;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
