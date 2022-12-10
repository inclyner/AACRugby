package gui.player;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;

public class PlayerPersonalDataController {


    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<?> cmbAptitude;

    @FXML
    private ComboBox<String> cmbPersonalData;

    @FXML
    private ComboBox<?> cmbPosition;

    @FXML
    private TextField tfAge;

    @FXML
    private DatePicker tfBirthDate;

    @FXML
    private TextField tfCC;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfHeight;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private TextField tfWeight;

    private ObservableList<String> optionsViewPersonalData = FXCollections.observableArrayList(
            "Personal Data","Notes","Diet Information");

    @FXML
    void onClickBtnBack(ActionEvent event) {
        try {
            Main main = new Main();
            main.changeScene("player\\PlayerMainView.fxml");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClivkBtnSave(ActionEvent event) {

    }

    @FXML
    void onSelectBirthDate(ActionEvent event) {

    }

    @FXML
    void initialize() {
        cmbPersonalData.setItems(optionsViewPersonalData);
    }
}
