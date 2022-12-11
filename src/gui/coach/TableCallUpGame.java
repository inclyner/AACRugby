package gui.coach;

import javafx.scene.control.CheckBox;

public class TableCallUpGame {
    private String name;
    private String email;
    private CheckBox checkBox;

    public TableCallUpGame(String name, String email) {
        this.name = name;
        this.email = email;
        this.checkBox = new CheckBox();
    }

    public String getName() {
        return name;
    }

    public String getEmail(){return  email;}

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
