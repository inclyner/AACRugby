package gui.manager;

import javafx.scene.control.CheckBox;

public class TableDeleteSetter {
    private String name;
    private String typeOfUser;
    private CheckBox checkBox;
    private String email;

    public String getEmail() {
        return email;
    }

    public TableDeleteSetter(String name, String typeOfUser,String email) {
        this.name = name;
        this.typeOfUser = typeOfUser;
        this.checkBox = new CheckBox();
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
