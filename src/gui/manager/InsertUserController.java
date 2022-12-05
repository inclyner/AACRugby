package gui.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InsertUserController {

    @FXML
    private Button btnCreate;
    @FXML
    private CheckBox chbFemale;
    @FXML
    private CheckBox chbMale;
    @FXML
    private ComboBox<String> cmbAptitude;
    @FXML
    private ComboBox<String> cmbPosition;
    @FXML
    private ComboBox<String> cmbTypeUser;
    @FXML
    private TextField tfAge;
    @FXML
    private DatePicker tfBirthDate;
    @FXML
    private TextField tfCitizenCardNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfHeight;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfWeight;
    private ObservableList<String> typeOfUsersList = FXCollections.observableArrayList("Coach","Player","Doctor");
    private ObservableList<String> AptitudeList = FXCollections.observableArrayList("Fit","Not Fit");
    private ObservableList<String> PositionsList = FXCollections.observableArrayList(
            "Wing","Centre", "Fly-Half",
            "Scrum-Half","Number Eight", "Flanker",
            "Hooker","Prop");

    @FXML
    void initialize() {
        cmbTypeUser.setItems(typeOfUsersList);
        cmbAptitude.setItems(AptitudeList);
        cmbPosition.setItems(PositionsList);
    }

}

