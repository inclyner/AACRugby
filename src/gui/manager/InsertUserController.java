package gui.manager;

import gui.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class InsertUserController {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
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
    private Label labelAptitude;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelPosition;

    @FXML
    private Label labelWeight;
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
    private ObservableList<String> typeOfUsersList = FXCollections.observableArrayList(
            "Coach","Player","Doctor");
    private ObservableList<String> AptitudeList = FXCollections.observableArrayList(
            "Fit","Not Fit");
    private ObservableList<String> PositionsList = FXCollections.observableArrayList(
            "Wing","Centre", "Fly-Half",
            "Scrum-Half","Number Eight", "Flanker",
            "Hooker","Prop");

    @FXML
    void onCreateBtnClick(MouseEvent event) {
        try {
            Main main = new Main();
            // TODO: metodo no model manager para criar user
            //main.changeScene("manager\\InsertUserView.fxml");

        } catch (SQLException e){
            System.err.println(e);
        }
    }



    @FXML
    void OnSelectTypeOfUser(ActionEvent event) {
        String selectedType = cmbTypeUser.getSelectionModel().getSelectedItem().toString();
        //System.out.println(selectedType);

        if(selectedType.equals("Player")){
            labelAptitude.setText("*");
            labelHeight.setText("*");
            labelWeight.setText("*");
            labelPosition.setText("*");
        } else {
            labelAptitude.setText("");
            labelHeight.setText("");
            labelWeight.setText("");
            labelPosition.setText("");
        }
    }

    @FXML
    void onCancelBtnClick(ActionEvent event) {
        try {
            Main main = new Main();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Canceling Operation");
            alert.setContentText("Are you sure you want do cancel the operation?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get() == null)
                return;
            else if(option.get() == ButtonType.CANCEL)
                return;
            else if(option.get() == ButtonType.OK)
                main.getStage().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        if(cmbTypeUser.getSelectionModel().getSelectedItem().isEmpty() ||
                (!chbMale.isSelected()) && !chbFemale.isSelected() ||
                tfName.getText().isEmpty() ||
                tfBirthDate.getValue() == null ||
                tfEmail.getText().isEmpty() ||
                tfAge.getText().isEmpty() ||
                tfCitizenCardNumber.getText().isEmpty() ||
                tfPhoneNumber.getText().isEmpty() ||
                tfPassword.getText().isEmpty()
        ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Canceling Operation");
                alert.setContentText("Are you sure you want do cancel the operation?");
                alert.showAndWait();
        }
*/




    }

    @FXML
    void onSelectBirthDate(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthAsDate = LocalDate.parse(tfBirthDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        int age = Utils.Utils.calculateAge(birthAsDate,currentDate);

        tfAge.setText(Integer.toString(age));
    }

    @FXML
    void initialize() {
        cmbTypeUser.setItems(typeOfUsersList);
        cmbAptitude.setItems(AptitudeList);
        cmbPosition.setItems(PositionsList);

        labelAptitude.setText("");
        labelHeight.setText("");
        labelWeight.setText("");
        labelPosition.setText("");
    }

}

